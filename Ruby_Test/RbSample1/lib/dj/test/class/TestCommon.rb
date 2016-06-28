class Parent
    def implicit()
        puts "Parent:implicit()"
    end
    
    def override()
        puts "Parent:override()"
    end

    def altered()
        puts "Parent:altered()"
    end
end

class Child < Parent
    def override()
        puts "Child:override()"
    end

    def altered()
        puts "Child:altered() before super()"
        super()
        puts "Child:altered() after super()"
    end
end

dad = Parent.new()
son = Child.new()

dad.implicit()
son.implicit()

puts "-" * 40

dad.override()

puts "-" * 40

son.override()

puts "-" * 40

dad.altered()

puts "-" * 40

son.altered()
