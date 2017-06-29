'''
Created on Jun 27, 2016

@author: dj
'''

from os import path
from urllib import request as urlrequest

import requests

# The default API key for demo purpose.
api_key = "demo"

input_file_path = "../../../../Temp"
api_key_file = "AlphaVantage_ApiKey.txt"
input_file = path.join(input_file_path, api_key_file)

if path.exists(input_file):
    with open(path.join(input_file_path, api_key_file)) as file:
        print("file =", file)
        # Use rstrip() to remove newline feed.
        api_key = file.readline().rstrip()
        print("api_key =", api_key)

print("-" * 60)

url_format = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={0}&apikey=" + api_key
# tickers = ["MSFT", "O87.SI"]
tickers = ["MSFT"]


for ticker in tickers:
    url = url_format.format(ticker)
    print("url =", url)

    resp = urlrequest.urlopen(url)
    print("resp =", resp)
    print("resp.status =", resp.status)
    print("resp.headers =", resp.getheaders())

    data = resp.read()
    print("data =", data)
    print("-" * 40)

print("=" * 60)

for ticker in tickers:
    url = url_format.format(ticker)
    print("url =", url)

    resp = requests.get(url)
    print("resp =", resp)
    print("resp.status_code =", resp.status_code)
    print("resp.headers =", resp.headers)
    print("resp.json =", resp.json())
    print("-" * 40)
    print("resp.text =", resp.text)
    print("-" * 40)

if __name__ == '__main__':
    pass
