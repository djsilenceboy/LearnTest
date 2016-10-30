'''
Created on Apr 8, 2016

@author: jiangdu
'''

from string import capwords


def cap_text(text):
    return text.capitalize()


def title_text(text):
    return text.title()


def capwords_text(text):
    return capwords(text)


def some_error():
    raise ValueError()


if __name__ == '__main__':
    pass
