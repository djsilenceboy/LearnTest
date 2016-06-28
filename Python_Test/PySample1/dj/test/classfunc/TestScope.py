'''
Created on Mar 2, 2016

@author: jiangdu
'''

cat = 1
dog = 10
rat = 100

fruits = []


def do_test1():
    cat = 2
    rat = cat + dog
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("vars() =", vars())
    print("locals() =", locals())


def do_test2():
    rat = cat + dog
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("vars() =", vars())
    print("locals() =", locals())


def do_test3():
    global rat
    rat = cat + dog
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("vars() =", vars())
    print("locals() =", locals())


def do_test4(aList):
    aList.extend(['apple', 'orange'])
    print("vars() =", vars())
    print("locals() =", locals())


def do_test5():
    cat = 2

    def do_test5_inner():
        cat = 3
        print("cat = {0}".format(cat))
        print("vars() =", vars())
        print("locals() =", locals())

    print("cat = {0}".format(cat))
    do_test5_inner()
    print("cat = {0}".format(cat))
    print("vars() =", vars())
    print("locals() =", locals())


def do_test6():
    cat = 2

    def do_test6_inner():
        nonlocal cat
        cat = 3
        print("cat = {0}".format(cat))
        print("vars() =", vars())
        print("locals() =", locals())

    print("cat = {0}".format(cat))
    do_test6_inner()
    print("cat = {0}".format(cat))
    print("vars() =", vars())
    print("locals() =", locals())


def main():
    print("globals() =", globals())
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    do_test1()
    print("-" * 40)
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    do_test2()
    print("-" * 40)
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    do_test3()
    print("-" * 40)
    print("cat = {0}, dog = {1}, rat = {2}".format(cat, dog, rat))
    print("-" * 40)
    print("fruits =", fruits)
    do_test4(fruits)
    print("fruits =", fruits)
    print("-" * 40)
    do_test5()
    print("-" * 40)
    do_test6()
    print("-" * 40)


if __name__ == '__main__':
    main()
