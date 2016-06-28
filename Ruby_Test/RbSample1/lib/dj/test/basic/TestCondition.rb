puts "true && false = #{true && false}"
puts "true && true = #{true && true}"
puts "false && true = #{false && true}"
puts "false && false = #{false && false}"

puts "true || false = #{true || false}"
puts "true || true = #{true || true}"
puts "false || true = #{false || true}"
puts "false || false = #{false || false}"

puts "!false = #{!false}"
puts "!true = #{!true}"

puts "-" * 40

puts "0 != 0 = #{0 != 0}"
puts "0 != 1 = #{0 != 1}"
puts "1 != 0 = #{1 != 0}"
puts "1 != 1 = #{1 != 1}"

puts "0 == 0 = #{0 == 0}"
puts "0 == 1 = #{0 == 1}"
puts "1 == 0 = #{1 == 0}"
puts "1 == 1 = #{1 == 1}"

puts "-" * 40

puts "true && 1 = #{true && 1}"
puts "true && ok = #{true && 'ok'}"
puts "false && 1 = #{false && 1}"

puts "true || 1 = #{true || 1}"
puts "true || ok = #{true || 'ok'}"
puts "false || ok = #{false || 'ok'}"

puts "-" * 40

score = nil
result = score || -1
puts "result = #{result}"
