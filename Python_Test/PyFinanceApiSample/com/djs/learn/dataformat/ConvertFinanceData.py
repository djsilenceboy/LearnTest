'''
Convert finance data: From JSON to CSV.

Update log: (date / version / author : comments)
2017-08-13 / 1.0.0 / Du Jiang : Creation
                                Support Fundsupermart fund
2017-11-07 / 1.0.0 / Du Jiang : Support Google Finance currency
2017-08-13 / 1.0.0 / Du Jiang : Support Google Finance stock
2017-12-10 / 1.0.0 / Du Jiang : Support Yahoo Finance stock
                                Support Yahoo Finance currency
2017-12-13 / 2.0.0 / Du Jiang : Combined support Fundsupermart fund and Google Finance stock / currency.
2018-07-01 / 3.0.0 / Du Jiang : Remove Google Finance (Deprecated)
                                Support XE currency
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


class Constants_FundsupermartFund(Constants_Base):
    FUND_ID = "Fund ID"
    FUND_NAME = "Fund name"

    SECTION_BANNER_INFO = "Banner info"
    SECTION_OFFER_TO_BID_INFO = "Offer to bid info"
    SECTION_BID_TO_OFFER_INFO = "Bid to offer info"
    SECTION_HISTORICAL_PRICE_INFO = "Historical price info"
    SECTION_RELEVANT_CHARGES = "Relevant charges"

    OFFER_TO_BID_FILTER = ["1 YR", "10 YR", "3 YR", "5 YR"]
    HISTORICAL_PRICE_FILTER = ["1 YR High", "1 YR Low"]
    RELEVANT_CHARGES_FILTER = [
        "Annual Expense Ratio", "Annual Management Charge"]


class Constants_YahooStock(Constants_Base):
    STOCK_INFO = "Stock info"
    STOCK_INFO_NAME = "Name"
    STOCK_INFO_EXCHANGE = "Exchange"
    STOCK_INFO_TICKER = "Ticker"

    MARKET_INFO = "Market info"

    MARKET_INFO_FIELD_PE_OLD = "PE Ratio (TTM)"
    MARKET_INFO_FIELD_PE_NEW = "P/E"

    MARKET_INFO_FIELD_BETA_OLD = "Beta (5Y Monthly)"
    MARKET_INFO_FIELD_BETA_NEW = "Beta"

    MARKET_INFO_FILTER = ["52 week high", "52 week low",
                          MARKET_INFO_FIELD_BETA_NEW, MARKET_INFO_FIELD_BETA_OLD, "Currency", MARKET_INFO_FIELD_PE_OLD, "Price"]


class Constants_Currency(Constants_Base):
    CURRENCY_INFO = "Currency info"
    CURRENCY_INFO_FROM_SYMBOL = "From symbol"
    CURRENCY_INFO_TO_SYMBOL = "To symbol"

    EXCHANGE_INFO = "Exchange info"
    EXCHANGE_INFO_VALUE = "Value"


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
                input_file, object_pairs_hook = OrderedDict)
            print('inventory_data =', inventory_data)

        print("-" * 80)

        if __data_type == 0:
            __Constants = Constants_FundsupermartFund
        elif __data_type == 2:
            __Constants = Constants_YahooStock
        else:  # __data_type == 3:
            __Constants = Constants_Currency

        add_field_name = True
        for item, record_value in inventory_data[__Constants.INVENTORIES].items():
            print("item =", item)

            if record_value[__Constants.RESULT] == __Constants.RESULT_ERROR:
                print("Result =", record_value[__Constants.RESULT_ERROR])
                print("-" * 60)
                continue

            record = OrderedDict()

            if __data_type == 0:
                record[__Constants.FUND_NAME] = record_value[__Constants.RECORD][__Constants.FUND_NAME]
                record[__Constants.FUND_ID] = record_value[__Constants.RECORD][__Constants.FUND_ID]

                if add_field_name:
                    field_names.append(__Constants.FUND_NAME)
                    field_names.append(__Constants.FUND_ID)

                # Following iteration must be sorted for adding field names in
                # correct order.

                for item_key, item_value in sorted(record_value[__Constants.INVENTORY_DATA][__Constants.SECTION_BANNER_INFO].items()):
                    if item_key == "Latest NAV Price":
                        item_values = item_value.split(" ")
                        if len(item_values) > 1:
                            item_value = item_values[1].strip()
                        else:
                            item_value = 0

                    record[item_key] = item_value
                    if add_field_name:
                        field_names.append(item_key)

                for item_key, item_value in sorted(record_value[__Constants.INVENTORY_DATA][__Constants.SECTION_OFFER_TO_BID_INFO].items()):
                    if item_key in __Constants.OFFER_TO_BID_FILTER:
                        if item_value == "-":
                            item_value = ""
                        record[item_key] = item_value
                        if add_field_name:
                            field_names.append(item_key)

                for item_key, item_value in sorted(record_value[__Constants.INVENTORY_DATA][__Constants.SECTION_HISTORICAL_PRICE_INFO].items()):
                    if item_key in __Constants.HISTORICAL_PRICE_FILTER:
                        if item_value == "-":
                            item_value = ""
                        record[item_key] = item_value
                        if add_field_name:
                            field_names.append(item_key)

                for item_key, item_value in sorted(record_value[__Constants.INVENTORY_DATA][__Constants.SECTION_RELEVANT_CHARGES].items()):
                    if item_key in __Constants.RELEVANT_CHARGES_FILTER:
                        if item_value == "-":
                            item_value = ""
                        record[item_key] = item_value
                        if add_field_name:
                            field_names.append(item_key)
            elif __data_type == 2:
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
                        if item_key == __Constants.MARKET_INFO_FIELD_PE_OLD:
                            item_key = __Constants.MARKET_INFO_FIELD_PE_NEW
                        elif item_key == __Constants.MARKET_INFO_FIELD_BETA_OLD:
                            item_key = __Constants.MARKET_INFO_FIELD_BETA_NEW
                        print(item_key, "=", item_value)
                        record[item_key] = item_value
                        if add_field_name:
                            field_names.append(item_key)
            else:  # __data_type == 3:
                record[__Constants.CURRENCY_INFO_FROM_SYMBOL] = record_value[__Constants.INVENTORY_DATA][
                    __Constants.CURRENCY_INFO][__Constants.CURRENCY_INFO_FROM_SYMBOL]
                record[__Constants.CURRENCY_INFO_TO_SYMBOL] = record_value[__Constants.INVENTORY_DATA][
                    __Constants.CURRENCY_INFO][__Constants.CURRENCY_INFO_TO_SYMBOL]
                record[__Constants.EXCHANGE_INFO_VALUE] = record_value[__Constants.INVENTORY_DATA][
                    __Constants.EXCHANGE_INFO][__Constants.EXCHANGE_INFO_VALUE]

                if add_field_name:
                    field_names.append(__Constants.CURRENCY_INFO_FROM_SYMBOL)
                    field_names.append(__Constants.CURRENCY_INFO_TO_SYMBOL)
                    field_names.append(__Constants.EXCHANGE_INFO_VALUE)

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
            with open(__csv_file_path, "wt", encoding = "utf-8") as output_file:
                print('output_file =', output_file)
                # Output file as CSV format.
                cout = csv.DictWriter(
                    output_file, fieldnames = field_names, lineterminator = "\n")
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
Convert finance data: From JSON to CSV.

Usage:
-h
-d <DataType> -i <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-d <DataType> : Finance data type. Compulsory, Value [0: Fundsupermart fund, 2: Yahoo Finance stock, 3: Currency].
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
                    __show_usage, __exit_code, __error_message = True, -\
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, -\
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("data_type =", __data_type)
    print("json_file_path =", __json_file_path)
    print("csv_file_path", __csv_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__json_file_path is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."
        elif not (__data_type in [0, 2, 3]):
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
