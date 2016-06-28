word_list= "All good things come to those who wait."
puts "word_list = #{word_list}"

puts "-" * 40

word_array = word_list.split(' ')
puts "word_array = #{word_array}"

puts "-" * 40

word_array_sorted = word_array.sort
puts "word_array_sorted = #{word_array_sorted}"

puts "-" * 40

fist_word = word_array.shift
puts "fist_word = #{fist_word}"
puts "word_array = #{word_array}"

puts "-" * 40

last_word = word_array.pop
puts "last_word = #{last_word}"
puts "word_array = #{word_array}"
