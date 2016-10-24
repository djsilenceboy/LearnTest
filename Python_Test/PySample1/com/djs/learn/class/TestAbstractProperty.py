'''
Created on Oct 24, 2016

@author: dj
'''

import abc


class AbstractProduct(metaclass=abc.ABCMeta):

    def __init__(self):
        print("AbstractProduct.init")

    # "property" must place before "abc.abstractmethod"!
    @property
    @abc.abstractmethod
    def brand(self):
        print("AbstractProduct.brand")
        return "Hello"


class ProductA(AbstractProduct):

    def __init__(self):
        print("ProductA.init")


class ProductB(AbstractProduct):

    def __init__(self):
        print("ProductB.init")

    @property
    def brand(self):
        print("ProductB.brand")
        return "World"


def main():
    print("-" * 40)

    # Can't instantiate abstract class.
    try:
        product1 = AbstractProduct()
    except Exception as e:
        print("Exception =", e)

    print("-" * 40)

    # Can't instantiate abstract class.
    try:
        product2 = ProductA()
    except Exception as e:
        print("Exception =", e)

    print("-" * 40)

    product3 = ProductB()
    print("product3.brand =", product3.brand)

    print("-" * 40)

if __name__ == '__main__':
    main()
