'''
Created on July 25, 2017

Selenium Firefox depends on Geckodriver.
https://github.com/mozilla/geckodriver/releases

Selenium 3.4.x and Geckodriver >=0.16.0 require Firefox >=52.0.

@author: dj
'''

from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains

geckodriver_path = "D:\Download\Shared\geckodriver.exe"


def test_get(url, keywords):
    print("url =", url)
    print("-" * 20)

    browser = webdriver.Firefox(executable_path=geckodriver_path)
    browser.get(url)

    search_keyword_field = browser.find_element_by_name("q")
    search_keyword_field.send_keys(keywords)

    # browser.close()


url = "https://www.google.com"

print("-" * 40)

test_get(url, "napoleon bonaparte")

print("-" * 40)


if __name__ == '__main__':
    pass
