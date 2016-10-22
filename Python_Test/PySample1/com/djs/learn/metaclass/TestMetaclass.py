'''
Created on Apr 15, 2016

@author: jiangdu
'''

# "metaclass" is built-in, from following package:
# from test.pickletester import metaclass


class Meta1(type):

    def __new__(cls, name, baseclass, class_dict):
        print("Meta.cls = {}".format(cls))
        print("Meta.name = {}".format(name))
        print("Meta.baseclass = {}".format(baseclass))
        print("Meta.class_dict = {}".format(class_dict))
        # super() same as super(Meta1, cls)
        return super().__new__(cls, name, baseclass, class_dict)

# For Python 3, no need declare object explicitly:
# class Product1(object, metaclass=Meta1):


class Product1(metaclass=Meta1):
    price = 10

    def foo(self):
        pass

print("-" * 40)


class Meta2(type):

    def __new__(cls, name, baseclass, class_dict):
        print("Meta.cls = {}".format(cls))
        print("Meta.name = {}".format(name))
        print("Meta.baseclass = {}".format(baseclass))
        print("Meta.class_dict = {}".format(class_dict))
        if class_dict["price"] > 20:
            print("price should <= 20!")
        return super().__new__(cls, name, baseclass, class_dict)


class Product2(metaclass=Meta2):
    price = 30

print("-" * 40)

print("type(type) =", type(type))
print("type(Meta1) =", type(Meta1))
print("type(Product1) =", type(Product1))
print("type(Meta2) =", type(Meta2))
print("type(Product2) =", type(Product2))

print("-" * 40)

Product = Meta1("Product", (object,), {})
ProductB = Meta2("ProductB", (object,), {"price": 10})

print("-" * 40)

print("type(Product) =", type(Product))
print("type(ProductB) =", type(ProductB))

print("-" * 40)

# Product3 and ProductC will get error:
# TypeError: metaclass conflict: the metaclass of a derived class must be
# a (non-strict) subclass of the metaclasses of all its bases


class Product3(Product1, Product2):
    pass


class ProductC(Product, ProductB):
    pass

print("-" * 40)


if __name__ == '__main__':
    pass
