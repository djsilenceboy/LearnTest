'''
Created on Mar 1, 2016

@author: jiangdu
'''

from functools import wraps


def test_default_list(my_item, my_list=[]):
    print("my_item =", my_item)
    my_list.append(my_item)
    print("my_list =", my_list)


def test_sum(a, b):
    return a + b


def test_mutilple(a, b):
    return a * b


def test_strategy(func, a, b):
    return func(a, b)


def test_outer(a, b):
    def test_inner(c, d):
        return c + d
    return test_inner(a, b)


def test_cloure(a, b):
    def test_inner(c):
        return a + b + c
    return test_inner


def main():
    test_default_list(1)
    test_default_list(3)
    test_default_list(5)

    print("-" * 40)

    result = test_strategy(test_sum, 2, 4)
    print("test_strategy(test_sum, 2, 4) =", result)

    result = test_strategy(test_mutilple, 2, 4)
    print("test_strategy(test_mutilple, 2, 4) =", result)

    print("-" * 40)

    result = test_outer(2, 4)
    print("test_outer(2, 4) =", result)

    print("-" * 40)

    cloureFunc = test_cloure(2, 4)

    result = cloureFunc(10)
    print("test_cloure(2, 4)(10) =", result)

    result = cloureFunc(20)
    print("test_cloure(2, 4)(20) =", result)

    print("-" * 40)


if __name__ == '__main__':
    main()
