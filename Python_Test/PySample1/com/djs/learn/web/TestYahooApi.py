'''
Created on Apr 6, 2016

@author: jiangdu
'''

import requests

url_format = "http://download.finance.yahoo.com/d/quotes.csv?s={}&f=nl1kjm3m4"
tickers = ["MSFT", "O87.SI"]

for ticker in tickers:
    url = url_format.format(ticker)
    print("url =", url)

    resp = requests.get(url)
    print("resp =", resp)
    print("resp.status_code =", resp.status_code)

    print("resp.text =", resp.text.split(","))

    print("-" * 40)

if __name__ == '__main__':
    pass
