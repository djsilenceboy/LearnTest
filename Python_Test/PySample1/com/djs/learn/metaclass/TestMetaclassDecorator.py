'''
Created on Apr 15, 2016

@author: jiangdu
'''
from test.pickletester import metaclass

# "metaclass" is built-in, from following package:
# from test.pickletester import metaclass


class Logged(type):

    def __new__(cls, name, baseclass, class_dict):
        print("Logged.cls = {}".format(cls))
        print("Logged.name = {}".format(name))
        print("Logged.baseclass = {}".format(baseclass))
        print("Logged.class_dict = {}".format(class_dict))
        for key, value in class_dict.items():
            print("Logged.method = {0}, {1}".format(key, value))
            if callable(value):
                class_dict[key] = cls.log_call(value)
        return super().__new__(cls, name, baseclass, class_dict)

    @staticmethod
    def log_call(fxn):
        def inner(*args, **kwargs):
            print("[{0}]({1}, {2}) Enter.".format(fxn.__name__, args, kwargs))
            try:
                response = fxn(*args, **kwargs)
                print("[{0}] Leave.".format(fxn.__name__))
                return response
            except Exception as e:
                print("[{0}] Exception: {1}".format(fxn.__name__, e))
                raise
        return inner


class Product(metaclass=Logged):

    def foo(self):
        pass

    def bar(self):
        raise TypeError("Oh no!")

product = Product()

print("-" * 40)

product.foo()

print("-" * 40)

try:
    product.bar()
except Exception as e:
    print("Exception: {}".format(e))

print("-" * 40)


if __name__ == '__main__':
    pass
