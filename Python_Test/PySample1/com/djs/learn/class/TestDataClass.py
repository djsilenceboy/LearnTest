from dataclasses import dataclass

@dataclass
class MovieA():
    name: str
    director: str
    year: int

movieA1 = MovieA("MovieA1", "DirectorA", 2018)
movieA1.director = "DirectorA1"
print("movieA1 =", movieA1)
movieA2 = MovieA("MovieA2", "DirectorA2", 2018)
print("movieA2 =", movieA2)
print("movieA1 == movieA2:", movieA1 == movieA2)
movieA3 = MovieA("MovieA2", "DirectorA2", 2018)
print("movieA3 =", movieA3)
print("movieA2 == movieA3:", movieA2 == movieA3)
# print("sorted(MovieA) =", sorted([movieA2, movieA1, movieA3]))


@dataclass(frozen=True)
class MovieB():
    name: str
    director: str
    year: int

movieB1 = MovieB("MovieB1", "DirectorB1", 2019)
# movieB1.director = "DirectorB2"
print("movieB1 =", movieB1)


@dataclass(eq=True, order=True)
class MovieC():
    name: str
    director: str
    year: int

movieC1 = MovieC("MovieC1", "DirectorC1", 2020)
print("movieC1 =", movieC1)
movieC2 = MovieC("MovieC1", "DirectorC1", 2020)
print("movieC2 =", movieC2)
print("movieC1 == movieC2:", movieC1 == movieC2)
movieC3 = MovieC("MovieC3", "DirectorC3", 2020)
print("movieC3 =", movieC3)
print("sorted(MovieC) =", sorted([movieC2, movieC3, movieC1]))
