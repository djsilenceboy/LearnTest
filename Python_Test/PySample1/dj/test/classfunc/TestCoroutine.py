'''
Created on Apr 18, 2016

@author: dj
'''


def coroutine_a():
    while True:
        received = yield
        print("Received =", received)

it = coroutine_a()
next(it)
it.send("First")
it.send("Second")

print("-" * 40)


def minimize():
    current = yield
    print("current:1 =", current)
    while True:
        value = yield current
        print("value =", value)
        current = min(value, current)
        print("current:2 =", current)
        print("")

it = minimize()
next(it)
print("it:1 =", it.send(22))
print("it:2 =", it.send(10))
print("it:3 =", it.send(15))
print("it:4 =", it.send(6))

print("-" * 40)


if __name__ == '__main__':
    pass
