
from dataclasses import dataclass
from pydantic import constr, PositiveInt

# constr / PositiveInt will be simple str / int, no validate in runtime.

@dataclass
class MovieA():
    name: constr(min_length=5, max_length=6)
    director: constr(min_length=5, max_length=8)
    year: PositiveInt

print("-" * 40)

movieA1 = MovieA("MovieA1", "DirectorA", -2018)
print("movieA1 =", movieA1)

movieA2 = MovieA("MovieA1", 1234, -2018)
print("movieA2 =", movieA2)

print("-" * 40)
