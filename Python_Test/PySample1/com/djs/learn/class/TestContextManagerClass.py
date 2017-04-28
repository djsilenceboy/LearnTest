'''
Created on Sep 30, 2016

@author: dj
'''


class ContextManager(object):

    def __init__(self):
        self.entered = False
        print("ContextManager.init.entered =", self.entered)

    def __enter__(self):
        self.entered = True
        print("ContextManager.enter.entered =", self.entered)
        return self

    def __exit__(self, exception_type, exception, traceback):
        self.entered = False
        print("ContextManager.exit.entered =", self.entered)
        print("ContextManager.exit.exception_type =", exception_type)
        print("ContextManager.exit.exception =", exception)
        print("ContextManager.exit.traceback =", traceback)


class ExceptionHandler(object):

    def __init__(self, error_message=None):
        '''Define different error message.'''
        self.error_message = error_message
        print("ExceptionHandler.enter.error_message =", self.error_message)

    def __enter__(self):
        print("ExceptionHandler.enter.")
        return self

    def __exit__(self, exception_type, exception, traceback):
        print("ExceptionHandler.exit.")
        print("ExceptionHandler.exit.exception_type =", exception_type)
        print("ExceptionHandler.exit.exception =", exception)
        print("ExceptionHandler.exit.traceback =", traceback)

        # If any exception.
        if exception_type:
            print("ExceptionHandler.exit.error_message =", self.error_message)

        # If no "return" clause, it will return False.
        # If "return False", the exception will be re-thrown.
        # If "return True", the exception will not be re-thrown.
        # It could re-throw with self-defined error.
        return True


class ValueErrorExceptionHandler(object):

    def __enter__(self):
        print("ValueErrorExceptionHandler.enter.")
        return self

    def __exit__(self, exception_type, exception, traceback):
        print("ValueErrorExceptionHandler.exit.")
        print(
            "ValueErrorExceptionHandler.exit.exception_type =", exception_type)
        print("ValueErrorExceptionHandler.exit.exception =", exception)
        print("ValueErrorExceptionHandler.exit.traceback =", traceback)

        # If no exception.
        if not exception_type:
            return True

        # Only suppress certain exception.
        if issubclass(exception_type, ValueError):
            print("ValueErrorExceptionHandler.exit.exception, less pass.")
            return True


def main():
    print("-" * 40)

    cm = ContextManager()
    print("cm.entered =", cm.entered)

    print("-" * 40)

    with cm:
        print("cm.entered =", cm.entered)

    print("-" * 40)

    with ExceptionHandler():
        x = 5 + 5

    print("-" * 40)

    with ExceptionHandler("New message."):
        x = 5 / 0
        print("Never printed due to exception.")

    print("-" * 40)

    with ExceptionHandler("Another new message."):
        try:
            x = 5 / 0
        except:
            print("Exception is capture here, not in __exit__.")

    print("-" * 40)

    with ValueErrorExceptionHandler():
        raise ValueError("Wrong value.")

    print("-" * 40)

    try:
        with ValueErrorExceptionHandler():
            raise TypeError("Wrong type.")
    except Exception as e:
        print("Exception =", e)

    print("-" * 40)

if __name__ == '__main__':
    main()
