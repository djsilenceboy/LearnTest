
number_array = [1, 2, 3, 4, 5]
puts "number_array = #{number_array}" 

number_array_2 = []
puts "number_array_2 = #{number_array_2}"

puts "-" * 40

number_array.each do |number|
    number_array_2.push(number)
end    

puts "number_array_2 = #{number_array_2}"

puts "-" * 40
    
number_array_3 = (1..5)
puts "number_array_3 = #{number_array_3}"

puts "-" * 40

puts "number_array[2] = #{number_array[2]}"
puts "number_array[-1] = #{number_array[-1]}"
puts "number_array[2..4] = #{number_array[2..4]}"
puts "number_array.join('-') = #{number_array.join('-')}"

puts "-" * 40

number_array_2[2] = "a"
puts "number_array_2 = #{number_array_2}"
