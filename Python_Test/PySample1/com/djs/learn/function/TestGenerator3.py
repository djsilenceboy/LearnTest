'''
Created on Oct 17, 2016

@author: dj
'''
import itertools


def sub_gen1():
    print("AA1")
    yield "Foo"
    print("AA2")
    yield "Bar"
    print("AA3")


# Coroutine can pass from parent generator to child generator.
def sub_gen2():
    print("BB1")
    yield "Apple"
    print("BB2")
    received = yield "Orange"
    print("BB3 =", received)
    yield "Banana"
    print("BB4")


def main_gen1():
    # "yield from" support yield send.
    print("A1")
    yield from sub_gen1()
    print("A2")
    yield from sub_gen2()
    print("A3")


def main_gen2():
    # "itertools.chain" does not support yield send.
    print("B1")
    for word in itertools.chain(sub_gen1(), sub_gen2()):
        yield word
    print("B2")


def main():
    print("-" * 40)

    gen = main_gen1()

    print("gen1 =", gen)
    print("-" * 20)
    print("M1 =", next(gen))
    print("-" * 20)
    print("M2 =", next(gen))
    print("-" * 20)
    print("M3 =", next(gen))
    print("-" * 20)
    print("M4 =", next(gen))
    print("-" * 20)
    # Send and get yield.
    print("M5 =", gen.send("Cat"))
    print("-" * 20)
    # This line will cause StopIteration, because no more from generator.
    # print("M6 =", next(gen))

    print("-" * 40)

    gen = main_gen2()

    print("gen2 =", gen)
    print("-" * 20)
    print("M1 =", next(gen))
    print("-" * 20)
    print("M2 =", next(gen))
    print("-" * 20)
    print("M3 =", next(gen))
    print("-" * 20)
    print("M4 =", next(gen))

    print("-" * 40)


if __name__ == '__main__':
    main()
