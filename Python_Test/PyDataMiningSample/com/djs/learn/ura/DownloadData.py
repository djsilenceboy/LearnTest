'''
Download URA data.

Update log: (date / version / author : comments)
2020-06-10 / 1.0.0 / Du Jiang : Creation
                                Support Transaction and Rental data
                                Currently, failed for requests. But API is working by curl.
'''

import csv
import getopt
import logging
import sys
from time import localtime, strftime, time
from urllib import parse

import requests

import http.client as http_client

# Global variables.
# The value can be updated by command line options.
__data_type = None
__output_file_path = None

__template_login_url = "https://www.ura.gov.sg/realEstateIIWeb/{0}/search.action"
__template_sqf_url = "https://www.ura.gov.sg/realEstateIIWeb/{0}/submitSearch.action;jsessionid={1}"
__template_sqm_url = "https://www.ura.gov.sg/realEstateIIWeb/{0}/changeDisplayUnit.action"

__sample_form_data = {"submissionType": "pd",
                      "selectedFromPeriodProjectName": "JUN 2017",
                      "selectedToPeriodProjectName": "MAY 2020",
                      "__multiselect_selectedProjects1": "",
                      "selectedFromPeriodPostalDistrict": "JAN 2020",
                      "selectedToPeriodPostalDistrict": "MAY 2020",
                      "propertyType": "ac",
                      "saleTypePD": 3,
                      "postalDistrictList": 28,
                      "selectedPostalDistricts1": "05",
                      "__multiselect_selectedPostalDistricts1": ""}

http_client.HTTPConnection.debuglevel = 1
logging.basicConfig()
logging.getLogger().setLevel(logging.DEBUG)
requests_log = logging.getLogger("requests.packages.urllib3")
requests_log.setLevel(logging.DEBUG)
requests_log.propagate = True


def get_jsessionid(url):
    jSessionID = None
    print("url =", url)
    response = requests.get(url)
    if (response.status_code == 200):
        if (response.headers is not None):
            set_cookie = response.headers["Set-Cookie"]
            if (set_cookie is not None):
                cookies = set_cookie.split(";")
                for cookie in cookies:
                    if cookie.find("JSESSIONID") >= 0:
                        jSessionID = cookie.split("=")[1]
                        print("jSessionID =", jSessionID)
                        break
    if jSessionID is None:
        raise Exception("No JSESSIONID returned from login")
    return jSessionID


def get_sqf_data(url, http_headers, form_data):
    print("url =", url)
    print("http_headers =", http_headers)
    encoded_form_data = parse.urlencode(form_data)
    print("encoded_form_data =", encoded_form_data)

    response = requests.post(url, headers = http_headers, data = encoded_form_data)
    print("response =", response)
    print("response.headers =", response.headers)
    if (response.text.find("Missing parameters in search query") >= 0):
        print("Missing parameters in search query")


def download_transaction_data():
    headers = []
    records = []

    login_url = __template_login_url.format("transaction")
    jSessionID = get_jsessionid(login_url)

    sqf_url = __template_sqf_url.format("transaction", jSessionID)
    http_headers = {"Cookie": "JSESSIONID={0}".format(jSessionID),
                    "Content-Type": "application/x-www-form-urlencoded",
                    # "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
                    "Accept": "text/html,application/xhtml+xml,application/xml",
                    "Accept-Encoding": "gzip, deflate, br"}
    get_sqf_data(sqf_url, http_headers, __sample_form_data)

    return headers, records


def download_rental_data():
    headers = []
    records = []
    login_url = __template_login_url.format("rental")
    return headers, records


def process_inventory_list():
    print("-" * 100)
    time_str = strftime("%Y-%m-%d %H:%M:%S", localtime(time()))
    print("Start time =", time_str)

    headers = []
    records = []

    try:

        if __data_type == 0:
            headers, records = download_transaction_data()
        else:  # __data_type == 1:
            headers, records = download_transaction_data()
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
Preprocess URA merged data.

Usage:
-h
-d <DataType> -i <FilePath> [-o <FilePath>]

Options:
-h : Show help.
-d <DataType> : Raw data type. Compulsory, Value [0: Transaction, 1: Rental].
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
    print("output_file_path =", __output_file_path)

    # Check options are valid.
    if not __show_usage:
        if (__data_type is None):
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
