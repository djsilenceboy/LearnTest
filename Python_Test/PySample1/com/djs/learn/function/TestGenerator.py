'''
Created on Feb 22, 2016

@author: djs
'''


def gen_number():
    for n in range(10):
        yield n


def gen_odds(start=1):
    ''' return all odd numbers from start.'''
    if int(start) % 2 == 0:
        start = int(start) + 1
    while True:
        print("start =", start)
        yield start
        start += 2


def gen_stopiteration():
    yield 1
    # Equal to raise StopIteration(12)
    return 12


class Wrapper():
    def __iter__(self):
        for n in range(10):
            yield n


def main():
    print("-" * 40)

    gn = gen_number()
    print("gn =", gn)

    print("next(gn) =", next(gn))
    print("next(gn) =", next(gn))
    print("next(gn) =", next(gn))

    print("-" * 40)

    list1 = list(gen_number())
    print("list1 =", list1)
    list2 = list(gen_number())
    print("list2 =", list2)

    print("-" * 40)

    for n in gen_odds():
        if n > 7:
            break
        else:
            print(n)

    print("-" * 40)

    for n in gen_odds():
        if n > 5:
            break
        else:
            print(n)

    print("-" * 40)

    gen_num = (number for number in range(8))
    print("gen_num =", gen_num)

    for number in gen_num:
        print(number)

    print("-" * 20)

    # No more in gen_num.
    for number in gen_num:
        print(number)

    print("-" * 40)

    gen_num2 = (number for number in range(8))
    print("gen_num2 =", gen_num2)
    gen_num3 = ((number, number**2) for number in gen_num2)
    print("gen_num3 =", gen_num3)
    for number in gen_num3:
        print(number)

    print("-" * 40)

    gn = gen_stopiteration()

    print("next(gn) =", next(gn))
    try:
        print("next(gn) =", next(gn))
    except Exception as e:
        print("next(gn).exception =", e)

    print("-" * 40)

    wrapper = Wrapper()
    list3 = list(wrapper)
    print("list3 =", list3)

    print("-" * 40)

    # r is iterable with __iter__.
    r = range(0, 5)
    print("r =", r)
    # g is iterator with __next__.
    g = iter(r)
    print("g =", g)
    print("next(g) =", next(g))

    print("-" * 40)

    dict = {'Cat': 'Tom', 'Mouse': 'Jerry'}
    # items is iterator with __next__.
    items = iter(dict.items())
    print("items =", items)
    print("next(dict) =", next(items))
    print("next(dict) =", next(items))

    print("-" * 40)

    z = zip(['x', 'y', 'z'], ['a', 'b', 'c', 'd'])
    print("z =", z)

    print("next(z) =", next(z))
    print("next(z) =", next(z))
    print("next(z) =", next(z))
    try:
        print("next(z) =", next(z))
    except:
        print("next(z) = No more")

    print("-" * 40)


if __name__ == '__main__':
    main()
