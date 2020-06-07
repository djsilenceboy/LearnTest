'''
Process URA data by SQLite.

Update log: (date / version / author : comments)
2020-06-07 / 1.0.0 / Du Jiang : Creation
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
            df_transaction_yearly_avg_price.to_csv(__output_file_prefix_path + "TransYearlyPrice.csv", index = False)
            df_rental_yearly_avg_price.to_csv(__output_file_prefix_path + "RentYearlyPrice.csv", index = False)
            df_price_rental_ratio.to_csv(__output_file_prefix_path + "PriceRentRatio.csv", index = False)
            print("Output process results: ok")
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        print("transaction_yearly_avg_price.size =", df_transaction_yearly_avg_price.size)
        print("rental_yearly_avg_price.size =", df_rental_yearly_avg_price.size)
        print("price_rental_ratio.size =", df_price_rental_ratio.size)
        print("Output process results.")

    print("-" * 100)


def prepare_db(dbConnection, dbCursor):
    dbCursor.execute("""
CREATE TABLE URA_CONDOEC_TRANS_HIST (
  PROJECT_NAME                VARCHAR(100) NOT NULL,
  STREET_NAME                 VARCHAR(100) NOT NULL,
  PROPERTY_TYPE               VARCHAR(50) NOT NULL,
  POSTAL_DISTRICT             INT NOT NULL,
  MARKET_SEGMENT              VARCHAR(5) NOT NULL,
  TENURE                      VARCHAR(50) NOT NULL,
  SALE_TYPE                   VARCHAR(20) NOT NULL,
  UNITS                       INT NOT NULL,
  PRICE                       INT NOT NULL,
  FLOOR_AREA                  INT NOT NULL,
  AREA_TYPE                   VARCHAR(20) NOT NULL,
  FLOOR_LEVEL                 VARCHAR(20) NOT NULL,
  PRICE_PSM                   INT NOT NULL,
  SALE_DATE                   VARCHAR(20) NOT NULL,
  TENURE_YEAR                 INT NOT NULL,
  TENURE_LENGTH               INT NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  FLOOR_AREA_UPPER            INT NOT NULL,
  SALE_YEAR                   INT NOT NULL
)
        """)

    dbCursor.execute("CREATE INDEX URA_CONDOEC_TRANS_HIST_1 ON URA_CONDOEC_TRANS_HIST (POSTAL_DISTRICT, SALE_YEAR, FLOOR_AREA_LOWER, PRICE)")

    dbCursor.execute("""
CREATE TABLE URA_CONDOEC_RENT_HIST (
  PROJECT_NAME                VARCHAR(100) NOT NULL,
  STREET_NAME                 VARCHAR(100) NOT NULL,
  POSTAL_DISTRICT             INT NOT NULL,
  PROPERTY_TYPE               VARCHAR(50) NOT NULL,
  BEDROOM_NUM                 INT NOT NULL,
  MONTHLY_GROSS_RENT          INT NOT NULL,
  FLOOR_AREA                  VARCHAR(20) NOT NULL,
  LEASE_DATE                  VARCHAR(20) NOT NULL,
  YEARLY_GROSS_RENT           INT NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  FLOOR_AREA_UPPER            INT NOT NULL,
  LEASE_YEAR                  INT NOT NULL
)
        """)

    dbCursor.execute("CREATE INDEX URA_CONDOEC_RENT_HIST_1 ON URA_CONDOEC_RENT_HIST (POSTAL_DISTRICT, LEASE_YEAR, FLOOR_AREA_LOWER, YEARLY_GROSS_RENT)")

    dbConnection.commit()


def import_data(dbConnection):
    transactionHeaders = [
"PROJECT_NAME",
"STREET_NAME",
"PROPERTY_TYPE",
"POSTAL_DISTRICT",
"MARKET_SEGMENT",
"TENURE",
"SALE_TYPE",
"UNITS",
"PRICE",
"FLOOR_AREA",
"AREA_TYPE",
"FLOOR_LEVEL",
"PRICE_PSM",
"SALE_DATE",
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
"MONTHLY_GROSS_RENT",
"FLOOR_AREA",
"LEASE_DATE",
"YEARLY_GROSS_RENT",
"FLOOR_AREA_LOWER",
"FLOOR_AREA_UPPER",
"LEASE_YEAR"
    ]
    dataFrame = pd.read_csv(__input_file_path_rental, header = 0, names = transactionHeaders)
    dataFrame.to_sql("URA_CONDOEC_RENT_HIST", dbConnection, if_exists = 'replace', index = False)


def process_transaction_yearly_avg_price(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS PRICE_AVG
FROM URA_CONDOEC_TRANS_HIST
GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER
ORDER BY SALE_YEAR, FLOOR_AREA_LOWER, PRICE_AVG;
    """)
    return dataFrame


def process_rental_yearly_avg_price(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(YEARLY_GROSS_RENT)) AS INTEGER) AS YEARLY_GROSS_RENT_AVG
FROM URA_CONDOEC_RENT_HIST
GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER
ORDER BY LEASE_YEAR, FLOOR_AREA_LOWER, YEARLY_GROSS_RENT_AVG;
    """)
    return dataFrame


def process_price_rental_ratio(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT a.POSTAL_DISTRICT, a.PROJECT_NAME, a.STREET_NAME, a.PROPERTY_TYPE, a.TENURE_YEAR, a.SALE_YEAR, a.FLOOR_AREA_LOWER, a.PRICE_AVG, b.YEARLY_GROSS_RENT_AVG, CAST(ROUND(a.PRICE_AVG / b.YEARLY_GROSS_RENT_AVG) AS INTEGER) AS PRICE_RENT_RATIO
FROM
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(PRICE)) AS INTEGER) AS PRICE_AVG
   FROM URA_CONDOEC_TRANS_HIST
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, TENURE_YEAR, SALE_YEAR, FLOOR_AREA_LOWER) a
  INNER JOIN
  (SELECT POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER, CAST(ROUND(AVG(YEARLY_GROSS_RENT)) AS INTEGER) AS YEARLY_GROSS_RENT_AVG
   FROM URA_CONDOEC_RENT_HIST
   GROUP BY POSTAL_DISTRICT, PROJECT_NAME, STREET_NAME, PROPERTY_TYPE, LEASE_YEAR, FLOOR_AREA_LOWER) b
  ON ((a.POSTAL_DISTRICT = b.POSTAL_DISTRICT)
      AND (a.PROJECT_NAME = b.PROJECT_NAME)
      AND (a.SALE_YEAR = b.LEASE_YEAR)
      AND (a.FLOOR_AREA_LOWER = b.FLOOR_AREA_LOWER))
ORDER BY a.SALE_YEAR, a.FLOOR_AREA_LOWER, PRICE_RENT_RATIO, a.PRICE_AVG;
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
