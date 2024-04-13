
class MyClass1(object):

    varA1 = 10
    _varA2 = 11
    __varA3 = 12

    def __init__(self):
        self.varB1 = 20
        self._varB2 = 21
        self.__varB3 = 22
        print("__init__: self, varA1 = {0}, {1}".format(self, MyClass1.varA1))
        print("__init__: self, _varA2 = {0}, {1}".format(self, MyClass1._varA2))
        print("__init__: self, __varA3 = {0}, {1}".format(self, MyClass1.__varA3))
        print("__init__: self, varB1 = {0}, {1}".format(self, self.varB1))
        print("__init__: self, _varB2 = {0}, {1}".format(self, self._varB2))
        print("__init__: self, __varB3 = {0}, {1}".format(self, self.__varB3))

    def myMethodA1(self):
        print("myMethodA1")

    def _myMethodA2(self):
        print("_myMethodA2")

    def __myMethodA3(self):
        print("__myMethodA3")


class MyClass2(MyClass1):

    def __init__(self):
        self.varC1 = 30
        self._varC2 = 31
        self.__varC3 = 32
        print("__init__: self, varA1 = {0}, {1}".format(self, MyClass1.varA1))
        print("__init__: self, _varA2 = {0}, {1}".format(self, MyClass1._varA2))
        print("__init__: self, varC1 = {0}, {1}".format(self, self.varC1))
        print("__init__: self, _varC2 = {0}, {1}".format(self, self._varC2))
        print("__init__: self, __varC3 = {0}, {1}".format(self, self.__varC3))

    def myMethodC1(self):
        print("myMethodC1")
        super().myMethodA1()
        super()._myMethodA2()

    def _myMethodC2(self):
        print("_myMethodC2")

    def __myMethodC3(self):
        print("__myMethodC3")


class MyClass3(MyClass2):

    def __init__(self):
        self.varD1 = 40
        self._varD2 = 41
        self.__varD3 = 42
        print("__init__: self, varA1 = {0}, {1}".format(self, MyClass1.varA1))
        print("__init__: self, _varA2 = {0}, {1}".format(self, MyClass1._varA2))
        print("__init__: self, varD1 = {0}, {1}".format(self, self.varD1))
        print("__init__: self, _varD2 = {0}, {1}".format(self, self._varD2))
        print("__init__: self, __varD3 = {0}, {1}".format(self, self.__varD3))

    def myMethodD1(self):
        print("myMethodD1")
        super().myMethodA1()
        super()._myMethodA2()
        super().myMethodC1()
        super()._myMethodC2()

    def _myMethodD2(self):
        print("_myMethodD2")


def main():
    print("-" * 40)
    myObj1 = MyClass1()
    print("-" * 40)
    myObj1.myMethodA1()
    myObj1._myMethodA2()

    print("-" * 40)
    myObj2 = MyClass2()
    print("-" * 40)
    myObj2.myMethodC1()
    myObj2._myMethodC2()

    print("-" * 40)
    myObj3 = MyClass3()
    print("-" * 40)
    myObj3.myMethodD1()
    myObj3._myMethodD2()

    print("-" * 40)


if __name__ == '__main__':
    main()
