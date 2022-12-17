'''
Process HDB data by SQLite.

Update log: (date / version / author : comments)
2020-06-27 / 1.0.0 / Du Jiang : Creation
                                Support Transaction
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
__db_file_path = None
__output_file_prefix_path = None


def prepare_db(dbConnection, dbCursor):
    dbCursor.execute("""
CREATE TABLE HDB_TRANS_HIST (
  RESALE_DATE                 VARCHAR(20) NOT NULL,
  TOWN                        VARCHAR(20) NOT NULL,
  FLAT_TYPE                   VARCHAR(20) NOT NULL,
  BLOCK                       VARCHAR(5) NOT NULL,
  STREET_NAME                 VARCHAR(50) NOT NULL,
  FLOOR_RANGE                 VARCHAR(10) NOT NULL,
  FLOOR_AREA                  INT NOT NULL,
  FLAT_MODEL                  VARCHAR(30) NOT NULL,
  LEASE_DATE                  INT NOT NULL,
  REMAIN_LEASE                VARCHAR(30) NOT NULL,
  RESALE_PRICE                INT NOT NULL,
  FLOOR_AREA_LOWER            INT NOT NULL,
  UNIT_PRICE                  INT NOT NULL,
  RESALE_YEAR                 INT NOT NULL
)
        """)

    dbCursor.execute("CREATE INDEX HDB_TRANS_HIST_1 ON HDB_TRANS_HIST (STREET_NAME, BLOCK, RESALE_YEAR, FLOOR_AREA, RESALE_PRICE)")

    dbConnection.commit()


def import_data(dbConnection):
    transactionHeaders = [
"RESALE_DATE",
"TOWN",
"FLAT_TYPE",
"BLOCK",
"STREET_NAME",
"FLOOR_RANGE",
"FLOOR_AREA",
"FLAT_MODEL",
"LEASE_DATE",
"REMAIN_LEASE",
"RESALE_PRICE",
"FLOOR_AREA_LOWER",
"UNIT_PRICE",
"RESALE_YEAR"
    ]
    dataFrame = pd.read_csv(__input_file_path_transaction, header = 0, names = transactionHeaders)
    dataFrame.to_sql("HDB_TRANS_HIST", dbConnection, if_exists = 'replace', index = False)


def process_transaction_avg_price_a(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT TOWN, STREET_NAME, FLAT_MODEL, FLAT_TYPE, FLOOR_AREA_LOWER, RESALE_YEAR, CAST(ROUND(AVG(RESALE_PRICE)) AS INTEGER) AS RESALE_PRICE_AVG, CAST(ROUND(AVG(UNIT_PRICE)) AS INTEGER) AS UNIT_PRICE_AVG, COUNT(*) AS TRANSACTIONS
FROM HDB_TRANS_HIST
GROUP BY TOWN, STREET_NAME, FLAT_MODEL, FLAT_TYPE, FLOOR_AREA_LOWER, RESALE_YEAR
ORDER BY TOWN, STREET_NAME, FLAT_MODEL, FLAT_TYPE, FLOOR_AREA_LOWER, RESALE_YEAR, RESALE_PRICE_AVG;
    """)
    return dataFrame


def process_transaction_avg_price_b(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT TOWN, STREET_NAME, FLAT_MODEL, FLAT_TYPE, FLOOR_RANGE, FLOOR_AREA_LOWER, RESALE_YEAR, CAST(ROUND(AVG(RESALE_PRICE)) AS INTEGER) AS RESALE_PRICE_AVG, CAST(ROUND(AVG(UNIT_PRICE)) AS INTEGER) AS UNIT_PRICE_AVG, COUNT(*) AS TRANSACTIONS
FROM HDB_TRANS_HIST
GROUP BY TOWN, STREET_NAME, FLAT_MODEL, FLAT_TYPE, FLOOR_RANGE, FLOOR_AREA_LOWER, RESALE_YEAR
ORDER BY TOWN, STREET_NAME, FLAT_MODEL, FLAT_TYPE, FLOOR_RANGE, FLOOR_AREA_LOWER, RESALE_YEAR, RESALE_PRICE_AVG;
    """)
    return dataFrame

def process_transaction_avg_price_c(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT TOWN, STREET_NAME, BLOCK, LEASE_DATE, FLAT_MODEL, FLAT_TYPE, FLOOR_AREA_LOWER, RESALE_YEAR, CAST(ROUND(AVG(RESALE_PRICE)) AS INTEGER) AS RESALE_PRICE_AVG, CAST(ROUND(AVG(UNIT_PRICE)) AS INTEGER) AS UNIT_PRICE_AVG, COUNT(*) AS TRANSACTIONS
FROM HDB_TRANS_HIST
GROUP BY TOWN, STREET_NAME, BLOCK, LEASE_DATE, FLAT_MODEL, FLAT_TYPE, FLOOR_AREA_LOWER, RESALE_YEAR
ORDER BY TOWN, STREET_NAME, BLOCK, LEASE_DATE, FLAT_MODEL, FLAT_TYPE, FLOOR_AREA_LOWER, RESALE_YEAR, RESALE_PRICE_AVG;
    """)
    return dataFrame

def process_transaction_avg_price_d(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT TOWN, STREET_NAME, BLOCK, LEASE_DATE, FLAT_MODEL, FLAT_TYPE, FLOOR_RANGE, FLOOR_AREA_LOWER, RESALE_YEAR, CAST(ROUND(AVG(RESALE_PRICE)) AS INTEGER) AS RESALE_PRICE_AVG, CAST(ROUND(AVG(UNIT_PRICE)) AS INTEGER) AS UNIT_PRICE_AVG, COUNT(*) AS TRANSACTIONS
FROM HDB_TRANS_HIST
GROUP BY TOWN, STREET_NAME, BLOCK, LEASE_DATE, FLAT_MODEL, FLAT_TYPE, FLOOR_RANGE, FLOOR_AREA_LOWER, RESALE_YEAR
ORDER BY TOWN, STREET_NAME, BLOCK, LEASE_DATE, FLAT_MODEL, FLAT_TYPE, FLOOR_RANGE, FLOOR_AREA_LOWER, RESALE_YEAR, RESALE_PRICE_AVG;
    """)
    return dataFrame

def usage():
    print('''
