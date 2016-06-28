# For no return function, it always return last line result.
def func_1(*args)
    arg1, arg2 = args
    puts "Argument list:\n1 = #{arg1}\n2 = #{arg2}"
end

def func_2(arg1, arg2)
    puts "Argument list:\n1 = #{arg1}\n2 = #{arg2}"
end

def func_3()
end

def func_4()
    name = "Mary"
end

def func_5()
    name = "Mary"
    return name
end

def func_6()
    name = "Mary"
    age = 6
    return name, age
end

func_1("Tom", "Jerry")
puts "-" * 40
func_2("Tom", "Jerry")
puts "-" * 40
puts func_3()
puts "-" * 40
puts func_4()
puts "-" * 40
puts func_5()
puts "-" * 40
name, age = func_6()
puts "name = #{name}, age = #{age}"
