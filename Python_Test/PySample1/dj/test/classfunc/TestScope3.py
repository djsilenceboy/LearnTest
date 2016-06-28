'''
Created on Mar 2, 2016

@author: jiangdu
'''


class TestA(object):

    def __init__(self):
        self._value = 5
        self.__value = 5
        print("TestA:_value =", self._value)
        print("TestA:__value =", self.__value)

    def get_value(self):
        return self._value, self.__value


class TestB(TestA):

    def __init__(self):
        super().__init__()
        self._value = 10
        self.__value = 10
        print("TestB:_value =", self._value)
        print("TestB:__value =", self.__value)

    def get_value2(self):
        return self._value, self.__value


obj1 = TestB()

print("-" * 40)

print("obj1.get_value() =", obj1.get_value())
print("obj1.get_value2() =", obj1.get_value2())

print("-" * 40)

print("obj1._value =", obj1._value)

print("obj1._TestA__value =", obj1._TestA__value)
print("obj1._TestB__value =", obj1._TestB__value)

print("-" * 40)


if __name__ == '__main__':
    pass
