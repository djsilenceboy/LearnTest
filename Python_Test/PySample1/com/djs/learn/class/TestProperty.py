'''
Created on Feb 23, 2016

@author: djs
'''


class Circle1(object):

    def __init__(self, radius):
        self.__radius = radius

    def set_radius(self, newValue):
        if newValue >= 0:
            self.__radius = newValue
        else:
            raise ValueError("Value must be positive")

    def area(self):
        return 3.14159 * (self.__radius ** 2)


class Circle2(object):

    def __init__(self, radius):
        self.__radius = radius

    def __set_radius(self, new_value):
        if new_value >= 0:
            self.__radius = new_value
        else:
            raise ValueError("Value must be positive")

    def __get_radius(self):
        return self.__radius

    radius = property(None, __set_radius)
    radius2 = property(__get_radius, __set_radius)

    @property
    def area(self):
        return 3.14159 * (self.__radius ** 2)


class Circle3(object):

    def __init__(self, radius):
        self.__radius = radius

    @property
    def radius(self):
        return self.__radius

    # Must "def radius(self)" together with "@radius.setter" in pair.
    @radius.setter
    def radius(self, new_value):
        self.__radius = new_value

    @property
    def area(self):
        return 3.14159 * (self.__radius ** 2)


def main():
    c1 = Circle1(42)
    print("c1.area() =", c1.area())
    # print(c1.__radius) # Cannot get "__" value.
    print("c1._Circle1__radius = ", c1._Circle1__radius)

    print("-" * 40)

    c2 = Circle2(42)
    print("c2.area =", c2.area)
    # print(c2.radius)

    print("-" * 40)

    c2.radius = 12
    print("c2.radius2 =", c2.radius2)
    print("c2.area =", c2.area)

    print("-" * 40)

    c3 = Circle3(0)
    c3.radius = 24
    print("c3.radius3 =", c3.radius)
    print("c3.area =", c3.area)


if __name__ == '__main__':
    main()
