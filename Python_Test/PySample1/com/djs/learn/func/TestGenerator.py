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


def main():
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


if __name__ == '__main__':
    main()
