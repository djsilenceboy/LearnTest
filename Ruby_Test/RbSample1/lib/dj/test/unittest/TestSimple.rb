class TestSimple < Test::Unit::TestCase
    def test_boolean()
        assert_equal(true, false)
    end
    
    def test_number_1()
        assert_equal(10, 10)
    end
    
    def test_number_2()
        assert_equal(10, 100)
    end
end
