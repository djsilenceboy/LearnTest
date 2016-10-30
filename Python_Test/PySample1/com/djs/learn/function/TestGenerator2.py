'''
Created on Feb 22, 2016

@author: djs
'''


def fibonacci():
    numbers = []
    while True:
        if len(numbers) < 2:
            numbers.append(1)
        else:
            numbers.append(sum(numbers))
            numbers.pop(0)
        yield numbers[-1]
        # continue


def main():
    print("-" * 40)

    for n in fibonacci():
        if n < 10000:
            print(n)
        else:
            break

    print("-" * 40)

    fib = fibonacci()

    print("fib =", fib)
    print("next(fib) =", next(fib))
    print("next(fib) =", next(fib))
    print("next(fib) =", next(fib))

    print("-" * 40)

    # fib2 is a new copy of fibonacci.
    fib2 = fibonacci()

    print("fib2 =", fib2)
    print("next(fib2) =", next(fib2))
    print("next(fib2) =", next(fib2))
    print("next(fib2) =", next(fib2))

    print("-" * 40)

    # Normally, a generator's __iter__ return self.
    # fib3 is same copy as fib.
    fib3 = iter(fib)

    print("fib3 =", fib3)
    print("next(fib3) =", next(fib3))
    print("next(fib3) =", next(fib3))

    print("-" * 40)

if __name__ == '__main__':
    main()
