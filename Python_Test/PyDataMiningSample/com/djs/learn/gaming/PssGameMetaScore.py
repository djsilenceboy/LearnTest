'''
Process data by SQLite to generate PSS games with Metacritic scores.

Update log: (date / version / author : comments)
2020-07-13 / 1.0.0 / Du Jiang : Creation
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
__input_file_path_playstation_store = None
__input_file_path_metascritic = None
__db_file_path = None
__output_file_prefix_path = None


def prepare_db(dbConnection, dbCursor):
    dbCursor.execute("""
CREATE TABLE PSS_GAME_LIST (
  NAME                        VARCHAR(100) NOT NULL,
  SUB_NAME                    VARCHAR(50),
  PROVIDER                    VARCHAR(50),
  GENRES                      VARCHAR(50),
  PLATFORMS                   VARCHAR(30) NOT NULL,
  RELEASE_DATE                TIMESTAMP,
  DISPLAY_PRICE               VARCHAR(12) NOT NULL,
  PRICE_VALUE                 INT NOT NULL,
  DISCOUNT_PERCENT            INT NOT NULL,
  DISCOUNT_FROM               TIMESTAMP,
  DISCOUNT_TO                 TIMESTAMP,
  CONTENT_TYPE                VARCHAR(12),
  SKU_ID                      VARCHAR(50) NOT NULL,
  GAME_POST                   VARCHAR(100)
)
        """)

    dbCursor.execute("CREATE INDEX PSS_GAME_LIST_1 ON PSS_GAME_LIST (NAME)")
    dbCursor.execute("CREATE INDEX PSS_GAME_LIST_2 ON PSS_GAME_LIST (PLATFORMS, NAME)")
    dbCursor.execute("CREATE INDEX PSS_GAME_LIST_3 ON PSS_GAME_LIST (PLATFORMS, DISCOUNT_PERCENT, PRICE_VALUE)")

    dbCursor.execute("""
CREATE TABLE META_GAME_LIST (
  NAME                        VARCHAR(120) NOT NULL,
  PLATFORM                    VARCHAR(20) NOT NULL,
  META_SCORE                  INT NOT NULL,
  USER_SCORE                  FLOAT NOT NULL,
  GAME_INFO_LINK              VARCHAR(250)
)
        """)

    dbCursor.execute("CREATE INDEX META_GAME_LIST_1 ON META_GAME_LIST (NAME)")
    dbCursor.execute("CREATE INDEX META_GAME_LIST_2 ON META_GAME_LIST (PLATFORM, NAME)")
    dbCursor.execute("CREATE INDEX META_GAME_LIST_3 ON META_GAME_LIST (PLATFORM, META_SCORE, USER_SCORE, NAME)")
    dbCursor.execute("CREATE INDEX META_GAME_LIST_4 ON META_GAME_LIST (PLATFORM, USER_SCORE, META_SCORE, NAME)")

    dbConnection.commit()


def import_data(dbConnection):
    transactionHeaders = [
"NAME",
"SUB_NAME",
"PROVIDER",
"GENRES",
"PLATFORMS",
"RELEASE_DATE",
"DISPLAY_PRICE",
"PRICE_VALUE",
"DISCOUNT_PERCENT",
"DISCOUNT_FROM",
"DISCOUNT_TO",
"CONTENT_TYPE",
"SKU_ID",
"GAME_POST"
    ]
    dataFrame = pd.read_csv(__input_file_path_playstation_store, header = 0, names = transactionHeaders)
    dataFrame.to_sql("PSS_GAME_LIST", dbConnection, if_exists = 'replace', index = False)

    transactionHeaders = [
"NAME",
"PLATFORM",
"META_SCORE",
"USER_SCORE",
"GAME_INFO_LINK"
    ]
    dataFrame = pd.read_csv(__input_file_path_metascritic, header = 0, names = transactionHeaders)
    dataFrame.to_sql("META_GAME_LIST", dbConnection, if_exists = 'replace', index = False)


def process_ps4_game_with_score(dbConnection):
    dataFrame = pd.read_sql_query(con = dbConnection, sql = """
SELECT a.NAME, a.SUB_NAME, a.PROVIDER, a.GENRES, a.PLATFORMS, a.RELEASE_DATE,
       a.DISPLAY_PRICE, a.PRICE_VALUE, a.DISCOUNT_PERCENT, b.META_SCORE, b.USER_SCORE,
       a.DISCOUNT_FROM, a.DISCOUNT_TO, a.CONTENT_TYPE, b.GAME_INFO_LINK
FROM
  (SELECT *
   FROM PSS_GAME_LIST
   WHERE (PLATFORMS LIKE '%PS4%')) a
  INNER JOIN
  (SELECT *
   FROM META_GAME_LIST
   WHERE (PLATFORM = 'PlayStation 4')) b
  ON (a.NAME LIKE b.NAME || '%')
ORDER BY a.DISCOUNT_PERCENT DESC, b.META_SCORE DESC, b.USER_SCORE DESC, a.NAME;
    """)
    return dataFrame


def usage():
    print('''
Process data by SQLite to generate PSS games with Metacritic scores.

Usage:
-h
-p <FilePath> -m <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-p <FilePath> : PlayStation Store data file path (CSV). Compulsory.
-m <FilePath> : Metacritic data file path (CSV). Compulsory.
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

        df_ps4_game_with_score = process_ps4_game_with_score(dbConnection)

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
            df_ps4_game_with_score.to_csv(__output_file_prefix_path + "Ps4Game.csv", index = False)
            print("Output process results: ok")
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        print("ps4_game_with_score.size =", df_ps4_game_with_score.size)
        print("Output process results.")

    print("-" * 100)


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __input_file_path_playstation_store
    global __input_file_path_metascritic
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
            opts, args = getopt.getopt(argv, "hp:m:d:o:")
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
                elif opt == "-p":
                    __input_file_path_playstation_store = arg
                elif opt == "-m":
                    __input_file_path_metascritic = arg
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
    print("input_file_path_playstation_store =", __input_file_path_playstation_store)
    print("input_file_path_metascritic =", __input_file_path_metascritic)
    print("db_file_path =", __db_file_path)
    print("output_file_prefix_path =", __output_file_prefix_path)

    # Check options are valid.
    if not __show_usage:
        if (__input_file_path_playstation_store is None) or (__input_file_path_metascritic is None):
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
