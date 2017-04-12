'''
Created on Mar 1, 2016

@author: jiangdu
'''

from _datetime import datetime
from inspect import ArgSpec
import random
import time


def roll1(sides, dice):
    result = 0
    for roll in range(0, dice):
        result += random.randint(1, sides)
    print("Roll1 (sides = {0}, dice = {1}) = {2}".format(sides, dice, result))
    return result


def roll2(sides=6, dice=1):
    result = 0
    for roll in range(0, dice):
        result += random.randint(1, sides)
    print("Roll2 (sides = {0}, dice = {1}) = {2}".format(sides, dice, result))
    return result


def default_args1(a, b, c=0, d=1):
    print("args = {}".format(vars()))


def default_args2(a, b, *, c=0, d=1):
    print("args = {}".format(vars()))


# time only init when load script.
def default_args3(time=datetime.now()):
    print("args = {}".format(vars()))


def default_args4(time=None):
    time = datetime.now() if time is None else time
    print("args = {}".format(vars()))


def collect_args(*args):
    print("*args =", args)
    for value in args:
        print(value)


def collect_kwargs(**kwargs):
    print("**kwargs =", kwargs)
    for value in kwargs:
        print(value)


def collect_args_kwargs(a, b, *args, **kwargs):
    print("a = {0}, b = {1}".format(a, b))
    print("*args =", args)
    print("**kwargs =", kwargs)


def main():
    print("-" * 40)

    roll1(4, 1)
    roll1(4, 2)

    print("-" * 40)

    roll2(4, 2)
    roll2(5)
    roll2()
    roll2(dice=2)
    roll2(dice=2, sides=5)

    print("-" * 40)

    default_args1(11, 22)
    default_args1(11, 22, 33)
    default_args1(11, 22, c=33)

    print("-" * 40)

    default_args2(11, 22)
    default_args2(11, 22, c=33)
    default_args2(11, 22, d=44, c=33)

    print("-" * 40)

    default_args3()
    time.sleep(2)
    default_args3()

    print("-" * 40)

    default_args4()
    time.sleep(2)
    default_args4()

    print("-" * 40)

    collect_args()
    collect_args(1, 2, 3)

    list1 = [4, 5, 6]
    collect_args(*list1)
    collect_args(list1)

    print("-" * 40)

    collect_kwargs()
    collect_kwargs(Cat=1, Dog=2, Chicken=3)

    print("-" * 40)

    collect_args_kwargs(1, 2, 'apple', 'carrots', Cat=1, Dog=2, Chicken=3)

    print("-" * 40)

    # Show default input parameters, if any.
    print("roll2 =", roll2.__defaults__)
    print("default_args1 =", default_args1.__defaults__)

    print("-" * 40)


if __name__ == '__main__':
    main()
