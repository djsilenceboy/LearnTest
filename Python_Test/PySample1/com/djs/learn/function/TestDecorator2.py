'''
Created on Sep 28, 2016

@author: dj
'''

from functools import wraps
import time

# Decorator for Class.


def decoratorTypeD(cls):
    print("-" * 40)
    print("Enter Decorator TypeD.")
    original_init = cls.__init__

    @wraps(original_init)
    def new_init(self, *args, **kwargs):
        print("Enter Decorator TypeD inner.")
        original_init(self, *args, **kwargs)
        print("Leave Decorator TypeD inner.")
        self._created = time.time()

    cls.__init__ = new_init

    cls.__lt__ = lambda self, other: self._created < other._created
    cls.__gt__ = lambda self, other: self._created > other._created

    print("Leave Decorator TypeD.")
    return cls


@decoratorTypeD
class Sortable(object):

    def __init__(self, name):
        print("name =", name)
        self.name = name

    def __repr__(self):
        return self.name


def main():
    print("-" * 40)

    first = Sortable('first')

    print("-" * 40)

    second = Sortable('second')

    print("-" * 40)

    third = Sortable('third')

    print("-" * 40)

    class_list = [second, third, first]

    print("class_list =", class_list)

    sorted_class_list = sorted(class_list)

    print("sorted class_list =", sorted_class_list)

    print("-" * 40)


if __name__ == '__main__':
    main()
