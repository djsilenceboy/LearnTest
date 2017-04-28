'''
Created on Sep 28, 2016

@author: dj
'''

from functools import wraps


method_list = []

# Type A decorator does not modify original method.
# Type B decorator modifies original method, replace it with a wrapper method.


def decoratorTypeA(decorated):
    print("-" * 40)
    print("TypeA Before: List = ", method_list)
    method_list.append(decorated)
    print("TypeA After: List = ", method_list)
    return decorated


def decoratorTypeB1(decorated):
    @wraps(decorated)
    def inner(*args, **kwargs):
        print("Enter Decorator TypeB1.")
        result = decorated(*args, **kwargs)
        print("Leave Decorator TypeB1.")
        return result
    return inner


def decoratorTypeB2(decorated):
    @wraps(decorated)
    def inner(*args, **kwargs):
        print("Enter Decorator TypeB2.")
        result = decorated(*args, **kwargs)
        print("Leave Decorator TypeB2.")
        return result
    return inner


def decoratorTypeC(decorated=None, prop_name=None):
    print("-" * 40)
    print("Enter Decorator TypeC.")
    print("decorated =", decorated)
    print("prop_name =", prop_name)

    # If only "decorated" present, it means decoratorTypeB.
    # If only "prop_name" present, it means decoratorTypeC.
    # "prop_name" means any or more properties.
    if decorated and prop_name:
        raise RuntimeError('Unexpected arguments.')

    # outer is decoratorTypeB.
    def outer(func):
        @wraps(func)
        def inner(*args, **kwargs):
            print("Enter Decorator TypeC - {0}.".format(prop_name))
            result = func(*args, **kwargs)
            print("Leave Decorator TypeC - {0}.".format(prop_name))
            return result
        return inner

    print("Leave Decorator TypeC.")

    if decorated:
        return outer(decorated)
    else:
        return outer


@decoratorTypeA
def decoratorTypeA_sum(a, b):
    print(a, "+", b)
    return a + b


@decoratorTypeA
def decoratorTypeA_multiple(a, b):
    print(a, "*", b)
    return a * b


@decoratorTypeB1
@decoratorTypeB2
def decoratorTypeB_sum(a, b):
    print(a, "+", b)
    return a + b


@decoratorTypeB2
@decoratorTypeB1
def decoratorTypeB_multiple(a, b):
    print(a, "*", b)
    return a * b


@decoratorTypeC
def decoratorTypeC_sum1(a, b):
    print(a, "+", b)
    return a + b


@decoratorTypeC()
def decoratorTypeC_sum2(a, b):
    print(a, "+", b)
    return a + b


@decoratorTypeC(prop_name="Jerry")
def decoratorTypeC_sum3(a, b):
    print(a, "+", b)
    return a + b


@decoratorTypeC(prop_name="Smith")
def decoratorTypeC_sum4(a, b):
    print(a, "+", b)
    return a + b


def main():
    print("-" * 60)

    result = decoratorTypeB_sum(2, 4)
    print(decoratorTypeB_sum, "=", result)

    print("-" * 40)

    result = decoratorTypeB_multiple(2, 4)
    print(decoratorTypeB_multiple, "=", result)

    print("-" * 40)

    result = decoratorTypeC_sum1(2, 4)
    print(decoratorTypeC_sum1, "=", result)

    print("-" * 40)

    result = decoratorTypeC_sum2(2, 4)
    print(decoratorTypeC_sum2, "=", result)

    print("-" * 40)

    result = decoratorTypeC_sum3(2, 4)
    print(decoratorTypeC_sum3, "=", result)

    print("-" * 40)

    result = decoratorTypeC_sum4(2, 4)
    print(decoratorTypeC_sum4, "=", result)

    print("-" * 40)


if __name__ == '__main__':
    main()
