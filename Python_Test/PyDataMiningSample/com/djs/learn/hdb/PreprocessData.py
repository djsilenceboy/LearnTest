'''
Preprocess HDB merged data.

Update log: (date / version / author : comments)
2020-06-27 / 1.0.0 / Du Jiang : Creation
                                Support Transaction
'''

import csv
import getopt
import math
import sys
from time import localtime, strftime, time

# Global variables.
# The value can be updated by command line options.
__data_type = None
__input_file_path = None
__output_file_path = None


def process_inventory_list():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    headers = []
    records = []
    try:
        with open(__input_file_path, "r") as file:
            # Read file as dict.
            reader = csv.DictReader(file)
            # Read header line.
            headers = reader.fieldnames
            # Read records into a list of dict.
            records = [line for line in reader]
            print("Records =", len(records))

        keyFloorAreaFlatModel = "Floor Area (sqm) /Flat Model"
        keyResalePrice = "Resale Price"
        keyResaleRegistrationDate = "Resale Registration Date"

        keyFlatModel = "Flat Model"
        keyFloorArea = "Floor Area (sqm)"
        keyFloorAreaLower = "Floor Area Lower (sqm)"
        keyUnitPrice = "Unit Price"
        keyResaleYear = "Resale Year"

        if __data_type == 0:
            headers.append(keyFlatModel)
            headers.append(keyFloorArea)
            headers.append(keyFloorAreaLower)
            headers.append(keyUnitPrice)
            headers.append(keyResaleYear)

            for record in records:
                temp = record[keyFloorAreaFlatModel].split("/")
                record[keyFlatModel] = temp[1]
                record[keyFloorArea] = temp[0]
                record[keyFloorAreaLower] = math.floor(float(record[keyFloorArea]) / 10) * 10
                record[keyUnitPrice] = math.floor(int(record[keyResalePrice]) / float(record[keyFloorArea]))
                record[keyResaleYear] = record[keyResaleRegistrationDate][-4:]

        print("Process inventory list: ok.")
    except Exception as e:
        print("Process inventory list: Exception = {0}".format(e))

    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Stop time =", time_str)

    print("-" * 100)

    # If given __output_file_path, output to file; otherwise, output to
    # screen.
    if __output_file_path:
        try:
            # Open output file.
            with open(__output_file_path, "wt", encoding = "utf-8") as output_file:
                print('output_file =', output_file)
                # Output file as CSV format.
                cout = csv.DictWriter(
                    output_file, fieldnames = headers, lineterminator = "\n")
                # Write header line.
                cout.writeheader()
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
Preprocess HDB merged data.

Usage:
-h
-d <DataType> -i <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-d <DataType> : Raw data type. Compulsory, Value [0: Transaction].
-i <FilePath> : Source data file path (CSV). Compulsory.
-o <FilePath> : Result output file path (CSV). Optional, output to screen by default.
''')


def main(argv):
    '''
    Pass input arguments from command line to method.

    @param argv: A list of arguments
    '''

    global __data_type
    global __input_file_path
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
                    __input_file_path = arg
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
    print("input_file_path =", __input_file_path)
    print("output_file_path =", __output_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__input_file_path is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."
        elif not (__data_type in [0]):
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
