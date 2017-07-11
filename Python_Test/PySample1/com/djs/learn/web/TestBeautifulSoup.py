'''
Created on Apr 6, 2016

@author: dj
'''

from urllib.request import urlopen

from bs4 import BeautifulSoup
import requests


def test_requests():
    resp = requests.get(url, "html.parser")
    print("resp.status_code =", resp.status_code)

    print("-" * 40)

    print("html =", resp.text)

    print("-" * 40)

    doc = BeautifulSoup(resp.text, "html.parser")

    print("title =", doc.title)
    print("head.title =", doc.head.title)
    print("html.head.title =", doc.html.head.title)
    print("-" * 40)

    rawlinks = [element["href"] for element in doc.find_all("a")]
    links = [link for link in rawlinks if link.startswith("http")]

    for link in links:
        print(link)


def test_urllib():
    html = urlopen(url)

    doc = BeautifulSoup(html.read(), "html.parser")

    print("title =", doc.title)
    print("-" * 40)

    rawlinks = [element["href"] for element in doc.find_all("a")]
    links = [link for link in rawlinks if link.startswith("http")]

    for link in links:
        print(link)


url = "https://www.google.com"
print("url =", url)

print("-" * 60)

test_requests()

print("-" * 60)

test_urllib()

print("-" * 60)

if __name__ == '__main__':
    pass
