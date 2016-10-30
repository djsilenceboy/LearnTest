'''
Created on Oct 27, 2016

@author: dj
'''


def test_age(age):
    try:
        print("age =", age)
        assert age >= 10, "Wow, too young! You are only {}.".format(age)
    except Exception as e:
        print("Exception =", e)


def main():
    print("-" * 40)

    test_age(8)

    print("-" * 40)

    test_age(10)

    print("-" * 40)

if __name__ == '__main__':
    main()
