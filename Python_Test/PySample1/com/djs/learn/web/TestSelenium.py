'''
Created on July 25, 2017

Selenium Firefox depends on Geckodriver.
https://github.com/mozilla/geckodriver/releases

Selenium 3.4.x and Geckodriver >=0.16.0 require Firefox >=52.0.

@author: dj
'''

from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys

# The path must be absolute path.
geckodriver_path = "D:\Download\Shared\geckodriver.exe"


def test_get(url, keywords):
    print("url =", url)
    print("-" * 20)

    browser = webdriver.Firefox(executable_path=geckodriver_path)
    browser.get(url)

    search_keyword_field = browser.find_element_by_name("search")
    search_keyword_field.send_keys(keywords)

    submit_button = browser.find_element_by_css_selector(
        "html.js-enabled body#www-wikipedia-org.jsl10n-visible div.search-container form#search-form.pure-form fieldset button.pure-button.pure-button-primary-progressive")
    submit_button.click()

    # browser.close()


def test_get_chain(url, keywords):
    print("url =", url)
    print("-" * 20)

    browser = webdriver.Firefox(executable_path=geckodriver_path)
    browser.get(url)

    actions = ActionChains(browser).click("search").send_keys(
        keywords).send_keys(Keys.RETURN)
    actions.perform()

    # browser.close()


url = "https://www.wikipedia.org/"

print("-" * 40)

test_get(url, "napoleon bonaparte")
# test_get_chain(url, "napoleon bonaparte")

print("-" * 40)


if __name__ == '__main__':
    pass
