import pytest

@pytest.mark.parametrize("fruit", ["apple", "banana", "grape"])
def test_available(fruit):
    print("test_available: fruit =", fruit)
    assert fruit == "apple"


@pytest.mark.parametrize("fruit, required, quantity", [("apple", 10, 12), ("banana", 8, 6), ("grape", 20, 20)])
def test_available_quan(fruit, required, quantity):
    print("test_available_quan: fruit, required, quantity = {}, {}, {}".format(fruit, required, quantity))
    assert required <= quantity
