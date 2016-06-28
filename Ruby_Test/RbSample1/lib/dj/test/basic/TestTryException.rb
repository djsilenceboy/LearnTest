def convert_number(number)
    begin
        puts "number = #{number}"
        return Integer(number)
    rescue
        puts "#{number} is not a number."
        return nil
    end
end

convert_number("10")
convert_number("a")
