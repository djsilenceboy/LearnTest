import unittest
from hypothesis import given, strategies as st

class TestHypothesis(unittest.TestCase):
    @given(st.integers())
    def test_quantity(self, quantity):
        print("test_quantity: quantity =", quantity)
        assert quantity < 100
    
    @given(st.tuples(st.booleans(), st.text()))
    def test_tuple(self, t):
        print("test_tuple: t =", t)
        assert len(t) == 2
        assert isinstance(t[0], bool)
        assert isinstance(t[1], str)

if __name__ == "__main__":
    unittest.main()

# python TestHypothesis2.py
# pytest TestHypothesis2.py
