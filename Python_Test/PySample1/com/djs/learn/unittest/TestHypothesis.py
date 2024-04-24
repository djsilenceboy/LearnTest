from hypothesis import given, strategies as st

@given(st.integers())
def test_quantity(quantity):
    print("test_quantity: quantity =", quantity)
    assert quantity < 100

@given(st.integers(min_value=50, max_value=150))
def test_quantity_2(quantity):
    print("test_quantity: quantity =", quantity)
    assert quantity < 100

@given(st.tuples(st.booleans(), st.text()))
def test_tuple(t):
    print("test_tuple: t =", t)
    assert len(t) == 2
    assert isinstance(t[0], bool)
    assert isinstance(t[1], str)

# pytest TestHypothesis2.py
