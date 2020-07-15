'''
Convert table data from html to CSV.

Update log: (date / version / author : comments)
2020-07-12 / 1.0.0 / Du Jiang : Creation
'''

import csv
import getopt
from operator import itemgetter
import os
import sys
from time import localtime, strftime, time

from bs4 import BeautifulSoup

# Global variables.
# The value can be updated by command line options.
__input_folder_path = None
__input_file_prefix = None
__output_file_path = None


def getDafaFileList():
    expected_file_list = []
    full_file_list = os.listdir(__input_folder_path)
    for file_name in full_file_list:
        if file_name.startswith(__input_file_prefix) and file_name.endswith(".html"):
            expected_file_list.append(file_name)
    print("expected_file_list =", expected_file_list)
    return expected_file_list


def process_inventory_list():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    file_list = getDafaFileList()

    headers = ["Name", "Platform", "MetaScore", "UserScore", "GameInfoLink", "NormalizedName"]
    records = []
    try:
        print("-" * 80)
        for file_name in file_list:
            file_path = os.path.join(__input_folder_path, file_name)
            print("HTML data file =", file_path)
            with open(file_path, "rb") as file:
                html_data = BeautifulSoup(file.read(), "html.parser")
            print("HTML title =", html_data.title)

            temp_records = []
            table_sections = html_data.find_all("table", {"class": "clamp-list condensed"})
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
                    game_name = game_title_section.h3.text.encode('ascii', errors = 'ignore').decode()
                    game_name_normalized = game_name + " "
                    game_score = game_score_section.a.div.text.replace("tbd", "").strip()
                    game_score2 = game_score2_section.div.text.replace("tbd", "").strip()
                    game_info_link = "https://www.metacritic.com" + game_title_section["href"]
                    record = [game_name, game_platform_data_section.text.strip(),
                              game_score, game_score2, game_info_link, game_name_normalized]
                    temp_records.append(record)

            print("Records from file =", len(temp_records))
            records.extend(temp_records)
            print("Total records =", len(records))
            print("-" * 80)

        print("Total records =", len(records))
        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)

    print("-" * 100)

    print("Sort records.")
    records.sort(key = itemgetter(2, 3), reverse = True)

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

    print("-" * 100)


def usage():
    print('''
Convert table data from html to CSV.

Usage:
-h
-p <FilePath> -i <FileNamePrefix> [-o <FilePath>]

Options:
-h : Show help.
-p <FilePath> : Source data file path. Compulsory.
-i <FileNamePrefix> : Source data file name prefix (HTML). Compulsory.
-o <FilePath> : Result output file path (CSV). Optional, output to screen by default.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __input_folder_path
    global __input_file_prefix
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
            opts, args = getopt.getopt(argv, "hf:i:o:")
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
                elif opt == "-f":
                    __input_folder_path = arg
                elif opt == "-i":
                    __input_file_prefix = arg
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
    print("input_folder_path =", __input_folder_path)
    print("input_file_prefix =", __input_file_prefix)
    print("output_file_path =", __output_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__input_folder_path is None) or (__input_file_prefix is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."

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
