'''
Created on Apr 6, 2016

@author: dj
'''

from bs4 import BeautifulSoup as soup
import requests


url = "https://www.google.com"
print("url =", url)

resp = requests.get(url)
print("resp.status_code =", resp.status_code)

print("-" * 40)

print("resp.text =", resp.text)

print("-" * 40)

page = resp.text
doc = soup(page, "html.parser")
rawlinks = [element.get('href') for element in doc.find_all('a')]
links = [link for link in rawlinks if link.startswith("http")]

for link in links:
    print(link)

print("-" * 40)

if __name__ == '__main__':
    pass
