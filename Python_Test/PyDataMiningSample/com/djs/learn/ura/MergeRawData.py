'''
Merge raw data in CSV.

Update log: (date / version / author : comments)
2020-06-16 / 1.0.0 / Du Jiang : Creation
'''

import csv
import getopt
import os
import sys
from time import localtime, strftime, time

# Global variables.
# The value can be updated by command line options.
__input_folder_path = None
__input_file_prefix = None
__output_file_path = None


def getDafaFileList():
    expected_file_list = []
    full_file_list = os.listdir(__input_folder_path)
    for file_name in full_file_list:
        if file_name.startswith(__input_file_prefix) and file_name.endswith(".csv"):
            expected_file_list.append(file_name)
    print("expected_file_list =", expected_file_list)
    return expected_file_list


def process_inventory_list():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    file_list = getDafaFileList()

    headers = []
    records = []
    try:
        print("-" * 80)
        for file_name in file_list:
            file_path = os.path.join(__input_folder_path, file_name)
            print("Raw data file =", file_path)
            with open(file_path, "r") as file:
                # Read file as dict.
                reader = csv.reader(file)
                # Read header line.
                if len(headers) == 0:
                    headers = next(reader)
                else:
                    next(reader)
                # Read records into a list of list.
                temp_records = [line for line in reader]
                print("Records =", len(temp_records))
                records.extend(temp_records)

            print("Total records =", len(records))
            print("-" * 80)

        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)

    print("-" * 100)

    # If given __output_file_path, output to file; otherwise, output to
    # screen.
    if __output_file_path:
        if (len(headers) == 0) or (len(records) == 0):
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
Merge raw data in CSV.

Usage:
-h
-p <FilePath> -i <FileNamePrefix> [-o <FilePath>]

Options:
-h : Show help.
-p <FilePath> : Source data file path. Compulsory.
-i <FileNamePrefix> : Source data file name prefix (CSV). Compulsory.
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
