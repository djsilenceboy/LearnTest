input_filename = "etc/SampleInput.txt"
puts "input_filename = #{input_filename}"

input_file_handle = open(input_filename)
puts "input_file_handle = #{input_file_handle}"
in_data = input_file_handle.read
puts "input data length = #{in_data.length}"

input_file_handle.close

puts "-" * 40
puts in_data
puts "-" * 40

output_filename = "etc/SampleOutput.txt"
puts "output_filename = #{output_filename}"

output_file_handle = open(output_filename, "w")
puts "output_file_handle = #{output_file_handle}"
output_file_handle.write(in_data)
puts "output data length = #{in_data.length}"

output_file_handle.close
