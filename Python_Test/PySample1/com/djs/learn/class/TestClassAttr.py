'''
Created on Feb 22, 2016

@author: djs
'''


class Product(object):
    m_name = "Cat"

    def __init__(self):
        print("Production.__init__", "Enter")
        self.name = "Tom"
        self.age = 10
        self.score = 89
        print("Production.__init__", "Leave")

    def __getattr__(self, name):
        # Invoked if attribute does not exist.
        print("Product.__getattr__({})".format(name))
        value = "This is " + name + "!"
        setattr(self, name, value)
        return value

    def __getattribute__(self, name):
        # Always invoked first.
        # If attribute does not exist, invoke __getattr__.
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
print("-" * 20)
# This will invoke __getattribute__ and __getattr__.
print("product.location = {}".format(product.location))
print("-" * 20)
print("product.__dict__ = {}".format(product.__dict__))
# This will invoke __getattribute__ only.
print("-" * 20)
print("product.location = {}".format(product.location))

print("-" * 40)


if __name__ == '__main__':
    pass
