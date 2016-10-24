'''
Created on Oct 24, 2016

@author: dj
'''

import abc


class AbstractProduct(metaclass=abc.ABCMeta):

    def __init__(self):
        print("AbstractProduct.init")

    # "classmethod" must place before "abc.abstractmethod"!
    @classmethod
    @abc.abstractmethod
    def make(cls):
        print("AbstractProduct.make")


class ProductA(AbstractProduct):

    def __init__(self):
        print("ProductA.init")


class ProductB(AbstractProduct):

    def __init__(self):
        print("ProductB.init")

    @classmethod
    def make(cls):
        print("ProductB.make")


def main():
    print("-" * 40)

    AbstractProduct.make()

    # Can't instantiate abstract class.
    try:
        product1 = AbstractProduct()
    except Exception as e:
        print("Exception =", e)

    print("-" * 40)

    ProductA.make()

    # Can't instantiate abstract class.
    try:
        product2 = ProductA()
    except Exception as e:
        print("Exception =", e)

    print("-" * 40)

    ProductB.make()

    product3 = ProductB()
    product3.make()

    print("-" * 40)

if __name__ == '__main__':
    main()
