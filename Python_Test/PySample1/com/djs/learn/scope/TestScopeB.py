'''
Created on Mar 7, 2016

@author: dj
'''

from com.djs.learn.scope import TestScopeA as inner

# Inner can only access its own space's "_city".
# Inner will not "use" this space's "_city".

_city = "London"


def set_city(value):
    # This "_city" is local one, not that global one!
    _city = value
    print("vars() =", vars().get('_city'))
    print("locals() =", locals().get('_city'))
    print("globals() =", globals().get('_city'))


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
