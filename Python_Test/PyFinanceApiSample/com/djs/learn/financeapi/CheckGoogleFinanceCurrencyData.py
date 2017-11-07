'''
Check Google finance currency data.

Update log: (date / version / author : comments)
2017-11-07 / 1.0.0 / Du Jiang : Creation

Note that:
1. This version ONLY supports Firefox.
2. It requires 3rd party python lib (at least): requests, selenium.
3. Selenium depends on Geckodriver for Firefox.
   Download: https://github.com/mozilla/geckodriver/releases
4. Selenium 3.4.x and Geckodriver >=0.16.0 requires Firefox >=52.0.
'''

from concurrent.futures import ThreadPoolExecutor
import csv
import getopt
from http import HTTPStatus
import json
import sys
from time import localtime, strftime, sleep, time

import requests
from selenium import webdriver


# Global variables.
# The value can be updated by command line options.
__currency_info_file_path = None
__result_output_file_path = None
__geckodriver_file_path = None
__geckodriver_log_file_path = None
__concurrent_max_workers = 5


class Constants(object):
    '''
    Use a class to keep constant variables.
    '''

    MAX_TRY = 3

    # Seconds.
    WAIT_TIME_LOAD_PAGE = 10
    WAIT_TIME_VIEW_PAGE = 5

    RESULT = "Result"
    ERROR = "Error"

    RESULT_OK = "Ok"
    RESULT_ERROR = "Error"

    CURRENCIES = "Currencies"
    RECORDS_NUMBER = "Records number"
    RECORD = "Record"

    START_TIME = "Start time"
    STOP_TIME = "Stop time"

    COLUMN_NAME = "Name"
    COLUMN_FROM_SYMBOL = "From symbol"
    COLUMN_TO_SYMBOL = "To symbol"

    URL = "URL"
    API_URL = "https://finance.google.com/finance?q={0}"
    STATUS_CODE = "Status code"

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

    return status_code


def get_currency_data(browser, results):
    '''
    Open URL and get data

    @param browser : Selenium handle.
    @param results : Dict with return results.
    '''

    try:
       # Appbar section.

        appbar_section = browser.find_element_by_id("appbar")

        if not appbar_section:
            raise Exception("Cannot find appbar section.")

        print("appbar_section =", appbar_section)
        results[Constants.SECTION_CURRENCY_INFO] = {}

        currency_info_name_section = appbar_section.find_element_by_class_name(
            "appbar-snippet-primary")
        if not currency_info_name_section:
            raise Exception("Cannot find currency_info_name section.")
        print("currency_info_name_section =", currency_info_name_section)

        currency_info_name = currency_info_name_section.text
        print("currency_info_name =", currency_info_name)
        results[Constants.SECTION_CURRENCY_INFO][Constants.CURRENCY_INFO_NAME] = currency_info_name

        currency_from_to_symbols_section = appbar_section.find_element_by_class_name(
            "appbar-snippet-secondary")
        if not currency_from_to_symbols_section:
            raise Exception(
                "Cannot find currency_from_to_symbols section.")
        print("currency_from_to_symbols_section =",
              currency_from_to_symbols_section)

        currency_from_to_symbols = currency_from_to_symbols_section.text
        print("currency_from_to_symbols =", currency_from_to_symbols)

        results[Constants.SECTION_CURRENCY_INFO][Constants.CURRENCY_INFO_FROM_SYMBOL] = currency_from_to_symbols[1:4]
        results[Constants.SECTION_CURRENCY_INFO][Constants.CURRENCY_INFO_TO_SYMBOL] = currency_from_to_symbols[4:-1]

        # App section.

        app_section = browser.find_element_by_id("app")

        if not app_section:
            raise Exception("Cannot find app section.")

        print("app_section =", app_section)
        results[Constants.SECTION_EXCHANGE_INFO] = {}

        value_info_section = app_section.find_element_by_id(
            "currency_value")
        if not value_info_section:
            raise Exception("Cannot find value_info section.")
        print("value_info_section =", value_info_section)

        rate_section = value_info_section.find_element_by_class_name("pr")
        if not rate_section:
            raise Exception("Cannot find rate section.")
        print("rate_section =", rate_section)
        results[Constants.SECTION_EXCHANGE_INFO][Constants.EXCHANGE_INFO_RATE] = rate_section.text
        results[Constants.SECTION_EXCHANGE_INFO][Constants.EXCHANGE_INFO_VALUE] = rate_section.text.split("=")[1].strip().split(" ")[
            0].strip()

        time_section = value_info_section.find_element_by_class_name("time")
        if not time_section:
            raise Exception("Cannot find time section.")
        print("time_section =", time_section)
        results[Constants.SECTION_EXCHANGE_INFO][Constants.EXCHANGE_INFO_TIME] = time_section.text

        print("Get currency data: ok.")
        print("-" * 40)
    except Exception as e:
        print("Get currency data: Exception = {0}".format(e))
        raise e


