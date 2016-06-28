'''
Created on Feb 23, 2016

@author: dj
'''


def fact(n):
    if n == 0:
        return 1
    else:
        return n * fact(n - 1)


def reverse(s):
    if s == "":
        return s
    else:
        return reverse(s[1:]) + s[0]


def anagrams(s):
    if s == "":
        return [s]
    else:
        ans = []
        for w in anagrams(s[1:]):
            for pos in range(len(w) + 1):
                ans.append(w[:pos] + s[0] + w[pos:])
    return ans


def gcd(m, n):
    if n == 0:
        return m
    else:
        return gcd(n, m % n)


def fib_loop(n):
    curr = 1
    prev = 1
    for i in range(n - 2):
        curr, prev = curr + prev, curr
    return curr


def fib_recursion(n):
    """Many duplicated calculation"""
    if n < 3:
        return 1
    else:
        print("{0} = {1} + {2}".format(n, n - 1, n - 2))
        return fib_recursion(n - 1) + fib_recursion(n - 2)


def main():
    print("fact({0}) = {1}".format(5, fact(5)))
    print("fact({0}) = {1}".format(10, fact(10)))
    print("-" * 40)
    print("reverse({0}) = {1}".format("Hello", reverse("Hello")))
    print("-" * 40)
    print("anagrams({0}) = {1}".format("abc", anagrams("abc")))
    print("-" * 40)
    print("gcd({0}, {1}) = {2}".format(12, 9, gcd(12, 9)))
    print("gcd({0}, {1}) = {2}".format(21, 14, gcd(21, 14)))
    print("-" * 40)
    print("fib_loop({0}) = {1}".format(50, fib_loop(50)))
    print("-" * 40)
    print("fib_recursion({0}) = {1}".format(10, fib_recursion(10)))


if __name__ == '__main__':
    main()
