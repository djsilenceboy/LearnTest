
from typing import Protocol

class Printable(Protocol):
    def print(self) -> None:
        pass


class Book:
    def __init__(self, title: str):
        self.title = title

    def print(self) -> None:
        print("Book Title: {0}".format(self.title))


def print_object(obj: Printable) -> None:
    obj.print()


book1 = Book("Xi You Ji")

print("-" * 40)

book1.print()

print("-" * 40)

print_object(book1)

print("-" * 40)
