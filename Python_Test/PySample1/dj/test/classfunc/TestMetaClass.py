'''
Created on Apr 15, 2016

@author: jiangdu
'''
from test.pickletester import metaclass


class Meta1(type):

    def __new__(meta, name, baseclass, class_dict):
        print("Meta.meta = {}".format(meta))
        print("Meta.name = {}".format(name))
        print("Meta.baseclass = {}".format(baseclass))
        print("Meta.class_dict = {}".format(class_dict))
        return super().__new__(meta, name, baseclass, class_dict)


class Product1(object, metaclass=Meta1):
    price = 10

    def foo(self):
        pass


class Meta2(type):

    def __new__(meta, name, baseclass, class_dict):
        print("Meta.meta = {}".format(meta))
        print("Meta.name = {}".format(name))
        print("Meta.baseclass = {}".format(baseclass))
        print("Meta.class_dict = {}".format(class_dict))
        if class_dict["price"] > 20:
            raise ValueError("Should <= 20!")
        return super().__new__(meta, name, baseclass, class_dict)


class Product2(object, metaclass=Meta2):
    price = 30


print("-" * 40)


if __name__ == '__main__':
    pass
