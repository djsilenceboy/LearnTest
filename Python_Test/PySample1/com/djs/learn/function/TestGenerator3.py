'''
Created on Oct 17, 2016

@author: dj
'''


def sub_gen1():
    print("B1")
    yield "Foo"
    print("B2")
    yield "Bar"
    print("B3")


# Coroutine can pass from parent generator to child generator.
def sub_gen2():
    print("C1")
    yield "Apple"
    print("C2")
    received = yield "Orange"
    print("C3 =", received)
    yield "Banana"
    print("C4")


def main_gen():
    print("A1")
    yield from sub_gen1()
    print("A2")
    yield from sub_gen2()
    print("A3")


def main():
    print("-" * 40)

    gen = main_gen()

    print("gen =", gen)
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

if __name__ == '__main__':
    main()
