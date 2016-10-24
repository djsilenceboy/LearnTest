'''
Created on Oct 24, 2016

@author: dj
'''

import abc


class AbstractProduct(metaclass=abc.ABCMeta):

    def __init__(self):
        print("AbstractProduct.init")

    @abc.abstractmethod
    def make(self):
        print("AbstractProduct.make")


class Product(AbstractProduct):

    def __init__(self):
        print("Product.init")

    def make(self):
        print("Product.make")


def main():
    print("-" * 40)

    # Can't instantiate abstract class.
    try:
        product1 = AbstractProduct()
    except Exception as e:
        print("Exception =", e)

    print("-" * 40)

    product1 = Product()
    product1.make()

    print("-" * 40)

if __name__ == '__main__':
    main()
