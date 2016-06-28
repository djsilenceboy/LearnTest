arg1, arg2, arg3 = ARGV

puts "Argument list:\n1 = %{arg1}\n2 = %{arg2}\n3 = %{arg3}" % {
    arg1: arg1, arg2: arg2, arg3: arg3
}

# There is only "ARGV.first", no "ARGV.second". 
arg4 = ARGV.first
puts "Argument 1 = #{arg4}"
