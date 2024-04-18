from typing import TypedDict

print("-" * 40)

class MovieA():
    name = "MA_NA"
    director = "MA_DA"
    year = 2018

movie1 = MovieA()
print("movie1 =", movie1)

print("-" * 40)

class MovieB(TypedDict):
    name: str
    director: str
    year: int

movie2: MovieB = {'name': "MB_NA", 'director': "MB_DA", 'year': 2020}
print("movie2 =", movie2)

print("-" * 40)

class MovieC(MovieB):
    actor = "MC_AA"

movie3: MovieC = {'name': "MC_NA", 'director': "MC_DA", 'year': 2021}
print("movie3 =", movie3)

print("-" * 40)

MovieD = TypedDict("MovieD", {'name': str, 'director': str, 'year': int})
movie4: MovieD = {'name': "MD_NA", 'director': "MD_DA", 'year': 2022}

print("movie4 =", movie4)

print("-" * 40)

# Run following command to check.
# $ mypy TestTypedDict.py
# TestTypedDict.py:22: error: Invalid statement in TypedDict definition; expected "field_name: field_type"  [misc]
# TestTypedDict.py:24: error: Missing key "actor" for TypedDict "MovieC"  [typeddict-item]
# Found 2 errors in 1 file (checked 1 source file)
