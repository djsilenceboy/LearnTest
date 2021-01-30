'''
Check currency XE by Requests.

Update log: (date / version / author : comments)
2021-01-30 / 1.0.0 / Du Jiang : Creation
                                Support https://free.currencyconverterapi.com/

Notes:
1. It requires 3rd parth python lib (at least): requests.
'''

from concurrent.futures import ThreadPoolExecutor
import csv
import getopt
from http import HTTPStatus
import json
import sys
from time import localtime, strftime, time
import requests

# Global variables.
# The value can be updated by command line options.
__data_type = None
__inventory_info_file_path = None
__result_output_file_path = None
__concurrent_max_workers = 5
__api_key = None

__Constants = None


class Constants_Base(object):
    '''
    Use a class to keep constant variables.
    '''

    WAIT_PAGE_LOAD_MAX_TRY = 10

    RESULT = "Result"
    ERROR = "Error"

    RESULT_OK = "Ok"
    RESULT_ERROR = "Error"

    START_TIME = "Start time"
    STOP_TIME = "Stop time"

    INVENTORIES = "Inventories"
    RECORDS_NUMBER = "Records number"
    RECORD = "Record"
    INVENTORY_DATA = "Inventory data"

    URL = "URL"
    STATUS_CODE = "Status code"


class Constants_YahooStock(Constants_Base):
    API_URL = "https://free.currconv.com/api/v7/convert?apiKey={0}&q={1}_{2}&compact=y"

    COLUMN_NAME = "Name"
    COLUMN_FROM_SYMBOL = "From symbol"
    COLUMN_TO_SYMBOL = "To symbol"

    SECTION_CURRENCY_INFO = "Currency info"
    CURRENCY_INFO_FROM_SYMBOL = "From symbol"
    CURRENCY_INFO_TO_SYMBOL = "To symbol"

    SECTION_EXCHANGE_INFO = "Exchange info"
    EXCHANGE_INFO_VALUE = "Value"
    EXCHANGE_INFO_TIME = "Time"


def check_url(url):
    '''
    Use requests to get URL, and return HTTP status code.

    @param url: A string of URL.
    @return: HTTP response status code, or None if request failed.
    @return: Parsed HTTP response, or None if request failed.
    '''

    print("url =", url)
    status_code = None

    for i in range(0, __Constants.WAIT_PAGE_LOAD_MAX_TRY):
        try:
            response = requests.get(url, timeout = 5)
            status_code = response.status_code
            # print("response.text =", response.text)
            print("response.status_code =", status_code)

            print("Check url: Count {0}: url = {1}".format(
                i, url))

            # It is ok, no need retry.
            break
        except Exception as e:
            print("Check url: Count {0}: Exception = {1}".format(
                i, e))
            # If reach max try.
            if (i + 1) == __Constants.WAIT_PAGE_LOAD_MAX_TRY:
                raise e

    return status_code, response.text


def parse_data_currency_xe(response_text, results):
    xe_obj = json.loads(response_text)
    from_currency = results[__Constants.RECORD][__Constants.COLUMN_FROM_SYMBOL]
    to_currency = results[__Constants.RECORD][__Constants.COLUMN_TO_SYMBOL]
    item_key_1 = from_currency + "_" + to_currency
    item_key_2 = "val"

    # {"USD_SGD":{"val":1.32835}}
    results[__Constants.INVENTORY_DATA] = {}
    results[__Constants.INVENTORY_DATA][__Constants.SECTION_CURRENCY_INFO] = {}
    results[__Constants.INVENTORY_DATA][__Constants.SECTION_EXCHANGE_INFO] = {}

    results[__Constants.INVENTORY_DATA][__Constants.SECTION_CURRENCY_INFO][__Constants.CURRENCY_INFO_FROM_SYMBOL] = from_currency
    results[__Constants.INVENTORY_DATA][__Constants.SECTION_CURRENCY_INFO][__Constants.CURRENCY_INFO_TO_SYMBOL] = to_currency
    results[__Constants.INVENTORY_DATA][__Constants.SECTION_EXCHANGE_INFO][__Constants.EXCHANGE_INFO_VALUE] = xe_obj[item_key_1][item_key_2]


