def test_age(age)
    if age <10
        puts "Age #{age} is no old enough."
    elsif age == 10
        puts "Age #{age} is right."
    else
        puts "Age #{age} is too old."
    end 
end

age_array = [8, 10, 12]

# For loop is not Ruby traditional style.
for age in age_array
    test_age(age)
end

puts "-" * 40

# .each is Ruby traditional style.
age_array.each do |age|
    test_age(age)
end

puts "-" * 40

# .each is Ruby traditional style.
age_array.each {|age| test_age(age)}

puts "-" * 40

(9..11).each do |age|
    test_age(age)
end

puts "-" * 40

(9...12).each do |age|
    test_age(age)
end

puts "-" * 40

i = 0
while i < age_array.length
    test_age(age_array.shift)
end
