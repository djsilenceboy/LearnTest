'''
Created on Sep 28, 2016

@author: dj
'''

# Function foo becomes Class TaskEx.


class Task(object):

    def run(self, *args, **kwargs):
        raise NotImplementedError('Subclass must implement run.')

    def identify(self):
        return 'I am a task.'


def decoratorTypeE1(decorated):
    print("Enter Decorator TypeE1.")

    class TaskEx(Task):

        def run(self, *args, **kwargs):
            print("Enter Decorator TypeE1 inner.")
            return decorated(*args, **kwargs)

    print("Leave Decorator TypeE1.")
    return TaskEx


@decoratorTypeE1
def foo():
    print("Inside foo.")
    return 2 + 2


def main():
    print("-" * 40)

    # foo becomes a class TaskEx.
    print("foo = ", foo)

    print("-" * 40)

    # f is an instance of class TaskEx.
    f = foo()

    print("foo instance = ", f)

    print("-" * 40)

    print(f.run())

    print("-" * 40)

    print(f.identify())

    print("-" * 40)


if __name__ == '__main__':
    main()