Process HDB data by SQLite.

Usage:
-h
-t <FilePath> -r <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-t <FilePath> : Transaction data file path (CSV). Compulsory.
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

        df_transaction_avg_price_a = process_transaction_avg_price_a(dbConnection)
        df_transaction_avg_price_b = process_transaction_avg_price_b(dbConnection)
        df_transaction_avg_price_c = process_transaction_avg_price_c(dbConnection)
        df_transaction_avg_price_d = process_transaction_avg_price_d(dbConnection)

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
            df_transaction_avg_price_a.to_csv(__output_file_prefix_path + "TransPriceA.csv", index = False)
            df_transaction_avg_price_b.to_csv(__output_file_prefix_path + "TransPriceB.csv", index = False)
            df_transaction_avg_price_c.to_csv(__output_file_prefix_path + "TransPriceC.csv", index = False)
            df_transaction_avg_price_d.to_csv(__output_file_prefix_path + "TransPriceD.csv", index = False)
            print("Output process results: ok")
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        print("transaction_avg_price.size =", df_transaction_avg_price.size)
        print("Output process results.")

    print("-" * 100)


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __input_file_path_transaction
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
            opts, args = getopt.getopt(argv, "ht:d:o:")
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
    print("db_file_path =", __db_file_path)
    print("output_file_prefix_path =", __output_file_prefix_path)

    # Check options are valid.
    if not __show_usage:
        if __input_file_path_transaction is None:
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
