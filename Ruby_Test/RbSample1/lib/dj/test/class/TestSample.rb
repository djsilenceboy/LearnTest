require "dj/test/class/MySimple.rb"

sample = MySimple.new()

puts "food = #{sample.food}"

puts "drink = #{sample.drink}"
sample.drink = "Tea"
puts "drink = #{sample.drink}"

puts "count = #{sample.count}"
puts "count = #{sample.count}"
