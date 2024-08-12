'''
Process URA CSV data by SQLite.

Update log: (date / version / author : comments)
2023-06-29 / 1.0.0 / Du Jiang : Creation
                                Support Transaction and Rental data
'''

import csv
import getopt
import math
from os import path
import os
import sqlite3
import sys
from time import localtime, strftime, time

import pandas as pd

# Global variables.
# The value can be updated by command line options.
__data_type = None
__input_file_path_transaction = None
__input_file_path_rental = None
__db_file_path = None
__output_file_prefix_path = None


def prepare_db(dbConnection, dbCursor):
    dbCursor.execute("""
CREATE TABLE URA_CONDOEC_TRANS_HIST (
  PROJECT_NAME                VARCHAR(100) NOT NULL,
  PRICE                       INT NOT NULL,
  SALE_DATE                   VARCHAR(20) NOT NULL,
  STREET_NAME                 VARCHAR(100) NOT NULL,
  SALE_TYPE                   VARCHAR(20) NOT NULL,
  AREA_TYPE                   VARCHAR(20) NOT NULL,
  FLOOR_AREA                  INT NOT NULL,
  PRICE_PSM                   INT NOT NULL,
  PROPERTY_TYPE               VARCHAR(50) NOT NULL,
  UNITS                       INT NOT NULL,
  TENURE                      VARCHAR(50) NOT NULL,
  POSTAL_DISTRICT             INT NOT NULL,
  MARKET_SEGMENT              VARCHAR(5) NOT NULL,
  FLOOR_LEVEL                 VARCHAR(20) NOT NULL,
  TENURE_YEAR                 INT NOT NULL,
  TENURE_LENGTH               INT NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  FLOOR_AREA_UPPER            INT NOT NULL,
  SALE_YEAR                   INT NOT NULL
)
        """)

    dbCursor.execute("CREATE INDEX URA_CONDOEC_TRANS_HIST_1 ON URA_CONDOEC_TRANS_HIST (POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, SALE_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER)")
    dbCursor.execute("CREATE INDEX URA_CONDOEC_TRANS_HIST_2 ON URA_CONDOEC_TRANS_HIST (POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, SALE_YEAR, SALE_DATE)")
    dbCursor.execute("CREATE INDEX URA_CONDOEC_TRANS_HIST_3 ON URA_CONDOEC_TRANS_HIST (POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA)")

    dbCursor.execute("""
CREATE TABLE URA_CONDOEC_RENT_HIST (
  PROJECT_NAME                VARCHAR(100) NOT NULL,
  STREET_NAME                 VARCHAR(100) NOT NULL,
  POSTAL_DISTRICT             INT NOT NULL,
  PROPERTY_TYPE               VARCHAR(50) NOT NULL,
  BEDROOM_NUM                 INT NOT NULL,
  MONTHLY_RENT                INT NOT NULL,
  FLOOR_AREA                  VARCHAR(20) NOT NULL,
  LEASE_DATE                  VARCHAR(20) NOT NULL,
  YEARLY_RENT                 INT NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  FLOOR_AREA_UPPER            INT NOT NULL,
  LEASE_YEAR                  INT NOT NULL
)
        """)

    dbCursor.execute("CREATE INDEX URA_CONDOEC_RENT_HIST_1 ON URA_CONDOEC_RENT_HIST (POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER)")

    dbConnection.commit()


