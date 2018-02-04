'''
@author: dj
'''

import json

import requests


getFibonacciList_Url_Get = "http://localhost:8080/WsTomcatSample2/GetFibonacciList/j/Number"
sortFibonacciList_Url_Get = "http://localhost:8080/WsTomcatSample3/SortFibonacciList/j/Number"

getFibonacciList_Url_Post = "http://localhost:8080/WsTomcatSample2/GetFibonacciList/u"
sortFibonacciList_Url_Post = "http://localhost:8080/WsTomcatSample3/SortFibonacciList/f"


def do_Get(url):
    print("url =", url)
    print("-" * 20)

    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0",
               "Accept": "application/json,text/html,application/xhtml+xml,application/xml"}
    response = requests.get(url, headers=headers)

    print("response =", response)
    print("response.status_code =", response.status_code)
    print("response.headers =\n", response.headers)
    print("-" * 40)

    # print("response.text =\n", response.text)
    # print("-" * 40)

    return response


def do_Post_1(url, number):
    print("url =", url)
    print("-" * 20)
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0",
               "Accept": "application/json,text/html,application/xhtml+xml,application/xml"}
    data = {"Number": number}
    response = requests.post(url, headers=headers, data=data)

    print("response =", response)
    print("response.status_code =", response.status_code)
    print("response.headers =\n", response.headers)
    print("-" * 40)

    # print("response.text =\n", response.text)
    # print("-" * 40)

    return response


def do_Post_2(url, number_list):
    print("url =", url)
    print("-" * 20)
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0",
               'Content-type': 'application/json',
               "Accept": "application/json,text/html,application/xhtml+xml,application/xml"}
    data = {"numberList": {"numbers": number_list}}
    response = requests.post(url, headers=headers,
                             json=json.dumps(data))

    print("response =", response)
    print("response.status_code =", response.status_code)
    print("response.headers =\n", response.headers)
    print("-" * 40)

    print("response.text =\n", response.text)
    # print("-" * 40)

    return response


def getfibonaccilist_Get_1(number):
    print("number =", number)
    url = getFibonacciList_Url_Get.replace("Number", str(number))
    response = do_Get(url)

    print("Get({0}) = {1}".format(number, response.json()))


def getfibonaccilist_Post_1(number):
    print("number =", number)
    response = do_Post_1(getFibonacciList_Url_Post, number)

    print("Get({0}) = {1}".format(number, response.json()))


def sortfibonaccilist_Get_1(number):
    print("number =", number)
    url = sortFibonacciList_Url_Get.replace("Number", str(number))
    response = do_Get(url)

    print("Get({0}) = {1}".format(number, response.json()))


def sortfibonaccilist_Post_1(number_list):
    print("number_list =", number_list)
    response = do_Post_2(sortFibonacciList_Url_Post, number_list)

    print("Get({0}) = {1}".format(number_list, response.json()))


print("-" * 60)

# getfibonaccilist_Get_1(10)

print("-" * 60)

# getfibonaccilist_Post_1(10)

print("-" * 60)

# sortfibonaccilist_Get_1(10)

print("-" * 60)

sortfibonaccilist_Post_1([1, 1, 2, 3, 5, 8, 13, 21, 34, 55])

print("-" * 60)

if __name__ == '__main__':
    pass
