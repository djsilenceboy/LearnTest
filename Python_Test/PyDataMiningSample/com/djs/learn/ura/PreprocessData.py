'''
Preprocess URA merged data.

Update log: (date / version / author : comments)
2020-06-06 / 1.0.0 / Du Jiang : Creation
                                Support Transaction and Rental data
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

        headers.remove("S/N")
        for record in records:
            del record["S/N"]

        if __data_type == 0:
            keyTenureYear = "Tenure Year"
            keyFloorAreaLower = "Floor Area Lower (Sqm)"
            keyFloorAreaUpper = "Floor Area Upper (Sqm)"

            headers.append(keyTenureYear)
            headers.append(keyFloorAreaLower)
            headers.append(keyFloorAreaUpper)

            for record in records:
                if record["Tenure"] == "Freehold":
                    tenureYear = "0"
                else:
                    tenureYear = record["Tenure"][-4:]
                record[keyTenureYear] = tenureYear
                record[keyFloorAreaLower] = math.floor(int(record["Area (Sqm)"]) / 10) * 10
                record[keyFloorAreaUpper] = math.ceil(int(record["Area (Sqm)"]) / 10) * 10
        else:  # __data_type == 1:
            keyYearlyGrossRent = "Yearly Gross Rent($)"
            keyFloorAreaLower = "Floor Area Lower (sq m)"
            keyFloorAreaUpper = "Floor Area Upper (sq m)"

            headers.append(keyYearlyGrossRent)
            headers.append(keyFloorAreaLower)
            headers.append(keyFloorAreaUpper)

            for record in records:
                record[keyYearlyGrossRent] = int(record["Monthly Gross Rent($)"]) * 12
                floorAreaSqm = record["Floor Area (sq m)"]
                if floorAreaSqm[:1] == ">":
                    record[keyFloorAreaLower] = floorAreaSqm[1:]
                    record[keyFloorAreaUpper] = floorAreaSqm[1:]
                else:
                    midIndex = floorAreaSqm.find("to") - 1
                    record[keyFloorAreaLower] = floorAreaSqm[:midIndex]
                    midIndex = midIndex + 4
                    record[keyFloorAreaUpper] = floorAreaSqm[midIndex:]
                # Floor Area Lower (sq m) =IF(LEFT(G2,1) <> ">", LEFT(G2, FIND("to", G2) -1), MID(G2,2,LEN(G2)))
                # Floor Area Upper (sq m) =IF(LEFT(G2,1) <> ">", RIGHT(G2, LEN(G2) - FIND("to", G2) - 2), MID(G2,2,LEN(G2)))

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
Preprocess URA merged data.

Usage:
-h
-d <DataType> -i <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-d <DataType> : Raw data type. Compulsory, Value [0: Transaction, 1: Rental].
-i <FilePath> : Environment info file path (CSV). Compulsory.
-o <FilePath> : Result output file path (JSON). Optional, output to screen by default.
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
    print("output_file_path", __output_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None) or (__input_file_path is None):
            __show_usage, __exit_code, __error_message = True, -\
                4, "Missing compulsory command line option."
        elif not (__data_type in [0, 1]):
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