def import_data(dbConnection):
    transactionHeaders = [

"PROJECT_NAME",
"PRICE",
"SALE_DATE",
"STREET_NAME",
"SALE_TYPE",
"AREA_TYPE",
"FLOOR_AREA",
"PRICE_PSM",
"PROPERTY_TYPE",
"UNITS",
"TENURE",
"POSTAL_DISTRICT",
"MARKET_SEGMENT",
"FLOOR_LEVEL",
"TENURE_YEAR",
"TENURE_LENGTH",
"FLOOR_AREA_LOWER",
"FLOOR_AREA_UPPER",
"SALE_YEAR"
    ]
    dataFrame = pd.read_csv(__input_file_path_transaction, header = 0, names = transactionHeaders)
    dataFrame.to_sql("URA_CONDOEC_TRANS_HIST", dbConnection, if_exists = 'replace', index = False)

    transactionHeaders = [
"PROJECT_NAME",
"STREET_NAME",
"POSTAL_DISTRICT",
"PROPERTY_TYPE",
"BEDROOM_NUM",
"MONTHLY_RENT",
"FLOOR_AREA",
"LEASE_DATE",
"YEARLY_RENT",
"FLOOR_AREA_LOWER",
"FLOOR_AREA_UPPER",
"LEASE_YEAR"
    ]
    dataFrame = pd.read_csv(__input_file_path_rental, header = 0, names = transactionHeaders)
    dataFrame.to_sql("URA_CONDOEC_RENT_HIST", dbConnection, if_exists = 'replace', index = False)


def process_transaction_yearly_avg_price(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, SALE_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS PRICE_AVG, COUNT(*) AS TRANSACTIONS
FROM URA_CONDOEC_TRANS_HIST
GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, SALE_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER
ORDER BY SALE_YEAR, FLOOR_AREA_LOWER, PRICE_AVG;
    """)
    return dataFrame


def process_rental_yearly_avg_price(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(MONTHLY_RENT)) AS INTEGER) AS MONTHLY_RENT_AVG, CAST(ROUND(AVG(YEARLY_RENT)) AS INTEGER) AS YEARLY_RENT_AVG, COUNT(*) AS TRANSACTIONS
FROM URA_CONDOEC_RENT_HIST
GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER
ORDER BY LEASE_YEAR, FLOOR_AREA_LOWER, MONTHLY_RENT_AVG;
    """)
    return dataFrame


def process_price_rental_ratio(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT a.POSTAL_DISTRICT, a.PROJECT_NAME, a.STREET_NAME, a.PROPERTY_TYPE, a.LEASE_YEAR, a.FLOOR_AREA_LOWER, b.TRANSACTIONS AS SALE_TRANSACTIONS, b.PRICE_AVG, a.TRANSACTIONS AS RENT_TRANSACTIONS, a.YEARLY_RENT_AVG, CAST(ROUND(b.PRICE_AVG / a.YEARLY_RENT_AVG) AS INTEGER) AS PRICE_RENT_RATIO
FROM
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(YEARLY_RENT)) AS INTEGER) AS YEARLY_RENT_AVG, COUNT(*) AS TRANSACTIONS
   FROM URA_CONDOEC_RENT_HIST
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER) a
  LEFT JOIN
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, SALE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS PRICE_AVG, COUNT(*) AS TRANSACTIONS
   FROM URA_CONDOEC_TRANS_HIST
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, SALE_YEAR, FLOOR_AREA_LOWER) b
  ON ((a.POSTAL_DISTRICT = b.POSTAL_DISTRICT)
      AND (a.PROJECT_NAME = b.PROJECT_NAME)
      AND (a.LEASE_YEAR = b.SALE_YEAR)
      AND (a.FLOOR_AREA_LOWER = b.FLOOR_AREA_LOWER))
