
from pydantic.dataclasses import dataclass
from pydantic import validator, constr, PositiveInt

@dataclass
class MovieA():
    name: constr(min_length=5, max_length=8)
    director: constr(min_length=5, max_length=10)
    year: PositiveInt

    @validator('year')
    def check_year(cls, year):
        if year <= 2024:
           return year
        raise ValueError("Year must <= 2024.")


print("-" * 40)

# movieA1 = MovieA("Mov", "DirectorA", 2018)
# movieA1 = MovieA("MovieA1", "DirectorA", -2018)
movieA1 = MovieA("MovieA1", "DirectorA", 2018)
print("movieA1 =", movieA1)

print("-" * 40)

movieA1.director = "DirectorA1"
movieA1.year = "Ok"
print("movieA1 =", movieA1)

print("-" * 40)

movieA2 = MovieA("MovieA2", "DirectorA2", 2025)
print("movieA2 =", movieA2)

print("-" * 40)


# pydantic_core._pydantic_core.ValidationError: 1 validation error for MovieA
# 2
#   Value error, Year must <= 2024. [type=value_error, input_value=2025, input_type=int]
