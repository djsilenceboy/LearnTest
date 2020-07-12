'''
Retrieve game list (by Requests).

Update log: (date / version / author : comments)
2020-07-11 / 1.0.0 / Du Jiang : Creation

Notes:
1. It requires 3rd parth python lib (at least): requests.
'''

import csv
import getopt
from http import HTTPStatus
import sys
from time import localtime, strftime, time, sleep

from bs4 import BeautifulSoup
from hyper import HTTPConnection

# Global variables.
# The value can be updated by command line options.
__data_type = None
__max_pages = None
__output_file_path = None

__PAGE_SIZE = 100
__RETRY_MAX = 30


def check_url(main_url, path_url):
    '''
    Use requests to get URL, and return HTTP status code.

    @param main_url: hostname with port.
    @param path_url: path url.
    @return: HTTP response status code, or None if request failed.
    @return: Parsed HTTP response, or None if request failed.
    '''

    print("main_url =", main_url)
    print("path_url =", path_url)
    status_code = None
    table_sections = None

    for i in range(0, __RETRY_MAX):
        print("Check url count = {0}".format(i))
        try:
            connection = HTTPConnection(main_url)
            connection_id = connection.request('GET', path_url)
            response = connection.get_response(connection_id)
            print("response.status =", response.status)

            status_code = response.status
            if status_code != HTTPStatus.OK:
                raise Exception("Retrieve data failed.")

            html_data = BeautifulSoup(response.read(), "html.parser")
            print("HTML title =", html_data.title)
            table_sections = html_data.find_all("table", {"class": "clamp-list condensed"})
            print("Find table_sections =", len(table_sections))
            if len(table_sections) < 4:
                raise Exception("Retrieve data failed.")

            break
        except Exception as e:
            print("Check url failed, Count = {0}, Exception = {1}".format(i, e))
            if i + 1 == __RETRY_MAX:
                raise e
            else:
                sleep((i + 1) * 10)

    return status_code, table_sections


def parse_data(table_sections):
    '''
    @param table_sections : HTML data.
    @return Records, a list of lists.
    @return Count of raw records.
    '''
    records = []

    for table_section in table_sections:
        game_items = table_section.find_all("tr")
        print("Find game_items =", len(game_items))
        for game_item in game_items:
            game_score_section = game_item.find("td", {"class": "score"})
            game_details_section = game_item.find("td", {"class": "details"})
            game_title_section = game_details_section.find("a", {"class": "title"})
            game_platform_section = game_details_section.find("div", {"class": "platform"})
            game_platform_data_section = game_platform_section.find("span", {"class": "data"})
            game_score2_section = game_details_section.find("div", {"class": "score title"})
            game_info_link = "https://www.metacritic.com" + game_title_section["href"]
            record = [game_title_section.h3.text, game_platform_data_section.text.strip(),
                      game_score_section.a.div.text, game_score2_section.div.text, game_info_link]
            records.append(record)

    return records


def process():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    platform = "all"
    if __data_type == 1:
        platform = "ps4"

    main_url = "www.metacritic.com:443"
    path_url = "/browse/games/score/metascore/all/{0}?view=condensed&page={1}"

    headers = ["Name", "Platform", "MetaScore", "UserScore", "GameInfoLink"]

    records = []
    page_number = 0
    while page_number < __max_pages:
        try:
            temp_path_url = path_url.format(platform, page_number)
            status_code, table_sections = check_url(main_url, temp_path_url)

            temp_records = parse_data(table_sections)
            records.extend(temp_records)
            print("Page {0}: Retrieved records = {1}".format(page_number, len(temp_records)))
            page_number += 1
        except Exception as e:
            print("Page {0}: Retrieved records failed. Exception = {1}".format(page_number, e))
            break

    print("Total records =", len(records))
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
-p <MaxPages> : Max pages. Compulsory, Value [1, ).
-o <file path> : Result output file path (CSV). Optional, output to screen by default.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __data_type
    global __max_pages
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
            opts, args = getopt.getopt(argv, "hd:p:o:")
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
                elif opt == "-p":
                    __max_pages = int(arg)
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
    print("__max_pages =", __max_pages)
    print("output_file_path", __output_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__max_pages is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."
        elif (__data_type < 0) or (__data_type > 1):
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -d."
        elif __max_pages <= 0:
            __show_usage, __exit_code, __error_message = True, -5, "Wrong value for -p."

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