def inspect_currency(record):
    '''
    Inspect currency info page.

    @param record: [currency name, from_symbol, to_symbol] 
    @return : Dict with return results.
    '''

    global __geckodriver_file_path
    global __geckodriver_log_file_path

    results = {}

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    results[Constants.START_TIME] = time_str

    currency_info_from_symbol, currency_info_to_symbol = record
    currency_info_from_symbol = currency_info_from_symbol.strip()
    currency_info_to_symbol = currency_info_to_symbol.strip()
    print("currency_info_from_symbol =", currency_info_from_symbol)
    print("currency_info_to_symbol =", currency_info_to_symbol)

    results[Constants.RECORD] = {}
    results[Constants.RECORD][Constants.COLUMN_FROM_SYMBOL] = currency_info_from_symbol
    results[Constants.RECORD][Constants.COLUMN_TO_SYMBOL] = currency_info_to_symbol

    browser = None
    try:
        url = Constants.API_URL.format("{0}{1}".format(
            currency_info_from_symbol, currency_info_to_symbol))
        print("url =", url)
        results[Constants.URL] = url

        status_code = check_url(url)
        if status_code != HTTPStatus.OK:
            raise Exception("Get '{0}' failed with status code {1}.".format(url,
                                                                            status_code))

        # Create profile.
        profile = webdriver.FirefoxProfile()
        profile.set_preference("general.useragent.override",
                               "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0")
        browser = webdriver.Firefox(
            profile, executable_path=__geckodriver_file_path, log_path=__geckodriver_log_file_path)
        print("browser =", browser)
        print("-" * 60)

        for i in range(0, Constants.MAX_TRY):
            try:
                # Load page.
                browser.get(url)
                # Wait for page to be fully loaded.
                sleep(Constants.WAIT_TIME_LOAD_PAGE)
                print("-" * 40)

                # Get currency data.
                get_currency_data(browser, results)
                sleep(Constants.WAIT_TIME_VIEW_PAGE)
                print("-" * 40)

                print("Inspect currency <{0}>: ok.".format(
                    currency_info_to_symbol))
                results[Constants.RESULT] = Constants.RESULT_OK

                # It is ok, no need retry.
                break
            except Exception as e:
                print("Inspect currency <{0}:{1}>: Count {2}: Exception = {3}".format(
                    currency_info_from_symbol, currency_info_to_symbol, i, e))
                # If reach max try.
                if (i + 1) == Constants.MAX_TRY:
                    raise e
    except Exception as e:
        print("Inspect currency <{0}:{1}>: Exception = {2}".format(
            currency_info_from_symbol, currency_info_to_symbol, e))
        results[Constants.RESULT] = Constants.RESULT_ERROR
        results[Constants.ERROR] = repr(e)
    finally:
        if browser:
            sleep(Constants.WAIT_TIME_VIEW_PAGE)
            print("Close browser.")
            browser.close()

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)
    results[Constants.STOP_TIME] = time_str

    return results


def process_currency_list():
    '''
    Get a list of currency info from a config file.
    Inspect each of them.

    @return: Dict with return results.
    '''

    global __concurrent_max_workers
    global __currency_info_file_path
    global __result_output_file_path

    results = {}

    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    results[Constants.START_TIME] = time_str

    try:
        results[Constants.CURRENCIES] = {}

        # Open input file.
        with open(__currency_info_file_path) as record_file:
            print('record_file =', record_file)
            cin = csv.reader(record_file)
            # Get all records.
            records = [line for line in cin]
            # But not header line.
            records.pop(0)
            print("records =", records)
            results[Constants.RECORDS_NUMBER] = len(records)
        print("-" * 80)

        # Inspect each currency concurrently.
        with ThreadPoolExecutor(max_workers=__concurrent_max_workers) as executor:
            # Wait for result to return.
            for record, result in zip(records, executor.map(inspect_currency, records)):
                currency_info_symbols = record[0].strip(
                ) + ":" + record[1].strip()
                results[Constants.CURRENCIES][currency_info_symbols] = result

        print("-" * 80)
        print("Process currency list: ok.")
    except Exception as e:
        print("Process currency list: Exception = {0}".format(e))

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
Check Fundsupermart currency data.

Usage:
-h
-i <file path> [-o <file path>] -w <file path> [-l <file path>] [-c <Number>]

Options:
-h : Show help.
-i <file path> : Environment info file path (CSV). Compulsory.
-o <file path> : Result output file path (JSON). Optional, output to screen by default.
-w <file path> : Selenium web driver file path (absolute path). For example, geckodriver for Firefox. Compulsory.
-l <file path> : Selenium web driver log file path. Optional, output to screen by default.
-c <Number> : Concurrent max workers to process records. Optional, 5 by default. Must >= 1

Fund info file format sample (With header line):
Fund name,Fund ID

Note that:
1. This version ONLY supports Firefox.
2. It requires 3rd parth python lib (at least): requests, selenium.
3. Selenium depends on Geckodriver for Firefox.
   Download: https://github.com/mozilla/geckodriver/releases
4. Selenium 3.4.x and Geckodriver >=0.16.0 requires Firefox >=52.0.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __currency_info_file_path
    global __result_output_file_path
    global __geckodriver_file_path
    global __geckodriver_log_file_path
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
            opts, args = getopt.getopt(argv, "hi:o:w:l:c:")
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
                    __currency_info_file_path = arg
                elif opt == "-o":
                    __result_output_file_path = arg
                elif opt == "-w":
                    __geckodriver_file_path = arg
                elif opt == "-l":
                    __geckodriver_log_file_path = arg
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
    print("currency_info_file_path =", __currency_info_file_path)
    print("result_output_file_path", __result_output_file_path)
    print("geckodriver_file_path =", __geckodriver_file_path)
    print("geckodriver_log_file_path =", __geckodriver_log_file_path)
    print("concurrent_max_workers =", __concurrent_max_workers)

    # Check options are valid.
    if not __show_usage:
        if (not __currency_info_file_path) or (not __geckodriver_file_path):
            __show_usage, __exit_code, __error_message = True, - \
                4, "Missing compulsory command line option."
        elif __concurrent_max_workers < 1:
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -c."

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
