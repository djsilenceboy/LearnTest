'''
Convert Fundsupermart fund data: From JSON to CSV.

Update log: (date / version / author : comments)
2017-08-13 / 1.0.0 / Du Jiang : Creation
'''

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

    START_TIME = "Start time"
    STOP_TIME = "Stop time"

    RESULT = "Result"
    ERROR = "Error"

    RESULT_OK = "Ok"
    RESULT_ERROR = "Error"

    FUNDS = "Funds"
    RECORD = "Record"

    FUND_ID = "Fund ID"
    FUND_NAME = "Fund name"
    URL = "URL"
    FUND_DATA = "Fund data"

    SECTION_BANNER_INFO = "Banner info"
    SECTION_OFFER_TO_BID_INFO = "Offer to bid info"
    SECTION_BID_TO_OFFER_INFO = "Bid to offer info"
    SECTION_RELEVANT_CHARGES = "Relevant charges"


def process_fund_list():
    '''
    Get a list of fund info from a config file.

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
            fund_data = json.load(input_file)
            print('fund_data =', fund_data)

        print("-" * 80)

        add_field_name = True
        for _, record_value in fund_data[Constants.FUNDS].items():
            record = {}
            record[Constants.FUND_NAME] = record_value[Constants.RECORD][Constants.FUND_NAME]
            record[Constants.FUND_ID] = record_value[Constants.RECORD][Constants.FUND_ID]

            if add_field_name:
                field_names.append(Constants.FUND_NAME)
                field_names.append(Constants.FUND_ID)

            # Following iteration must be sorted for adding field names in
            # correct order.

            for item_key, item_value in sorted(record_value[Constants.FUND_DATA][Constants.SECTION_BANNER_INFO].items()):
                record[item_key] = item_value
                if add_field_name:
                    field_names.append(item_key)

            for item_key, item_value in sorted(record_value[Constants.FUND_DATA][Constants.SECTION_OFFER_TO_BID_INFO].items()):
                record[item_key] = item_value
                if add_field_name:
                    field_names.append(item_key)

            for item_key, item_value in sorted(record_value[Constants.FUND_DATA][Constants.SECTION_RELEVANT_CHARGES].items()):
                if "Annual" in item_key:
                    record[item_key] = item_value
                    if add_field_name:
                        field_names.append(item_key)

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

        print("Process fund list: ok.")
    except Exception as e:
        print("Process fund list: Exception = {0}".format(e))

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
Convert Fundsupermart fund data: From JSON to CSV.

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
        process_fund_list()
    else:
        print("__exit_code =", __exit_code)
        if __error_message:
            print("__error_message =", __error_message)
        print("")
        usage()
        sys.exit(__exit_code)


if __name__ == '__main__':
    main(sys.argv[1:])