ORDER BY a.POSTAL_DISTRICT, a.PROJECT_NAME, a.LEASE_YEAR, a.FLOOR_AREA_LOWER;
    """)
    return dataFrame


def process_price_summary(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT a.POSTAL_DISTRICT, a.PROJECT_NAME, a.PROPERTY_TYPE, a.TENURE_YEAR, a.FLOOR_AREA,
  a.TRANSACTIONS AS ALL_TRANS, a.MIN_PRICE AS ALL_MIN, a.AVG_PRICE AS ALL_AVG, a.MAX_PRICE AS ALL_MAX,
  b.TRANSACTIONS AS F2021_TRANS, b.MIN_PRICE AS F2021_MIN, b.AVG_PRICE AS F2021_AVG, b.MAX_PRICE AS F2021_MAX,
  c.TRANSACTIONS AS F2022_TRANS, c.MIN_PRICE AS F2022_MIN, c.AVG_PRICE AS F2022_AVG, c.MAX_PRICE AS F2022_MAX,
  d.TRANSACTIONS AS F2023_TRANS, d.MIN_PRICE AS F2023_MIN, d.AVG_PRICE AS F2023_AVG, d.MAX_PRICE AS F2023_MAX,
  e.TRANSACTIONS AS F2024_TRANS, e.MIN_PRICE AS F2024_MIN, e.AVG_PRICE AS F2024_AVG, e.MAX_PRICE AS F2024_MAX
FROM
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA, COUNT(*) AS TRANSACTIONS, MIN(PRICE) AS MIN_PRICE, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS AVG_PRICE, MAX(PRICE) AS MAX_PRICE
   FROM URA_CONDOEC_TRANS_HIST
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA) a
  LEFT JOIN
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA, COUNT(*) AS TRANSACTIONS, MIN(PRICE) AS MIN_PRICE, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS AVG_PRICE, MAX(PRICE) AS MAX_PRICE
   FROM URA_CONDOEC_TRANS_HIST
   WHERE (SALE_YEAR >= 2021)
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA) b
  ON ((a.POSTAL_DISTRICT = b.POSTAL_DISTRICT)
      AND (a.PROJECT_NAME = b.PROJECT_NAME)
      AND (a.PROPERTY_TYPE = b.PROPERTY_TYPE)
      AND (a.TENURE_YEAR = b.TENURE_YEAR)
      AND (a.FLOOR_AREA = b.FLOOR_AREA))
  LEFT JOIN
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA, COUNT(*) AS TRANSACTIONS, MIN(PRICE) AS MIN_PRICE, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS AVG_PRICE, MAX(PRICE) AS MAX_PRICE
   FROM URA_CONDOEC_TRANS_HIST
   WHERE (SALE_YEAR >= 2022)
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA) c
  ON ((a.POSTAL_DISTRICT = c.POSTAL_DISTRICT)
      AND (a.PROJECT_NAME = c.PROJECT_NAME)
      AND (a.PROPERTY_TYPE = c.PROPERTY_TYPE)
      AND (a.TENURE_YEAR = c.TENURE_YEAR)
      AND (a.FLOOR_AREA = c.FLOOR_AREA))
  LEFT JOIN
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA, COUNT(*) AS TRANSACTIONS, MIN(PRICE) AS MIN_PRICE, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS AVG_PRICE, MAX(PRICE) AS MAX_PRICE
   FROM URA_CONDOEC_TRANS_HIST
   WHERE (SALE_YEAR >= 2023)
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA) d
  ON ((a.POSTAL_DISTRICT = d.POSTAL_DISTRICT)
      AND (a.PROJECT_NAME = d.PROJECT_NAME)
      AND (a.PROPERTY_TYPE = d.PROPERTY_TYPE)
      AND (a.TENURE_YEAR = d.TENURE_YEAR)
      AND (a.FLOOR_AREA = d.FLOOR_AREA))
  LEFT JOIN
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA, COUNT(*) AS TRANSACTIONS, MIN(PRICE) AS MIN_PRICE, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS AVG_PRICE, MAX(PRICE) AS MAX_PRICE
   FROM URA_CONDOEC_TRANS_HIST
   WHERE (SALE_YEAR >= 2024)
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, PROPERTY_TYPE, TENURE_YEAR, FLOOR_AREA) e
  ON ((a.POSTAL_DISTRICT = e.POSTAL_DISTRICT)
      AND (a.PROJECT_NAME = e.PROJECT_NAME)
      AND (a.PROPERTY_TYPE = e.PROPERTY_TYPE)
      AND (a.TENURE_YEAR = e.TENURE_YEAR)
      AND (a.FLOOR_AREA = e.FLOOR_AREA))
ORDER BY a.POSTAL_DISTRICT, a.PROJECT_NAME, a.PROPERTY_TYPE, a.TENURE_YEAR, a.FLOOR_AREA;
    """)
    return dataFrame


