
def fib_while(a: int, b: int, max: int):
    print("a, b = {}, {}".format(a, b))
    c: int = a + b
    i: int = 3
    while c < max:
        print("{}: {}".format(i, c))
        a = b
        b = c
        c = a + b
        i = i + 1

def fib_recursive(loop: int):
    v: int = 0
    if loop == 0:
       v = 0
    elif loop == 1:
        v = 1
    else:
        v = fib_recursive(loop - 2) + fib_recursive(loop - 1)

    print("{}: {}".format(loop, v))
    return v

fib_while(0, 1, 100)

fib_recursive(12)
