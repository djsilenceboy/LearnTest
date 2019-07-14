'''
Created on Mar 2, 2016

@author: dj
'''

cat = 1
dog = 10
rat = 100

fruits = []


class DoTest(object):
    '''
    classdocs
    '''

    count = 0

    def __init__(self):
        DoTest.count += 1
        print("Instance No {} created.".format(DoTest.count))
        self.aValue = 1

    def do_test1(self):
        cat = 2
        rat = cat + dog
        print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
        print("vars(self) =", vars(self))
        print("vars() =", vars())
        print("locals() =", locals())

    def do_test2(self):
        self.bValue = 2
        rat = cat + dog
        print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
        print("vars(self) =", vars(self))
        print("vars() =", vars())
        print("locals() =", locals())

    def do_test3(self):
        global rat
        rat = cat + dog
        print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
        print("vars(self) =", vars(self))
        print("vars() =", vars())
        print("locals() =", locals())

    def do_test4(self, aList):
        aList.extend(['apple', 'orange'])
        print("vars() =", vars())
        print("locals() =", locals())


def main():
    test = DoTest()
    print("globals() =", globals())
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    test.do_test1()
    print("-" * 40)
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    test.do_test2()
    print("-" * 40)
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    test.do_test3()
    print("-" * 40)
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    print("fruits =", fruits)
    test.do_test4(fruits)
    print("fruits =", fruits)
    print("-" * 40)
    print("globals() =", globals())
    print("-" * 40)

if __name__ == '__main__':
    main()
