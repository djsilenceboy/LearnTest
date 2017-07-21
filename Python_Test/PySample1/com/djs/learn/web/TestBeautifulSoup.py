'''
Created on Apr 6, 2016

@author: dj
'''

from urllib.request import urlopen

from bs4 import BeautifulSoup
import requests


def requests_html(url):
    print("url =", url)

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


def urllib_html(url):
    print("url =", url)

    html = urlopen(url)

    doc = BeautifulSoup(html.read(), "html.parser")

    print("title =", doc.title)
    print("-" * 40)

    rawlinks = [element["href"] for element in doc.find_all("a")]
    links = [link for link in rawlinks if link.startswith("http")]

    for link in links:
        print(link)


def requests_xml(url):
    print("url =", url)

    resp = requests.get(url, "lxml-xml")
    print("resp.status_code =", resp.status_code)

    doc = BeautifulSoup(resp.text, "lxml-xml")

    for item in doc.findAll("TITLE"):
        print(item.text)


def urllib_xml(url):
    print("url =", url)

    xml = urlopen(url)

    doc = BeautifulSoup(xml.read(), "lxml-xml")

    for item in doc.findAll("TITLE"):
        print(item.text)


url = "https://www.google.com"

print("-" * 60)

requests_html(url)

print("-" * 60)

urllib_html(url)

print("-" * 60)

url = "https://www.w3schools.com/xml/cd_catalog.xml"

requests_xml(url)

print("-" * 60)

urllib_xml(url)

print("-" * 60)

if __name__ == '__main__':
    pass
