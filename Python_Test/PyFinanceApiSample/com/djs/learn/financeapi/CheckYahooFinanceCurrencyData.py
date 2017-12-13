'''
Check Yahoo finance currency data.

Update log: (date / version / author : comments)
2017-12-10 / 1.0.0 / Du Jiang : Creation

Notes:
1. When using Selenium, the webdriver.Firefox.get() does not return, always timeout.
2. Those page load detection methods are all not working.
'''

from concurrent.futures import ThreadPoolExecutor
import csv
import getopt
from http import HTTPStatus
import json
import sys
from time import localtime, strftime, time
from bs4 import BeautifulSoup
import requests

# Global variables.
# The value can be updated by command line options.
__inventory_info_file_path = None
__result_output_file_path = None
__concurrent_max_workers = 5


class Constants(object):
    '''
    Use a class to keep constant variables.
    '''

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
    API_URL = "https://finance.yahoo.com/quote/{0}=X"
    STATUS_CODE = "Status code"

    COLUMN_NAME = "Name"
    COLUMN_FROM_SYMBOL = "From symbol"
    COLUMN_TO_SYMBOL = "To symbol"

    SECTION_CURRENCY_INFO = "Currency info"
    CURRENCY_INFO_NAME = "Name"
    CURRENCY_INFO_FROM_SYMBOL = "From symbol"
    CURRENCY_INFO_TO_SYMBOL = "To symbol"

    SECTION_EXCHANGE_INFO = "Exchange info"
    EXCHANGE_INFO_RATE = "Rate"
    EXCHANGE_INFO_VALUE = "Value"
    EXCHANGE_INFO_TIME = "Time"


def check_url(url):
    '''
    Use requests to get URL, and return HTTP status code.

    @param url: A string of URL.
    @return: HTTP response status code, or None if request failed.
    @return: HTTP response, or None if request failed.
    '''

    print("url =", url)
    status_code = None

    try:
        headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0"}
        response = requests.get(url, headers=headers)
        # print("response =", response)
        print("response.status_code =", response.status_code)
        status_code = response.status_code
    except Exception as e:
        print("Check url: Exception = {0}".format(e))
        raise e

    return status_code, response


def parse_get_data(http_response, results):
    '''
    Analyse HTTP response data.

    @param http_response : HTTP response.
    @param results : Dict with return results.
    '''

    try:
        # There is non-printable characters for Yahoo page.
        # print("text_data =\n", ascii(http_response.text))
        # There is error while parasing yahoo page.
        # print("json_data =\n", http_response.json())

        parsed_data = BeautifulSoup(http_response.text, "lxml-xml")
        # print("parsed_data =\n", parsed_data)

        # The return object from find() is class 'bs4.element.Tag'.

        results[Constants.SECTION_CURRENCY_INFO] = {}

        try:
            currency_name_info_section = parsed_data.find(
                "h1", {"data-reactid": 7})

            if not currency_name_info_section:
                raise

            print("currency_name_info_section =", currency_name_info_section)
        except Exception:
            raise Exception("Cannot find currency_name_info_section.")

        currency_name_info = currency_name_info_section.get_text().strip()
        print("currency_name_info =", currency_name_info)
        results[Constants.SECTION_CURRENCY_INFO][Constants.CURRENCY_INFO_NAME] = currency_name_info

        currency_names = currency_name_info.split(" ")[0].split("/")
        print("currency_names =", currency_names)
        results[Constants.SECTION_CURRENCY_INFO][Constants.CURRENCY_INFO_FROM_SYMBOL] = currency_names[0]
        results[Constants.SECTION_CURRENCY_INFO][Constants.CURRENCY_INFO_TO_SYMBOL] = currency_names[1]

        results[Constants.SECTION_EXCHANGE_INFO] = {}

        try:
            value_section = parsed_data.find(
                "span", {"data-reactid": 35})

            if not value_section:
                raise

            print("value_section =", value_section)
        except Exception:
            raise Exception("Cannot find value_section.")

        results[Constants.SECTION_EXCHANGE_INFO][Constants.EXCHANGE_INFO_VALUE] = value_section.get_text().strip()

        try:
            time_section = parsed_data.find(
                "div", {"id": "quote-market-notice"})

            if not time_section:
                raise

            print("time_section =", time_section)
        except Exception:
            raise Exception("Cannot find time_section.")

        results[Constants.SECTION_EXCHANGE_INFO][Constants.EXCHANGE_INFO_TIME] = time_section.get_text()

        print("Get currency data: ok.")
        print("-" * 40)
    except Exception as e:
        print("Get currency data: Exception = {0}".format(e))
        raise e


