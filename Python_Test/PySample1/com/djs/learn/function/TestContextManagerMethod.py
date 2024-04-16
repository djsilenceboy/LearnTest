'''
Created on Sep 30, 2016

@author: dj
'''

from contextlib import contextmanager


@contextmanager
def get_mobilephones():
    mobilePhoneList = ["Samsung", "Apple", "Huawei"]
    try:
        print("get_mobilephones: mobilePhoneList =", mobilePhoneList)
        yield mobilePhoneList
    except Exception as e:
        pass

@contextmanager
def exception_handler(error_message=None):
    '''Define different error message.'''
    print("Enter exception_handler.")
    print("exception_handler.error_message =", error_message)

    try:
        yield
        print("Leave exception_handler with normal.")
    except Exception as e:
        print("exitexception_handler.exception =", e)
        print("Leave exception_handler with exception.")


def main():
    print("-" * 40)

    with get_mobilephones() as mobilePhoneList:
        print("main: mobilePhoneList =", mobilePhoneList)

    print("-" * 40)

    with exception_handler():
        x = 5 + 5

    print("-" * 40)

    with exception_handler("New message."):
        x = 5 / 0
        print("Never printed due to exception.")

    print("-" * 40)

    with exception_handler("Another new message."):
        try:
            x = 5 / 0
        except:
            print("Exception is capture here, not in __exit__.")

    print("-" * 40)

if __name__ == '__main__':
    main()
