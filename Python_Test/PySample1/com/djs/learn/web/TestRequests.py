'''
Created on Apr 6, 2016

https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/User-Agent/Firefox

@author: dj
'''

import requests


def test_get(url):
    print("url =", url)
    print("-" * 20)

    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0",
               "Accept": "text/html,application/xhtml+xml,application/xml"}

    response = requests.get(url, headers=headers)

    print("response =", response)
    print("response.status_code =", response.status_code)
    print("response.headers =", response.headers)

    print("-" * 20)
    print("response.text =\n", response.text)


def test_get_with_session(url):
    print("url =", url)
    session = requests.Session()
    print("session =", session)
    print("-" * 20)

    response = session.get(url)

    print("response =", response)
    print("response.status_code =", response.status_code)
    print("response.headers =", response.headers)

    # print("-" * 20)
    # print("response.text =\n", response.text)


def test_get_data(url, keywords):
    print("url =", url)

    field_search = "q"
    get_data = {field_search: keywords}
    print("get_data =", get_data)

    print("-" * 20)

    response = requests.get(url, params=get_data)

    print("response =", response)
    print("response.status_code =", response.status_code)
    print("response.headers =", response.headers)

    print("-" * 20)
    print("response.text =\n", response.text)


url = "https://www.google.com"
# url = "http://localhost:8002"
# url = "http://localhost:8002/echo/Cake"

print("-" * 40)

test_get(url)

print("-" * 40)

test_get_with_session(url)

print("-" * 40)

url = "https://www.google.com"
test_get_data(url, "napoleon bonaparte")

print("-" * 40)


if __name__ == '__main__':
    pass
