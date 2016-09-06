'''
Created on Apr 6, 2016

@author: jiangdu
'''

import requests

url = "https://www.google.com"
# url = "http://localhost:8002"
# url = "http://localhost:8002/echo/Cake"
print("url =", url)

resp = requests.get(url)
print("resp =", resp)
print("resp.status_code =", resp.status_code)

print("-" * 40)

print("resp.headers =", resp.headers)

print("-" * 40)

print("resp.text =", resp.text)

print("-" * 40)

if __name__ == '__main__':
    pass
