puts "Hello, world!"
puts """Several lines 1.\n
Several lines 2.\n
Several lines 3.
"""

puts "-" * 40

puts 'Hello, world!'
puts '''Several lines 1.\n
Several lines 2.\n
Several lines 3.
'''

puts "-" * 40

puts "This", "is", "a", "small", "world."

puts "-" * 40

puts "Height is\n 6\"4'."
puts 'Height is\n 6"4\'.'

puts "-" * 40

my_name = "Tom"
your_name = 'Jerry'

# What do you say?

puts "This is my name #{my_name}."
puts 'That is your name #{your_name}.'

puts "These are our names %{my} and %{your}" % {my: my_name, your: your_name}

puts "These are our names %{my} and %{your} again" % {
    my: my_name,
    your: your_name
}

puts "-" * 40

a = 4
b = 5.0
c = false

puts "Answer = #{a * b}."
puts "Answer = #{3 + 5}."
puts "Answer is %{a1} or %{a2}." % {a1: true, a2: c}
