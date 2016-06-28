'''
Created on Mar 7, 2016

@author: dj
'''

from dj.test.classfunc import TestScopeA as inner

_city = "London"


def set_city(value):
    _city = value
    print("vars() =", vars())


def get_city():
    return _city


def main():
    print("-" * 40)

    print("inner.get_city() = {0}".format(inner.get_city()))
    inner.set_city("China")
    print("inner.get_city() = {0}".format(inner.get_city()))

    print("-" * 40)

    print("innerCity = {0}".format(inner._city))
    inner._city = "Malaysia"
    print("innerCity = {0}".format(inner._city))
    print("inner.get_city() = {0}".format(inner.get_city()))

    print("-" * 40)

    print("get_city() = {0}".format(get_city()))
    set_city("Singapore")
    print("get_city() = {0}".format(get_city()))

    print("-" * 40)

    global _city
    _city = "Japan"
    print("get_city() = {0}".format(get_city()))
    print("inner.get_city() = {0}".format(inner.get_city()))

    print("-" * 40)


if __name__ == '__main__':
    main()
