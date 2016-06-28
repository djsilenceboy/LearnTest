class MySimple
    def initialize()
        puts "MySimple:initialize"
        @food = "Cake"
        @drink = "Coffee"
        @@counter = 0
    end

    def count()
        @@counter += 1
        return @@counter
    end

    attr_reader :food
    attr_accessor :drink
end
