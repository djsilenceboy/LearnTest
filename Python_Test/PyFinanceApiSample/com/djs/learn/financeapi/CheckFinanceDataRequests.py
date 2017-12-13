'''
Check finance data by Requests.

Update log: (date / version / author : comments)
2017-12-08 / 1.0.0 / Du Jiang : Creation
                                Support Yahoo Finance stock
2017-12-10 / 1.1.0 / Du Jiang : Support Yahoo Finance currency
2017-12-13 / 2.0.0 / Du Jiang : Combined support Yahoo Finance stock / currency.
                                
Notes:
1. It requires 3rd parth python lib (at least): requests.
2. When using Selenium, the webdriver.Firefox.get() does not return, always timeout.
3. Those page load detection methods are all not working. 
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
__data_type = None
__inventory_info_file_path = None
__result_output_file_path = None
__concurrent_max_workers = 5

__Constants = None


class Constants_Base(object):
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
    STATUS_CODE = "Status code"


class Constants_YahooStock(Constants_Base):
    API_URL = "https://finance.yahoo.com/quote/{0}"

    COLUMN_NAME = "Name"
    COLUMN_EXCHANGE = "Exchange"
    COLUMN_TICKER = "Ticker"

    SECTION_STOCK_INFO = "Stock info"
    STOCK_INFO_NAME = "Name"
    STOCK_INFO_EXCHANGE = "Exchange"
    STOCK_INFO_TICKER = "Ticker"

    SECTION_MARKET_INFO = "Market info"
    MARKET_INFO_PRICE = "Price"
    MARKET_INFO_CURRENCY = "Currency"

    MARKET_INFO_52WEEK = "52 Week Range"
    MARKET_INFO_52WEEK_LOW = "52 week low"
    MARKET_INFO_52WEEK_HIGH = "52 week high"

    MARKET_INFO_RANGE = "Day's Range"
    MARKET_INFO_RANGE_LOW = "Range low"
    MARKET_INFO_RANGE_HIGH = "Range high"

    MARKET_INFO_DIVIDEND_YIELD = "Forward Dividend  Yield"
    MARKET_INFO_DIVIDEND = "Forward Dividend"
    MARKET_INFO_YIELD = "Yield"


class Constants_YahooCurrency(Constants_Base):
    API_URL = "https://finance.yahoo.com/quote/{0}=X"

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
    response = None

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


def parse_get_data_yahoo_stock(http_response, results):
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

        results[__Constants.SECTION_STOCK_INFO] = {}

        # The return object from find() is class 'bs4.element.Tag'.

        try:
            stock_name_info_section = parsed_data.find(
                "h1", {"data-reactid": 7})

            if not stock_name_info_section:
                raise

            print("stock_name_info_section =", stock_name_info_section)
        except Exception:
            raise Exception("Cannot find stock_name_info_section.")

        stock_name_info = stock_name_info_section.get_text().strip().rsplit(" ", 1)
        print("stock_name_info =", stock_name_info)
        results[__Constants.SECTION_STOCK_INFO][__Constants.STOCK_INFO_NAME] = stock_name_info[0]
        ticker_exchange_info = stock_name_info[1][1:-1].split(".")
        print("ticker_exchange_info =", ticker_exchange_info)
        results[__Constants.SECTION_STOCK_INFO][__Constants.STOCK_INFO_TICKER] = ticker_exchange_info[0]
        if len(ticker_exchange_info) > 1:
            results[__Constants.SECTION_STOCK_INFO][__Constants.STOCK_INFO_EXCHANGE] = ticker_exchange_info[1]
        else:
            results[__Constants.SECTION_STOCK_INFO][__Constants.STOCK_INFO_EXCHANGE] = ""

        results[__Constants.SECTION_MARKET_INFO] = {}

        try:
            currency_section = parsed_data.find(
                "span", {"data-reactid": 9})

            if not currency_section:
                raise

            print("currency_section =", currency_section)
        except Exception:
            raise Exception("Cannot find currency_section.")

        results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_CURRENCY] = currency_section.get_text(
        ).strip()[-3:]

        try:
            price_section = parsed_data.find(
                "span", {"data-reactid": 35})

            if not price_section:
                raise

            print("price_section =", price_section)
        except Exception:
            raise Exception("Cannot find price_section.")

        results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_PRICE] = price_section.get_text(
        ).strip()

        try:
            misc_info_section = parsed_data.find(
                "div", {"id": "quote-summary"})

            if not misc_info_section:
                raise

            # print("misc_info_section =", misc_info_section)
        except Exception:
            raise Exception("Cannot find misc_info_section.")

        try:
            table_entry_sections = misc_info_section.find_all("tr")

            if not table_entry_sections:
                raise

            # print("table_entry_sections =", table_entry_sections)
            print("table_entry_sections[] =", len(table_entry_sections))
        except Exception:
            raise Exception("Cannot find table_entry_sections.")

        for table_entry_section in table_entry_sections:
            # print("table_entry_section =", table_entry_section.get_text())

            try:
                key_value_sections = table_entry_section.find_all("td")
                # print("key_value_sections[] =", len(key_value_sections))

                if key_value_sections:
                    key = key_value_sections[0].get_text().strip()
                    value = None

                    if len(key_value_sections) > 1:
                        value = key_value_sections[1].get_text().strip()
                        if (value == "-") or (value.startswith("N/A")):
                            value = ""

                        print("key, value = {0}, {1}".format(key, value))

                        if key == __Constants.MARKET_INFO_52WEEK:
                            if value:
                                values = value.split("-")
                            else:
                                values = ["", ""]
                            print("values =", values)
                            results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_52WEEK_LOW] = values[0].strip(
                            )
                            results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_52WEEK_HIGH] = values[1].strip(
                            )
                        elif key == __Constants.MARKET_INFO_RANGE:
                            if value:
                                values = value.split("-")
                            else:
                                values = ["", ""]
                            print("values =", values)
                            results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_RANGE_LOW] = values[0].strip(
                            )
                            results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_RANGE_HIGH] = values[1].strip(
                            )
                        elif key == __Constants.MARKET_INFO_DIVIDEND_YIELD:
                            if value:
                                values = value.split(" ")
                            else:
                                values = ["", ""]
                            results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_DIVIDEND] = values[0].strip(
                            )
                            results[__Constants.SECTION_MARKET_INFO][__Constants.MARKET_INFO_YIELD] = values[1].strip(
                            )[1:-1]
                        else:
                            results[__Constants.SECTION_MARKET_INFO][key] = value
            except Exception:
                raise Exception("Cannot find key_value_sections.")

        print("Get stock data: ok.")
        print("-" * 40)
    except Exception as e:
        print("Get stock data: Exception = {0}".format(e))
        raise e


def parse_get_data_yahoo_currency(http_response, results):
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

        results[__Constants.SECTION_CURRENCY_INFO] = {}

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
        results[__Constants.SECTION_CURRENCY_INFO][__Constants.CURRENCY_INFO_NAME] = currency_name_info

        currency_names = currency_name_info.split(" ")[0].split("/")
        print("currency_names =", currency_names)
        results[__Constants.SECTION_CURRENCY_INFO][__Constants.CURRENCY_INFO_FROM_SYMBOL] = currency_names[0]
        results[__Constants.SECTION_CURRENCY_INFO][__Constants.CURRENCY_INFO_TO_SYMBOL] = currency_names[1]

        results[__Constants.SECTION_EXCHANGE_INFO] = {}

        try:
            value_section = parsed_data.find(
                "span", {"data-reactid": 35})

            if not value_section:
                raise

            print("value_section =", value_section)
        except Exception:
            raise Exception("Cannot find value_section.")

        results[__Constants.SECTION_EXCHANGE_INFO][__Constants.EXCHANGE_INFO_VALUE] = value_section.get_text().strip()

        try:
            time_section = parsed_data.find(
                "div", {"id": "quote-market-notice"})

            if not time_section:
                raise

            print("time_section =", time_section)
        except Exception:
            raise Exception("Cannot find time_section.")

        results[__Constants.SECTION_EXCHANGE_INFO][__Constants.EXCHANGE_INFO_TIME] = time_section.get_text()

        print("Get currency data: ok.")
        print("-" * 40)
    except Exception as e:
        print("Get currency data: Exception = {0}".format(e))
        raise e


def inspect_inventory(record):
    '''
    Inspect inventory info page.

    @param record: [Stock name, Exchange, Ticker], [Currency from_symbol, To symbol] 
    @return : Dict with return results.
    '''

    results = {}

    if __data_type == 0:
        parse_get_data = parse_get_data_yahoo_stock

        stock_info_name, stock_info_exchange, stock_info_ticker = record
        stock_info_name = stock_info_name.strip()
        stock_info_exchange = stock_info_exchange.strip()
        stock_info_ticker = stock_info_ticker.strip()
        print("stock_info_name =", stock_info_name)
        print("stock_info_exchange =", stock_info_exchange)
        print("stock_info_ticker =", stock_info_ticker)
        inventory_id = stock_info_ticker

        results[stock_info_ticker] = {}
        result = results[stock_info_ticker]

        result[__Constants.RECORD] = {}
        result[__Constants.RECORD][__Constants.COLUMN_NAME] = stock_info_name
        result[__Constants.RECORD][__Constants.COLUMN_EXCHANGE] = stock_info_exchange
        result[__Constants.RECORD][__Constants.COLUMN_TICKER] = stock_info_ticker

        if stock_info_exchange:
            url = __Constants.API_URL.format("{0}.{1}".format(
                stock_info_ticker, stock_info_exchange))
        else:
            url = __Constants.API_URL.format(stock_info_ticker)
    else:  # __data_type == 1:
        parse_get_data = parse_get_data_yahoo_currency

        currency_info_from_symbol, currency_info_to_symbol = record
        currency_info_from_symbol = currency_info_from_symbol.strip()
        currency_info_to_symbol = currency_info_to_symbol.strip()
        print("currency_info_from_symbol =", currency_info_from_symbol)
        print("currency_info_to_symbol =", currency_info_to_symbol)
        currency_info_symbols = currency_info_from_symbol + currency_info_to_symbol
        inventory_id = currency_info_symbols

        results[currency_info_symbols] = {}
        result = results[currency_info_symbols]

        result[__Constants.RECORD] = {}
        result[__Constants.RECORD][__Constants.COLUMN_FROM_SYMBOL] = currency_info_from_symbol
        result[__Constants.RECORD][__Constants.COLUMN_TO_SYMBOL] = currency_info_to_symbol

        url = __Constants.API_URL.format(currency_info_symbols)

    print("url =", url)
    result[__Constants.URL] = url

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)
    result[__Constants.START_TIME] = time_str

    browser = None
    try:
        status_code, http_response = check_url(url)
        if status_code != HTTPStatus.OK:
            raise Exception("Get '{0}' failed with status code {1}.".format(url,
                                                                            status_code))

        # Parse and get data.
        result[__Constants.INVENTORY_DATA] = {}
        parse_get_data(http_response, result[__Constants.INVENTORY_DATA])
        print("-" * 40)

        print("Inspect inventory <{0}>: ok.".format(inventory_id))
        result[__Constants.RESULT] = __Constants.RESULT_OK
    except Exception as e:
        print("Inspect inventory <{0}> Exception = {1}".format(
            inventory_id, e))
        result[__Constants.RESULT] = __Constants.RESULT_ERROR
        result[__Constants.ERROR] = repr(e)
    finally:
        if browser:
            print("Close browser.")
            browser.close()

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
    else:  # __data_type == 1:
        __Constants = Constants_YahooCurrency

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
        with ThreadPoolExecutor(max_workers=__concurrent_max_workers) as executor:
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
Check finance data by Requests.

Usage:
-h
-d <DataType> -i <file path> [-o <file path>] [-c <Number>]

Options:
-h : Show help.
-d <DataType> : Finance data type. Compulsory, Value [0: Yahoo Finance stock, 1: Yahoo Finance currency].
-i <file path> : Environment info file path (CSV). Compulsory.
-o <file path> : Result output file path (JSON). Optional, output to screen by default.
-c <Number> : Concurrent max workers to process records. Optional, Value [1, 10], 5 by default.

Notes:
Inventory info file format sample (With header line):
1. Google stock 
Name,Exchange,Ticker
2. Google currency
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
            opts, args = getopt.getopt(argv, "hd:i:o:c:")
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
                else:
                    __show_usage, __exit_code, __error_message = True, - \
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, - \
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("data_type =", __data_type)
    print("inventory_info_file_path =", __inventory_info_file_path)
    print("result_output_file_path", __result_output_file_path)
    print("concurrent_max_workers =", __concurrent_max_workers)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__inventory_info_file_path is None):
            __show_usage, __exit_code, __error_message = True, - \
                4, "Missing compulsory command line option."
        elif (__data_type < 0) or (__data_type > 2):
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -d."
        elif (__concurrent_max_workers < 1) or (__concurrent_max_workers > 10):
            __show_usage, __exit_code, __error_message = True, -6, "Wrong value for -c."

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
