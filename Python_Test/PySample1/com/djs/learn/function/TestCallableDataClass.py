
from collections.abc import Callable
from dataclasses import dataclass

@dataclass
class Produce():
    prepare: Callable
    process: Callable
    cleanup: Callable

def prepare():
    print("Product: prepare.")

def process():
    print("Product: process.")

def cleanup():
    print("Product: cleanup.")

print("-" * 40)

produce = Produce(prepare, process, cleanup)

print("produce =", produce)

print("-" * 40)

produce.prepare()
produce.process()
produce.cleanup()

print("-" * 40)
