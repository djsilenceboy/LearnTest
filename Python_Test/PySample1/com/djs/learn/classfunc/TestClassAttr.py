'''
Created on Feb 22, 2016

@author: djs
'''


class Product(object):
    m_name = "Cat"

    def __init__(self):
        self.name = "Tom"
        self.age = 10
        self.score = 89

    def __getattr__(self, name):
        print("Product.__getattr__({0})".format(name))
        value = "This is " + name + "!"
        setattr(self, name, value)
        return value

    def __getattribute__(self, name):
        print("Product.__getattribute__({})".format(name))
        return super().__getattribute__(name)

    def __setattr__(self, name, value):
        print("Product.__setattr__({0}, {1})".format(name, value))
        return super().__setattr__(name, value)


product = Product()

print("-" * 40)

print("product.m_name = {}".format(product.m_name))

print("-" * 40)

print("product.name = {}".format(product.name))

print("-" * 40)

print("product.__dict__ = {}".format(product.__dict__))
print("product.location = {}".format(product.location))
print("product.__dict__ = {}".format(product.__dict__))

print("-" * 40)


if __name__ == '__main__':
    pass
