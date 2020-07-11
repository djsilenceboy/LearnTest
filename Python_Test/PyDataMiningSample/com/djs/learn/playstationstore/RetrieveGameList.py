'''
Retrieve game list (by Requests).

Update log: (date / version / author : comments)
2020-07-10 / 1.0.0 / Du Jiang : Creation

Notes:
1. It requires 3rd parth python lib (at least): requests.
'''

import csv
import getopt
from http import HTTPStatus
import json
import sys
from time import localtime, strftime, time

import requests
from statsmodels.sandbox.distributions.sppatch import _fitstart_poisson

# Global variables.
# The value can be updated by command line options.
__data_type = None
__output_file_path = None

__PAGE_SIZE = 99


def check_url(url):
    '''
    Use requests to get URL, and return HTTP status code.

    @param url: A string of URL.
    @return: HTTP response status code, or None if request failed.
    @return: Parsed HTTP response, or None if request failed.
    '''

    print("url =", url)
    status_code = None
    json_data = None

    try:
        response = requests.get(url, timeout = 10)
        # print("response =", response)
        print("response.status_code =", response.status_code)

        if response.history:
            status_code = HTTPStatus.OK
            print("response.status_code (Due to redirected) =", status_code)
        else:
            status_code = response.status_code
            response.raise_for_status()

        json_data = json.loads(response.text)
    except Exception as e:
        print("Check url failed. Exception = {1}".format(e))
        raise e

    return status_code, json_data


def parse_data(json_data):
    '''
    @param json_data : JSON data.
    @return Records, a list of lists.
    @return Count of raw records.
    '''
    records = []
    game_list = json_data["included"]

    for game_info in game_list:
        if game_info["type"] == "game":
            game_details = game_info["attributes"]
            if "skus" not in game_details:
                continue
            game_skus = game_details["skus"]
            if len(game_skus) == 0:
                continue
            game_subname = game_skus[0]["name"].encode('ascii', errors = 'ignore').decode()
            game_prices = game_skus[0]["prices"]
            game_prices_plus = game_prices["plus-user"]
            game_name = game_details["name"].encode('ascii', errors = 'ignore').decode()
            platforms = "/".join(game_details["platforms"]).encode('ascii', errors = 'ignore').decode()
            genres = "/".join(game_details["genres"]).encode('ascii', errors = 'ignore').decode()

            record = [ game_name, game_subname, game_details["provider-name"], genres, platforms, game_details["release-date"],
                    game_prices_plus["actual-price"]["display"], game_prices_plus["actual-price"]["value"], game_prices_plus["discount-percentage"],
                    game_prices_plus["availability"]["start-date"] if game_prices_plus["discount-percentage"] > 0 else "",
                    game_prices_plus["availability"]["end-date"] if game_prices_plus["discount-percentage"] > 0 else "",
                    game_details["game-content-type"], game_info["id"], game_details["thumbnail-url-base"]]
            records.append(record)

    return records, len(game_list)


def process():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    url = "https://store.playstation.com/valkyrie-api/en/SG/19/container/STORE-MSF86012-GAMESALL?game_content_type=games%2Cbundles&sort=name&direction=asc"
    if __data_type == 1:
        url = url + "&platform=ps4"
    url = url + "&size={0}&start={1}"

    headers = ["Name", "SubName", "Provider", "Genres", "Platforms", "ReleaseDate",
            "DisplayPrice", "PriceValue", "DiscountPercent", "DiscountFromDate", "DiscountToDate",
            "GameContentType", "SkuId", "GamePost"]

    start_position = 0

    records = []
    while True:
        temp_url = url.format(__PAGE_SIZE, start_position)
        status_code, json_data = check_url(temp_url)
        if status_code != HTTPStatus.OK:
            raise Exception("Retrieve data failed at position {0}.".format(start_position))

        temp_records, raw_count = parse_data(json_data)
        records.extend(temp_records)
        print("Position {0}: Retrieved size = {1}".format(start_position, len(temp_records)))

        if raw_count < __PAGE_SIZE:
            break
        start_position += __PAGE_SIZE

    print("Total retrieved size =", len(records))
    print("-" * 100)

    # If given __output_file_path, output to file; otherwise, output to
    # screen.
    if __output_file_path:
        if len(records) == 0:
            raise Exception("No data retrieved.")

        try:
            # Open output file.
            with open(__output_file_path, "wt", encoding = "utf-8") as output_file:
                print('output_file =', output_file)
                # Output file as CSV format.
                cout = csv.writer(output_file, lineterminator = "\n")
                # Write header line.
                cout.writerow(headers)
                # Write record lines.
                cout.writerows(records)
            print("Output process results: ok")
        except Exception as e:
            print("Output process results: Exception = {0}".format(e))
    else:
        # Output screen as JSON format.
        print("headers =", headers)
        print("records =")
        for record in records:
            print(record)

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)


def usage():
    print('''
Retrieve game list.

Usage:
-h
-d <DataType> [-o <file path>]

Options:
-h : Show help.
-d <DataType> : Data type. Compulsory, Value [0: All, 1: PS4 only].
-o <file path> : Result output file path (CSV). Optional, output to screen by default.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __data_type
    global __output_file_path

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
            opts, args = getopt.getopt(argv, "hd:o:")
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
                elif opt == "-o":
                    __output_file_path = arg
                else:
                    __show_usage, __exit_code, __error_message = True, -\
                        2, "Unknown command line option."
        except Exception as e:
            print("Parse command options: Exception = {0}".format(e))
            __show_usage, __exit_code, __error_message = True, -\
                3, "Wrong value for command line option."

    print("show_usage =", __show_usage)
    print("data_type =", __data_type)
    print("output_file_path", __output_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."
        elif (__data_type < 0) or (__data_type > 1):
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -d."

    if not __show_usage:
        process()
    else:
        print("__exit_code =", __exit_code)
        if __error_message:
            print("__error_message =", __error_message)
        print("")
        usage()
        sys.exit(__exit_code)


if __name__ == '__main__':
    main(sys.argv[1:])