def usage():
    print('''
Process URA data by SQLite.

Usage:
-h
-t <FilePath> -r <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-t <FilePath> : Transaction data file path (CSV). Compulsory.
-r <FilePath> : Rental data file path (CSV). Compulsory.
-d <FilePath> : Database file path. Optional, import data to memory by default.
-o <FilePath> : Result output file prefix path (CSV). Optional, output to screen by default.
''')


def process_inventory_list():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    try:
        if __db_file_path is None:
            dbConnection = sqlite3.connect(":memory:")
        else:
            if path.exists(__db_file_path):
                os.remove(__db_file_path)
            dbConnection = sqlite3.connect(__db_file_path)
        print("dbConnection =", dbConnection)

        dbCursor = dbConnection.cursor()
        print("dbCursor =", dbCursor)

        prepare_db(dbConnection, dbCursor)
        import_data(dbConnection)

        df_transaction_yearly_avg_price = process_transaction_yearly_avg_price(dbConnection)
        df_rental_yearly_avg_price = process_rental_yearly_avg_price(dbConnection)
        df_price_rental_ratio = process_price_rental_ratio(dbConnection)
        df_process_price_summary = process_price_summary(dbConnection)

        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))
    finally:
        if dbCursor is not None:
            dbCursor.close()
        if dbConnection is not None:
            dbConnection.close()

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)

    print("-" * 100)

    # If given __output_file_prefix_path, output to file; otherwise, output to
    # screen.
    if __output_file_prefix_path:
        try:
            df_transaction_yearly_avg_price.to_csv(__output_file_prefix_path + "_TransYearlyPrice.csv", index = False)
            df_rental_yearly_avg_price.to_csv(__output_file_prefix_path + "_RentYearlyPrice.csv", index = False)
            df_price_rental_ratio.to_csv(__output_file_prefix_path + "_PriceRentRatio.csv", index = False)
            df_process_price_summary.to_csv(__output_file_prefix_path + "_PriceSummary.csv", index = False)
            print("Output process results: ok")
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        print("transaction_yearly_avg_price.size =", df_transaction_yearly_avg_price.size)
        print("rental_yearly_avg_price.size =", df_rental_yearly_avg_price.size)
        print("price_rental_ratio.size =", df_price_rental_ratio.size)
        print("process_price_summary.size =", df_process_price_summary.size)
        print("Output process results.")

    print("-" * 100)


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __input_file_path_transaction
    global __input_file_path_rental
    global __db_file_path
    global __output_file_prefix_path

    print("argv =", argv)

    __show_usage = False
    __exit_code = 0
    __error_message = None

    # If no any option.
    if not argv:
        __show_usage = True

    # Parse command line.
    if not __show_usage:
        try:
            opts, args = getopt.getopt(argv, "ht:r:d:o:")
            print("opts =", opts)
            print("args =", args)
        except Exception as e:
            # There would be getopt.GetoptError.
            print("Parse command line: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, -1, "Wrong command line option."

    # Check and parse each option.
    if not __show_usage:
        try:
            for opt, arg in opts:
                if opt == "-h":
                    __show_usage, __exit_code = True, 0
                elif opt == "-t":
                    __input_file_path_transaction = arg
                elif opt == "-r":
                    __input_file_path_rental = arg
                elif opt == "-d":
                    __db_file_path = arg
                elif opt == "-o":
                    __output_file_prefix_path = arg
                else:
                    __show_usage, __exit_code, __error_message = True, -\
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, -\
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("input_file_path_transaction =", __input_file_path_transaction)
    print("input_file_path_rental =", __input_file_path_rental)
    print("db_file_path =", __db_file_path)
    print("output_file_prefix_path =", __output_file_prefix_path)

    # Check options are valid.
    if not __show_usage:
        if (__input_file_path_transaction is None) or (__input_file_path_rental is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."

    if not __show_usage:
        process_inventory_list()
    else:
        print("__exit_code =", __exit_code)
        if __error_message:
            print("__error_message =", __error_message)
        print("")
        usage()
        sys.exit(__exit_code)


if __name__ == '__main__':
    main(sys.argv[1:])
