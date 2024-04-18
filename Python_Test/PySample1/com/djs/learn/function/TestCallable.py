
from collections.abc import Callable

def repeat(func: Callable, times: int=3) -> Callable:
    def _wrapper(*args, **kwargs):
        for _ in range(times):
            func(*args, **kwargs)

    return _wrapper

@repeat
def say_hello():
    print("hello!")

say_hello()

print("-" * 40)

# @repeat(times=5)
# def say_good():
#     print("good!")

# say_good()

print("-" * 40)

if __name__ == '__main__':
    pass
