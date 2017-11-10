'''
Convert Google finance currency data: From JSON to CSV.

Update log: (date / version / author : comments)
2017-11-07 / 1.0.0 / Du Jiang : Creation
'''

from collections import OrderedDict
import csv
import getopt
import json
import sys
from time import localtime, strftime, sleep, time


# Global variables.
# The value can be updated by command line options.
__json_file_path = None
__csv_file_path = None


class Constants(object):
    '''
    Use a class to keep constant variables.
    '''

    CURRENCIES = "Currencies"
    RECORD = "Record"

    RESULT = "Result"
    RESULT_ERROR = "Error"

    CURRENCY_INFO = "Currency info"
    CURRENCY_INFO_FROM_SYMBOL = "From symbol"
    CURRENCY_INFO_TO_SYMBOL = "To symbol"

    EXCHANGE_INFO = "Exchange info"
    EXCHANGE_INFO_VALUE = "Value"

    URL = "URL"


def process_currency_list():
    '''
    Get a list of currency info from a config file.

    @return: Dict with return results.
    '''

    global __json_file_path
    global __csv_file_path

    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    field_names = []
    records = []
    try:
        # Open input file.
        with open(__json_file_path) as input_file:
            print('input_file =', input_file)
            currency_data = json.load(
                input_file, object_pairs_hook=OrderedDict)
            print('currency_data =', currency_data)

        print("-" * 80)

        add_field_name = True
        for item, record_value in currency_data[Constants.CURRENCIES].items():
            print("item =", item)

            if record_value[Constants.RESULT] == Constants.RESULT_ERROR:
                print("Result =", record_value[Constants.RESULT_ERROR])
                print("-" * 60)
                continue

            record = OrderedDict()
            record[Constants.CURRENCY_INFO_FROM_SYMBOL] = record_value[Constants.CURRENCY_INFO][Constants.CURRENCY_INFO_FROM_SYMBOL]
            record[Constants.CURRENCY_INFO_TO_SYMBOL] = record_value[Constants.CURRENCY_INFO][Constants.CURRENCY_INFO_TO_SYMBOL]
            record[Constants.EXCHANGE_INFO_VALUE] = record_value[Constants.EXCHANGE_INFO][Constants.EXCHANGE_INFO_VALUE]

            if add_field_name:
                field_names.append(Constants.CURRENCY_INFO_FROM_SYMBOL)
                field_names.append(Constants.CURRENCY_INFO_TO_SYMBOL)
                field_names.append(Constants.EXCHANGE_INFO_VALUE)

            record[Constants.URL] = record_value[Constants.URL]
            if add_field_name:
                field_names.append(Constants.URL)

            records.append(record)
            if add_field_name:
                add_field_name = False
                print("field_names =", field_names)

            print("record =", record)
            print("-" * 60)

        print("-" * 80)

        print("Process currency list: ok.")
    except Exception as e:
        print("Process currency list: Exception = {0}".format(e))

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
Convert Google finance currency data: From JSON to CSV.

Usage:
-h
-i <file path> [-o <file path>]

Options:
-h : Show help.
-i <file path> : Environment info file path (CSV). Compulsory.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

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
            opts, args = getopt.getopt(argv, "hi:o:")
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
    print("json_file_path =", __json_file_path)
    print("csv_file_path", __csv_file_path)

    # Check options are valid.
    if not __show_usage:
        if not __json_file_path:
            __show_usage, __exit_code, __error_message = True, - \
                4, "Missing compulsory command line option."

    if not __show_usage:
        process_currency_list()
    else:
        print("__exit_code =", __exit_code)
        if __error_message:
            print("__error_message =", __error_message)
        print("")
        usage()
        sys.exit(__exit_code)


if __name__ == '__main__':
    main(sys.argv[1:])
