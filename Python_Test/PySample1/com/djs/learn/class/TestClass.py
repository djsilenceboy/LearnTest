'''
Created on Feb 22, 2016

@author: dj
'''


class MyClass(object):
    '''
    classdocs
    '''

    count = 0

    def __new__(self, *args, **kwargs):
        print("{0} instance created.".format(self.__class__))
        return object.__new__(self)

    def __init__(self, params):
        '''
        Constructor
        '''
        self.__value = params
        MyClass.count += 1
        print("{0} instance {1} created.".format(
            self.__class__.__name__, self.count))

    def a_method(self, aValue):
        self.__value *= aValue
        print("{0}.a_method(value = {1})".format(
            self.__class__.__name__, self.__value))

    def __del__(self):
        print("{0} instance {1} deleted.".format(
            self.__class__.__name__, self.count))
        MyClass.count -= 1


class MyClass2(MyClass):

    @classmethod
    def get_instance_count(cls):
        print("classmethod: Class count = {0}, {1}.".format(
            cls.count, MyClass.count))


class MyClass3(MyClass2):

    @staticmethod
    def just_show_something():
        print("staticmethod: Class count = {}.".format(MyClass3.count))


class MyClass4(MyClass3):
    def __init__(self):
        # Python 3: Best way to call super.
        super().__init__()
        # Python 2: Best way to call super.
        super(MyClass4, self).__init__()
        # Not good way to call super.
        MyClass3.__init__(self)


class Fruit():

    def __init__(self, name):
        self.name = name
        print("Fruit name =", name)

    @classmethod
    def create_fruits(cls, names):
        fruits = []
        for name in names:
            fruits.append(cls(name))
        return fruits


# print will use __str__, then __repr__, then default one.
# command line will use __repr__, then default one.


class TestStr():

    def __repr__(self):
        return "repr: " + self.__class__.__name__


class TestStr2(TestStr):

    def __str__(self):
        return "str: " + self.__class__.__name__


class Animal():

    def __init__(self, name):
        self.name = name

    def get_name(self):
        return self.name


class Cat(Animal):

    def get_name(self):
        return "Wow " + self.name


class Dog(Animal):

    def get_name(self):
        return "Oops " + self.name


def just_get_name(obj):
    print("The name is", obj.get_name())


def main():
    print("{0}.count = {1}".format("MyClass", MyClass.count))
    inst = MyClass(12)
    print("{0}.count = {1}".format("MyClass", MyClass.count))
    print(inst)
    inst.a_method(24)
    print(inst)

    print("-" * 40)

    inst2 = MyClass(36)
    print("{0}.count = {1}".format("MyClass", MyClass.count))

    del(inst2)
    print("del(inst2)")
    print("{0}.count = {1}".format("MyClass", MyClass.count))

    print("-" * 40)

    new_obj1 = MyClass2(1)
    new_obj2 = MyClass2(2)
    new_obj3 = MyClass2(3)
    new_obj3.get_instance_count()
    MyClass2.get_instance_count()

    print("-" * 40)

    new_obj4 = MyClass3(4)
    new_obj4.just_show_something()
    MyClass3.just_show_something()

    print("-" * 40)

    fruit_names = ["Apple", "Orange", "Banana"]
    print("fruit_names =", fruit_names)
    fruits = Fruit.create_fruits(fruit_names)
    print("fruits =", fruits)

    print("-" * 40)

    inst3 = TestStr()
    print(inst3)

    print("-" * 40)

    inst4 = TestStr2()
    print(inst4)

    print("-" * 40)

    animal = Animal("Animal")
    cat = Cat("Cat")
    dog = Dog("Dog")

    print("Animal =", animal.get_name())
    print("Cat =", cat.get_name())
    print("Dog =", dog.get_name())

    just_get_name(animal)
    just_get_name(cat)
    just_get_name(dog)

    print("-" * 40)


if __name__ == '__main__':
    main()
