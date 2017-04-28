'''
Created on Sep 28, 2016

@author: dj
'''


class Task(object):

    def __call__(self, *args, **kwargs):
        self.run(*args, **kwargs)

    def run(self, *args, **kwargs):
        raise NotImplementedError('Subclass must implement run.')

    def identify(self):
        return 'I am a task.'


def decoratorTypeE2(decorated):
    print("Enter Decorator TypeE2.")

    class TaskEx(Task):

        def run(self, *args, **kwargs):
            print("Enter Decorator TypeE2 inner.")
            return decorated(*args, **kwargs)

    print("Leave Decorator TypeE2.")
    return TaskEx()


@decoratorTypeE2
def foo():
    return 2 + 2


def main():
    print("-" * 40)

    # foo is an instance of class TaskEx.
    print("foo instance = ", foo)

    print("-" * 40)

    # Run __call__
    foo()

    print(foo.identify())

    print("-" * 40)


if __name__ == '__main__':
    main()
