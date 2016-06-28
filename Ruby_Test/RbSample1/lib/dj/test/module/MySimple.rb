module MySimple
    def MySimple.print_name()
        puts "My name is Tom."
    end
    
    # Cannot see by caller.
    def print_age()
        puts "My age is 10."
    end
    
    LOCATION = "London"
end