def inspect_inventory(record):
    '''
    Inspect inventory info page.

    @param record: [Currency from_symbol, To symbol]
    @return : Dict with return results.
    '''

    results = {}
    result = None

    if __data_type == 0:
        parse_get_data = parse_data_currency_xe

        currency_info_from_symbol, currency_info_to_symbol = record
        currency_info_from_symbol = currency_info_from_symbol.strip()
        currency_info_to_symbol = currency_info_to_symbol.strip()
        print("currency_info_from_symbol =", currency_info_from_symbol)
        print("currency_info_to_symbol =", currency_info_to_symbol)
        inventory_id = currency_info_from_symbol + currency_info_to_symbol

        results[inventory_id] = {}
        result = results[inventory_id]

        result[__Constants.RECORD] = {}
        result[__Constants.RECORD][__Constants.COLUMN_FROM_SYMBOL] = currency_info_from_symbol
        result[__Constants.RECORD][__Constants.COLUMN_TO_SYMBOL] = currency_info_to_symbol

        url = __Constants.API_URL.format(__api_key, currency_info_from_symbol, currency_info_to_symbol)

    print("url =", url)
    result[__Constants.URL] = url

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    result[__Constants.START_TIME] = time_str

    try:
        status_code, response_text = check_url(url)
        if status_code != HTTPStatus.OK:
            raise Exception("Get '{0}' failed with status code {1}.".format(url,
                                                                            status_code))

        # Parse and get data.
        result[__Constants.INVENTORY_DATA] = {}
        parse_get_data(response_text, result)
        print("-" * 40)

        print("Inspect inventory <{0}>: ok.".format(inventory_id))
        result[__Constants.RESULT] = __Constants.RESULT_OK
    except Exception as e:
        print("Inspect inventory <{0}> Exception = {1}".format(
            inventory_id, e))
        result[__Constants.RESULT] = __Constants.RESULT_ERROR
        result[__Constants.ERROR] = repr(e)

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)
    result[__Constants.STOP_TIME] = time_str

    return results


def process_inventory_list():
    '''
    Get a list of inventory info from a config file.
    Inspect each of them.

    @return: Dict with return results.
    '''

    global __Constants

    if __data_type == 0:
        __Constants = Constants_YahooStock

    results = {}

    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    results[__Constants.START_TIME] = time_str

    try:
        results[__Constants.INVENTORIES] = {}

        # Open input file.
        with open(__inventory_info_file_path) as record_file:
            print('record_file =', record_file)
            cin = csv.reader(record_file)
            # Get all records.
            records = [line for line in cin]
            # But not header line.
            records.pop(0)
            print("records =", records)
            results[__Constants.RECORDS_NUMBER] = len(records)
        print("-" * 80)

        # Inspect inventory concurrently.
        with ThreadPoolExecutor(max_workers = __concurrent_max_workers) as executor:
            # Wait for result to return.
            for record, result in zip(records, executor.map(inspect_inventory, records)):
                results[__Constants.INVENTORIES].update(result)

        print("-" * 80)
        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)
    results[__Constants.STOP_TIME] = time_str

    print("Results =", results)
    print("-" * 100)

    # If given __result_output_file_path, output to file; otherwise, output to
    # screen.
    if __result_output_file_path:
        try:
            # Open output file.
            with open(__result_output_file_path, "w") as result_file:
                print('result_file =', result_file)
                # Output file as JSON format.
                json.dump(results, result_file, indent = 4, sort_keys = True)
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        # Output screen as JSON format.
        print(json.dumps(results, indent = 4, sort_keys = True))

    print("-" * 100)

    return results


def usage():
    print('''
Check finance data by Requests.

Usage:
-h
-d <DataType> -i <file path> [-o <file path>] [-c <Number>] [-k <API key>]

Options:
-h : Show help.
-d <DataType> : Finance data type. Compulsory, Value [0: Yahoo Finance stock].
-i <file path> : Environment info file path (CSV). Compulsory.
-o <file path> : Result output file path (JSON). Optional, output to screen by default.
-c <Number> : Concurrent max workers to process records. Optional, Value [1, 10], 5 by default.
-k <API key> : API key for certain API. Optional.

Notes:
Inventory info file format sample (With header line):
1. Currency XE
From symbol,To symbol
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __data_type
    global __inventory_info_file_path
    global __result_output_file_path
    global __concurrent_max_workers
    global __api_key

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
            opts, args = getopt.getopt(argv, "hd:i:o:c:k:")
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
                    __inventory_info_file_path = arg
                elif opt == "-o":
                    __result_output_file_path = arg
                elif opt == "-c":
                    __concurrent_max_workers = int(arg)
                elif opt == "-k":
                    __api_key = arg
                else:
                    __show_usage, __exit_code, __error_message = True, -\
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, -\
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("data_type =", __data_type)
    print("inventory_info_file_path =", __inventory_info_file_path)
    print("result_output_file_path", __result_output_file_path)
    print("concurrent_max_workers =", __concurrent_max_workers)
    print("api_key =", __api_key)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__inventory_info_file_path is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."
        elif (__data_type < 0) or (__data_type > 1):
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -d."
        elif (__concurrent_max_workers < 1) or (__concurrent_max_workers > 10):
            __show_usage, __exit_code, __error_message = True, -6, "Wrong value for -c."
        elif (__data_type == 0) and (__api_key is None):
            __show_usage, __exit_code, __error_message = True, -7, "Wrong value for -k."

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
