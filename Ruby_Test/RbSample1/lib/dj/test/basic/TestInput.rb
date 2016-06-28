print "What's your name?"
# name = gets
# name = name.chomp
name = gets.chomp

puts "Your name is #{name}."

puts "-" * 40

puts "What's your name again?"
name = $stdin.gets.chomp

puts "Your name actually is #{name}."

puts "-" * 40

print "How old are you?"
age = gets.chomp.to_i

puts "You are #{age} years old."

puts "-" * 40

print "How old are you again?"
another_age = gets.chomp
age2 = another_age.to_i

puts "Your age is #{age2} years."

puts "-" * 40

print "What's score x.x?"
score = gets.chomp.to_f

puts "Your score is #{score}."
