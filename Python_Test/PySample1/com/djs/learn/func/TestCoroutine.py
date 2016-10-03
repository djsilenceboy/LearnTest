'''
Created on Apr 18, 2016

@author: djs
'''


def coroutine_a():
    while True:
        received = yield
        print("Received =", received)


def minimize():
    current = yield
    print("current:1 =", current)
    while True:
        value = yield current
        print("value =", value)
        current = min(value, current)
        print("current:2 =", current)
        print("")


def squares(cursor=1):
    while True:
        print("Cursor =", cursor)
        response = yield cursor ** 2
        print("Response =", response)
        if response:
            cursor = int(cursor)
            print("New cursor =", cursor)
        else:
            cursor += 1


def main():
    print("-" * 40)

    it = coroutine_a()
    next(it)
    it.send("First")
    it.send("Second")

    print("-" * 40)

    it = minimize()
    next(it)
    print("it:1 =", it.send(22))
    print("it:2 =", it.send(10))
    print("it:3 =", it.send(15))
    print("it:4 =", it.send(6))

    print("-" * 40)

    sq = squares()

    print("next(sq) =", next(sq))

    print("-" * 20)

    sq.send(4)

    print("-" * 20)

    print("send(sq) =", next(sq))

    print("-" * 20)

    print("next(sq) =", next(sq))

    print("-" * 40)

if __name__ == '__main__':
    main()
