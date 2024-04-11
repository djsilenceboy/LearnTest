from typing import TypedDict

class MovieA():
    name = "MA_NA"
    director = "MA_DA"
    year = 2018

movie1 = MovieA()
print("movie1 =", movie1)

class MovieB(TypedDict):
    name: str
    director: str
    year: int

movie2: MovieB = {'name': "MB_NA", 'director': "MB_DA", 'year': 2020}
print("movie2 =", movie2)

# Run following command to check.
# $ mypy TestTypedDict.py
# Success: no issues found in 1 source file
