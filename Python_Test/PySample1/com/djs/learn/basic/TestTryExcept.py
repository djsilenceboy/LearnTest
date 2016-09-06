'''
Created on Mar 7, 2016

@author: djs
'''


class MyException(Exception):
    pass


def method1(x, y):
    try:
        a = x / y
    except Exception as e:
        print("method1:Exception = {0}".format(e))
        raise
    else:
        print("method1:No exception.")
    finally:
        print("method1:Finally.")


def method2(x, y):
    try:
        method1(x, y)
    except Exception as e:
        print("method2:Exception = {0}".format(e))
        raise Exception("Are you ok?") from e
    else:
        print("method2:No exception.")
    finally:
        print("method2:Finally.")


def method3(x, y):
    try:
        method2(x, y)
    except Exception as e:
        print("method3:Exception = {0}".format(e))
        raise MyException("It is mine.", True) from e
    else:
        print("method3:No exception.")
    finally:
        print("method3:Finally.")


def method4(x, y):
    try:
        method3(x, y)
    except Exception as e:
        print("method4:Exception = {0}".format(e))
        print("method4:Exception[0] = {0}".format(e.args[0]))
        print("method4:Exception[1] = {0}".format(e.args[1]))
        raise
    else:
        print("method4:No exception.")
    finally:
        print("method4:Finally.")


def main():
    method1(1, 1)

    print("-" * 40)

    try:
        method1(1, 0)
    except:
        pass

    print("-" * 40)

    method2(1, 1)

    print("-" * 40)

    try:
        method2(1, 0)
    except:
        pass

    print("-" * 40)

    try:
        method3(1, 0)
    except:
        pass

    print("-" * 40)

    try:
        method4(1, 0)
    except Exception as e:
        print("-" * 40)
        ec = e.__class__
        print(ec.__name__)
        indent = " "
        while ec.__bases__:
            ec = ec.__bases__[0]
            print(indent, ec.__name__)
            indent = indent + "  "

    print("-" * 40)


if __name__ == '__main__':
    main()
