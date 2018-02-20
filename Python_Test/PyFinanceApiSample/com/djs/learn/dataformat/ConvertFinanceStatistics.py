'''
Convert finance statistics: From JSON to CSV.

Update log: (date / version / author : comments)
2018-02-19 / 1.0.0 / Du Jiang : Creation
                                Support Yahoo Finance stock
'''

from collections import OrderedDict
import csv
import getopt
import json
import sys
from time import localtime, strftime, time


# Global variables.
# The value can be updated by command line options.
__data_type = None
__json_file_path = None
__csv_file_path = None

__Constants = None


class Constants_Base(object):
    '''
    Use a class to keep constant variables.
    '''
    RESULT = "Result"
    RESULT_ERROR = "Error"

    INVENTORIES = "Inventories"
    RECORD = "Record"
    INVENTORY_DATA = "Inventory data"

    URL = "URL"


class Constants_YahooStock(Constants_Base):
    STOCK_INFO = "Stock info"
    STOCK_INFO_NAME = "Name"
    STOCK_INFO_EXCHANGE = "Exchange"
    STOCK_INFO_TICKER = "Ticker"

    MARKET_INFO = "Market info"

    MARKET_INFO_FILTER = ["200-Day Moving Average",
                          "5 Year Average Dividend Yield",
                          "50-Day Moving Average",
                          "52 Week High",
                          "52 Week Low",
                          "52-Week Change",
                          "Beta",
                          "Diluted EPS (ttm)",
                          "PEG Ratio (5 yr expected)",
                          "Price/Book (mrq)",
                          "Profit Margin",
                          "Return on Assets (ttm)",
                          "Return on Equity (ttm)",
                          "Trailing Annual Dividend Yield",
                          "Trailing P/E"]


def process_inventory_list():
    '''
    Get a list of inventory info from a config file.

    @return: Dict with return results.
    '''

    global __Constants

    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    field_names = []
    records = []
    try:
        # Open input file.
        with open(__json_file_path) as input_file:
            print('input_file =', input_file)
            inventory_data = json.load(
                input_file, object_pairs_hook=OrderedDict)
            print('inventory_data =', inventory_data)

        print("-" * 80)

        if __data_type == 0:
            __Constants = Constants_YahooStock

        add_field_name = True
        for item, record_value in inventory_data[__Constants.INVENTORIES].items():
            print("item =", item)

            if record_value[__Constants.RESULT] == __Constants.RESULT_ERROR:
                print("Result =", record_value[__Constants.RESULT_ERROR])
                print("-" * 60)
                continue

            record = OrderedDict()

            if __data_type == 0:
                record[__Constants.STOCK_INFO_NAME] = record_value[__Constants.INVENTORY_DATA][__Constants.STOCK_INFO][__Constants.STOCK_INFO_NAME]
                record[__Constants.STOCK_INFO_EXCHANGE] = record_value[__Constants.INVENTORY_DATA][__Constants.STOCK_INFO][__Constants.STOCK_INFO_EXCHANGE]
                record[__Constants.STOCK_INFO_TICKER] = record_value[__Constants.INVENTORY_DATA][__Constants.STOCK_INFO][__Constants.STOCK_INFO_TICKER]

                if add_field_name:
                    field_names.append(__Constants.STOCK_INFO_NAME)
                    field_names.append(__Constants.STOCK_INFO_EXCHANGE)
                    field_names.append(__Constants.STOCK_INFO_TICKER)

                # Following iteration must be sorted for adding field names in
                # correct order.

                for item_key, item_value in sorted(record_value[__Constants.INVENTORY_DATA][__Constants.MARKET_INFO].items()):
                    if item_key in __Constants.MARKET_INFO_FILTER:
                        print(item_key, "=", item_value)
                        record[item_key] = item_value
                        if add_field_name:
                            field_names.append(item_key)

            record[__Constants.URL] = record_value[__Constants.URL]
            if add_field_name:
                field_names.append(__Constants.URL)

            records.append(record)
            if add_field_name:
                add_field_name = False
                print("field_names =", field_names)

            print("record =", record)
            print("-" * 60)

        print("-" * 80)

        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)

    print("-" * 100)

    # If given __csv_file_path, output to file; otherwise, output to
    # screen.
    if __csv_file_path:
        try:
            # Open output file.
            with open(__csv_file_path, "wt") as output_file:
                print('output_file =', output_file)
                # Output file as CSV format.
                cout = csv.DictWriter(
                    output_file, fieldnames=field_names, lineterminator="\n")
                # Write header line.
                cout.writeheader()
                # Write record lines.
                cout.writerows(records)
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        # Output screen as JSON format.
        print("field_names =", field_names)
        print("records =", records)

    print("-" * 100)


def usage():
    print('''
Convert finance statistics: From JSON to CSV.

Usage:
-h
-d <DataType> -i <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-d <DataType> : Finance data type. Compulsory, Value [0: Google Finance stock].
-i <FilePath> : Environment info file path (CSV). Compulsory.
-o <FilePath> : Result output file path (JSON). Optional, output to screen by default.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __data_type
    global __json_file_path
    global __csv_file_path

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
            opts, args = getopt.getopt(argv, "hd:i:o:")
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
                elif opt == "-d":
                    __data_type = int(arg)
                elif opt == "-i":
                    __json_file_path = arg
                elif opt == "-o":
                    __csv_file_path = arg
                else:
                    __show_usage, __exit_code, __error_message = True, - \
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, - \
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("data_type =", __data_type)
    print("json_file_path =", __json_file_path)
    print("csv_file_path", __csv_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__json_file_path is None):
            __show_usage, __exit_code, __error_message = True, - \
                4, "Missing compulsory command line option."
        elif (__data_type < 0) or (__data_type > 1):
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -d."

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
