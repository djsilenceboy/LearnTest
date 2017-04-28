'''
Created on Apr 6, 2016

@author: dj
'''

import urllib.request as ur

# url = "https://www.google.com"
# url = "http://localhost:8002"
url = "http://localhost:8002/echo/Cake"
print("url =", url)

conn = ur.urlopen(url)
print("conn =", conn)
print("conn.status =", conn.status)
print("-" * 40)

print("Headers =", conn.getheaders())
print("Content-Type =", conn.getheader("Content-Type"))

print("-" * 40)

data = conn.read()
print("data =", data)

print("-" * 40)

if __name__ == '__main__':
    pass
