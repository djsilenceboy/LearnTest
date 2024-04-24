import pytest

@pytest.fixture
def get_grape():
    print("return grape.")
    return "grape"

@pytest.fixture
def fruit_bowl(get_grape):
    print("add grape.")
    return ["apple", "banana", get_grape]

def test_ok(fruit_bowl):
    print("=" * 40)

    print("test_ok: fruit_bowl =", fruit_bowl)

    assert "grape" in fruit_bowl


def test_failed(fruit_bowl):
    print("=" * 40)

    print("test_failed: fruit_bowl =", fruit_bowl)

    assert "orange" in fruit_bowl
