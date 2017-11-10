'''
Check Google finance stock data.

Update log: (date / version / author : comments)
2017-11-05 / 1.0.0 / Du Jiang : Creation

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
__stock_info_file_path = None
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

    STOCKS = "Stocks"
    RECORDS_NUMBER = "Records number"
    RECORD = "Record"

    START_TIME = "Start time"
    STOP_TIME = "Stop time"

    COLUMN_NAME = "Name"
    COLUMN_EXCHANGE = "Exchange"
    COLUMN_TICKER = "Ticker"

    URL = "URL"
    API_URL = "https://finance.google.com/finance?q={0}"
    STATUS_CODE = "Status code"

    SECTION_STOCK_INFO = "Stock info"
    STOCK_INFO_NAME = "Name"
    STOCK_INFO_EXCHANGE = "Exchange"
    STOCK_INFO_TICKER = "Ticker"

    SECTION_MARKET_INFO = "Market info"
    MARKET_INFO_PRICE = "Price"
    MARKET_INFO_CURRENCY = "Currency"

    MARKET_INFO_52WEEK = "52 week"
    MARKET_INFO_52WEEK_LOW = "52 week low"
    MARKET_INFO_52WEEK_HIGH = "52 week high"

    MARKET_INFO_RANGE = "Range"
    MARKET_INFO_RANGE_LOW = "Range low"
    MARKET_INFO_RANGE_HIGH = "Range high"


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


def get_stock_data(browser, results):
    '''
    Open URL and get data

    @param browser : Selenium handle.
    @param results : Dict with return results.
    '''

    try:
       # Appbar section.

        try:
            appbar_section = browser.find_element_by_id("appbar")
            print("appbar_section =", appbar_section)
        except Exception:
            raise Exception("Cannot find appbar section.")
        results[Constants.SECTION_STOCK_INFO] = {}

        try:
            stock_info_name_section = appbar_section.find_element_by_class_name(
                "appbar-snippet-primary")
            print("stock_info_name_section =", stock_info_name_section)
        except Exception:
            raise Exception("Cannot find stock_info_name section.")

        stock_info_name = stock_info_name_section.text
        print("stock_info_name =", stock_info_name)
        results[Constants.SECTION_STOCK_INFO][Constants.STOCK_INFO_NAME] = stock_info_name

        try:
            stock_exchange_ticker_section = appbar_section.find_element_by_class_name(
                "appbar-snippet-secondary")
            print("stock_exchange_ticker_section =",
                  stock_exchange_ticker_section)
        except Exception:
            raise Exception("Cannot find stock_exchange_ticker section.")

        stock_exchange_ticker = stock_exchange_ticker_section.text
        print("stock_exchange_ticker =", stock_exchange_ticker)
        stock_exchange_ticker = stock_exchange_ticker[1:-1].split(":")
        results[Constants.SECTION_STOCK_INFO][Constants.STOCK_INFO_EXCHANGE] = stock_exchange_ticker[0]
        results[Constants.SECTION_STOCK_INFO][Constants.STOCK_INFO_TICKER] = stock_exchange_ticker[1]

        # App section.

        try:
            app_section = browser.find_element_by_id("app")
            print("app_section =", app_section)
        except Exception:
            raise Exception("Cannot find app section.")
        results[Constants.SECTION_MARKET_INFO] = {}

        try:
            price_info_section = app_section.find_element_by_id("price-panel")
            print("price_info_section =", price_info_section)
        except Exception:
            raise Exception("Cannot find price_info section.")

        try:
            price_section = price_info_section.find_element_by_class_name("pr")
            print("price_section =", price_section)
        except Exception:
            raise Exception("Cannot find price section.")
        results[Constants.SECTION_MARKET_INFO][Constants.MARKET_INFO_PRICE] = price_section.text

        try:
            currency_section = price_info_section.find_element_by_class_name(
                "mdata-dis")
            print("currency_section =", currency_section)
        except Exception:
            raise Exception("Cannot find currency section.")
        results[Constants.SECTION_MARKET_INFO][Constants.MARKET_INFO_CURRENCY] = currency_section.text[-3:]

        try:
            misc_info_section = app_section.find_element_by_class_name(
                "snap-panel-and-plusone")
            print("misc_info_section =", misc_info_section)
        except Exception:
            raise Exception("Cannot find misc_info section.")

        try:
            table_entry_sections = misc_info_section.find_elements_by_tag_name(
                "tr")
            print("table_entry_sections =", table_entry_sections)
        except Exception:
            raise Exception("Cannot find table_entry section.")

        for table_entry_section in table_entry_sections:
            print("table_entry_section =", table_entry_section.text)
            try:
                key_section = table_entry_section.find_element_by_class_name(
                    "key")
                print("key_section =", key_section.text)
                value_section = table_entry_section.find_element_by_class_name(
                    "val")
                print("value_section =", value_section.text)
            except Exception:
                raise Exception("Cannot find key/value section.")

            key = key_section.text.strip()
            value = value_section.text.strip()
            if value == "-":
                value = ""

            if key == Constants.MARKET_INFO_52WEEK:
                if value:
                    values = value.split("-")
                else:
                    values = ["", ""]
                print("values =", values)
                results[Constants.SECTION_MARKET_INFO][Constants.MARKET_INFO_52WEEK_LOW] = values[0].strip(
                )
                results[Constants.SECTION_MARKET_INFO][Constants.MARKET_INFO_52WEEK_HIGH] = values[1].strip(
                )
            elif key == Constants.MARKET_INFO_RANGE:
                if value:
                    values = value.split("-")
                else:
                    values = ["", ""]
                print("values =", values)
                results[Constants.SECTION_MARKET_INFO][Constants.MARKET_INFO_RANGE_LOW] = values[0].strip(
                )
                results[Constants.SECTION_MARKET_INFO][Constants.MARKET_INFO_RANGE_HIGH] = values[1].strip(
                )
            else:
                results[Constants.SECTION_MARKET_INFO][key] = value

        print("Get stock data: ok.")
        print("-" * 40)
    except Exception as e:
        print("Get stock data: Exception = {0}".format(e))
        raise e


def inspect_stock(record):
    '''
    Inspect stock info page.

    @param record: [stock name, exchange, ticker] 
    @return : Dict with return results.
    '''

    global __geckodriver_file_path
    global __geckodriver_log_file_path

    results = {}

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    results[Constants.START_TIME] = time_str

    stock_info_name, stock_info_exchange, stock_info_ticker = record
    stock_info_name = stock_info_name.strip()
    stock_info_exchange = stock_info_exchange.strip()
    stock_info_ticker = stock_info_ticker.strip()
    print("stock_info_name =", stock_info_name)
    print("stock_info_exchange =", stock_info_exchange)
    print("stock_info_ticker =", stock_info_ticker)

    results[Constants.RECORD] = {}
    results[Constants.RECORD][Constants.COLUMN_NAME] = stock_info_name
    results[Constants.RECORD][Constants.COLUMN_EXCHANGE] = stock_info_exchange
    results[Constants.RECORD][Constants.COLUMN_TICKER] = stock_info_ticker

    if stock_info_exchange:
        url = Constants.API_URL.format("{0}:{1}".format(
            stock_info_exchange, stock_info_ticker))
    else:
        url = Constants.API_URL.format(stock_info_ticker)
    print("url =", url)
    results[Constants.URL] = url

    browser = None
    try:
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

                # Get stock data.
                get_stock_data(browser, results)
                sleep(Constants.WAIT_TIME_VIEW_PAGE)
                print("-" * 40)

                print("Inspect stock <{0}>: ok.".format(stock_info_ticker))
                results[Constants.RESULT] = Constants.RESULT_OK

                # It is ok, no need retry.
                break
            except Exception as e:
                print("Inspect stock <{0}>: Count {1}: Exception = {2}".format(
                    stock_info_ticker, i, e))
                # If reach max try.
                if (i + 1) == Constants.MAX_TRY:
                    raise e
    except Exception as e:
        print("Inspect stock <{0}> Exception = {1}".format(
            stock_info_ticker, e))
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


def process_stock_list():
    '''
    Get a list of stock info from a config file.
    Inspect each of them.

    @return: Dict with return results.
    '''

    global __concurrent_max_workers
    global __stock_info_file_path
    global __result_output_file_path

    results = {}

    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    results[Constants.START_TIME] = time_str

    try:
        results[Constants.STOCKS] = {}

        # Open input file.
        with open(__stock_info_file_path) as record_file:
            print('record_file =', record_file)
            cin = csv.reader(record_file)
            # Get all records.
            records = [line for line in cin]
            # But not header line.
            records.pop(0)
            print("records =", records)
            results[Constants.RECORDS_NUMBER] = len(records)
        print("-" * 80)

        # Inspect each stock concurrently.
        with ThreadPoolExecutor(max_workers=__concurrent_max_workers) as executor:
            # Wait for result to return.
            for record, result in zip(records, executor.map(inspect_stock, records)):
                stock_info_ticker = record[2].strip()
                results[Constants.STOCKS][stock_info_ticker] = result

        print("-" * 80)
        print("Process stock list: ok.")
    except Exception as e:
        print("Process stock list: Exception = {0}".format(e))

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
Check Fundsupermart stock data.

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

    global __stock_info_file_path
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
                    __stock_info_file_path = arg
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
    print("stock_info_file_path =", __stock_info_file_path)
    print("result_output_file_path", __result_output_file_path)
    print("geckodriver_file_path =", __geckodriver_file_path)
    print("geckodriver_log_file_path =", __geckodriver_log_file_path)
    print("concurrent_max_workers =", __concurrent_max_workers)

    # Check options are valid.
    if not __show_usage:
        if (not __stock_info_file_path) or (not __geckodriver_file_path):
            __show_usage, __exit_code, __error_message = True, - \
                4, "Missing compulsory command line option."
        elif __concurrent_max_workers < 1:
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -c."

    if not __show_usage:
        process_stock_list()
    else:
        print("__exit_code =", __exit_code)
        if __error_message:
            print("__error_message =", __error_message)
        print("")
        usage()
        sys.exit(__exit_code)


if __name__ == '__main__':
    main(sys.argv[1:])
