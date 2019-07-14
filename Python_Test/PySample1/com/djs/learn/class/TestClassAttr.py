'''
Created on Feb 22, 2016

@author: dj
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
        # Only be invoked if attribute does not exist in "__dict__".
        print("Product.__getattr__({})".format(name))
        value = "This is " + name + "!"
        setattr(self, name, value)
        return value

    def __getattribute__(self, name):
        # Always be invoked first.
        # It will invoke __getattr__, if attribute does not exist in
        # "__dict__".
        print("Product.__getattribute__({})".format(name))
        # Do not call this __getattribute__() directly, call super one.
        return super().__getattribute__(name)

    def __setattr__(self, name, value):
        print("Product.__setattr__({0}, {1})".format(name, value))
        return super().__setattr__(name, value)


print("-" * 40)

print("Product.__dict__ = {}".format(Product.__dict__))
print("Product.m_name = {}".format(Product.m_name))

print("-" * 40)

product = Product()
print("product.__dict__ = {}".format(product.__dict__))

print("-" * 40)

print("product.name = {}".format(product.name))
print("product.__dict__ = {}".format(product.__dict__))

print("-" * 40)

# This will invoke __getattribute__ and then __getattr__ and then __setaddr__.
print("product.location = {}".format(product.location))
print("product.__dict__ = {}".format(product.__dict__))

print("-" * 40)

print("product.location = {}".format(product.location))
print("product.__dict__ = {}".format(product.__dict__))

print("-" * 40)

print("hasattr(product, location) = {}".format(hasattr(product, "location")))

print("-" * 40)

# This will also invoke __getattribute__ and then __getattr__ and then
# __setaddr__.
# hasattr will add attribute into __dict__, if not exists!
print("hasattr(product, height) = {}".format(hasattr(product, "height")))
print("product.__dict__ = {}".format(product.__dict__))

print("-" * 40)
if __name__ == '__main__':
    pass