def inspect_inventory(record):
    '''
    Inspect inventory info page.

    @param record: [currency name, from_symbol, to_symbol] 
    @return : Dict with return results.
    '''

    currency_info_from_symbol, currency_info_to_symbol = record
    currency_info_from_symbol = currency_info_from_symbol.strip()
    currency_info_to_symbol = currency_info_to_symbol.strip()
    print("currency_info_from_symbol =", currency_info_from_symbol)
    print("currency_info_to_symbol =", currency_info_to_symbol)
    currency_info_symbols = currency_info_from_symbol + currency_info_to_symbol

    results = {}
    results[currency_info_symbols] = {}
    result = results[currency_info_symbols]

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    result[Constants.START_TIME] = time_str

    result[Constants.RECORD] = {}
    result[Constants.RECORD][Constants.COLUMN_FROM_SYMBOL] = currency_info_from_symbol
    result[Constants.RECORD][Constants.COLUMN_TO_SYMBOL] = currency_info_to_symbol

    browser = None
    try:
        url = Constants.API_URL.format("{0}{1}".format(
            currency_info_from_symbol, currency_info_to_symbol))
        print("url =", url)
        result[Constants.URL] = url

        status_code, http_response = check_url(url)
        if status_code != HTTPStatus.OK:
            raise Exception("Get '{0}' failed with status code {1}.".format(url,
                                                                            status_code))

        # Parse and get data.
        result[Constants.INVENTORY_DATA] = {}
        parse_get_data(http_response, result[Constants.INVENTORY_DATA])
        print("-" * 40)

        print("Inspect currency <{0}>: ok.".format(currency_info_symbols))
        result[Constants.RESULT] = Constants.RESULT_OK
    except Exception as e:
        print("Inspect currency <{0}>: Exception = {1}".format(
            currency_info_symbols, e))
        result[Constants.RESULT] = Constants.RESULT_ERROR
        result[Constants.ERROR] = repr(e)
    finally:
        if browser:
            print("Close browser.")
            browser.close()

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)
    result[Constants.STOP_TIME] = time_str

    return results


def process_inventory_list():
    '''
    Get a list of inventory info from a config file.
    Inspect each of them.

    @return: Dict with return results.
    '''

    global __concurrent_max_workers
    global __inventory_info_file_path
    global __result_output_file_path

    results = {}

    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    results[Constants.START_TIME] = time_str

    try:
        results[Constants.INVENTORIES] = {}

        # Open input file.
        with open(__inventory_info_file_path) as record_file:
            print('record_file =', record_file)
            cin = csv.reader(record_file)
            # Get all records.
            records = [line for line in cin]
            # But not header line.
            records.pop(0)
            print("records =", records)
            results[Constants.RECORDS_NUMBER] = len(records)
        print("-" * 80)

        # Inspect inventory concurrently.
        with ThreadPoolExecutor(max_workers=__concurrent_max_workers) as executor:
            # Wait for result to return.
            for record, result in zip(records, executor.map(inspect_inventory, records)):
                results[Constants.INVENTORIES].update(result)

        print("-" * 80)
        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)
    results[Constants.STOP_TIME] = time_str

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
                json.dump(results, result_file, indent=4, sort_keys=True)
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        # Output screen as JSON format.
        print(json.dumps(results, indent=4, sort_keys=True))

    print("-" * 100)

    return results


def usage():
    print('''
Check Yahoo data.

Usage:
-h
-i <file path> [-o <file path>] -w <file path> [-l <file path>] [-c <Number>]

Options:
-h : Show help.
-i <file path> : Environment info file path (CSV). Compulsory.
-o <file path> : Result output file path (JSON). Optional, output to screen by default.
-c <Number> : Concurrent max workers to process records. Optional, 5 by default. Must >= 1

Inventory info file format sample (With header line):
currency name, from_symbol, to_symbol
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __inventory_info_file_path
    global __result_output_file_path
    global __concurrent_max_workers

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
            opts, args = getopt.getopt(argv, "hi:o:c:")
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
                    __inventory_info_file_path = arg
                elif opt == "-o":
                    __result_output_file_path = arg
                elif opt == "-c":
                    __concurrent_max_workers = int(arg)
                else:
                    __show_usage, __exit_code, __error_message = True, - \
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, - \
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("inventory_info_file_path =", __inventory_info_file_path)
    print("result_output_file_path", __result_output_file_path)
    print("concurrent_max_workers =", __concurrent_max_workers)

    # Check options are valid.
    if not __show_usage:
        if not __inventory_info_file_path:
            __show_usage, __exit_code, __error_message = True, - \
                4, "Missing compulsory command line option."
        elif __concurrent_max_workers < 1:
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -c."

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
