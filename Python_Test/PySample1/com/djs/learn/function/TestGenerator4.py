'''
Created on Feb 22, 2016

@author: dj
'''


class Fibonacci():
    '''
    Support both iterator and iterable.
    A singlton.
    '''

    def __init__(self):
        self.numbers = []

    def __iter__(self):
        return self

    def __next__(self):
        print("self.numbers =", self.numbers)
        if len(self.numbers) < 2:
            self.numbers.append(1)
        else:
            self.numbers.append(sum(self.numbers))
            self.numbers.pop(0)
        yield self.numbers[-1]

    def send(self, Value):
        pass


def main():
    print("-" * 40)

    fib = Fibonacci()
    print("fib =", fib)

    it = iter(fib)
    print("it =", it)

    print("next(it) =", next(it))
    print("next(it) =", next(it))
    print("next(it) =", next(it))

    print("-" * 40)

    it2 = iter(fib)
    print("it2 =", it2)

    print("next(it2) =", next(it2))

    print("-" * 40)


if __name__ == '__main__':
    main()
