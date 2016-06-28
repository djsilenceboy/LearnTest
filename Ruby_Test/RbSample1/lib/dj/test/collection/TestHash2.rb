require "dj/test/module/TestDict.rb"

# create a mapping of state to abbreviation
states = TestDict.new()
TestDict.set(states, 'Oregon', 'OR')
TestDict.set(states, 'Florida', 'FL')
TestDict.set(states, 'California', 'CA')
TestDict.set(states, 'New York', 'NY')
TestDict.set(states, 'Michigan', 'MI')

# create a basic set of states and some cities in them
cities = TestDict.new()
TestDict.set(cities, 'CA', 'San Francisco')
TestDict.set(cities, 'MI', 'Detroit')
TestDict.set(cities, 'FL', 'Jacksonville')

# add some more cities
TestDict.set(cities, 'NY', 'New York')
TestDict.set(cities, 'OR', 'Portland')

# puts out some cities
puts "-" * 40
puts "NY State has: #{TestDict.get(cities, 'NY')}"
puts "OR State has: #{TestDict.get(cities, 'OR')}"

# puts some states
puts "-" * 40
puts "Michigan's abbreviation is: #{TestDict.get(states, 'Michigan')}"
puts "Florida's abbreviation is: #{TestDict.get(states, 'Florida')}"

# do it by using the state then cities dict
puts "-" * 40
puts "Michigan has: #{TestDict.get(cities, TestDict.get(states, 'Michigan'))}"
puts "Florida has: #{TestDict.get(cities, TestDict.get(states, 'Florida'))}"

# puts every state abbreviation
puts "-" * 40
TestDict.list(states)

# puts every city in state
puts "-" * 40
TestDict.list(cities)

puts "-" * 40
# by default ruby says "nil" when something isn't in there
state = TestDict.get(states, 'Texas')

if !state
    puts "Sorry, no Texas."
end

# default values using ||= with the nil result
city = TestDict.get(cities, 'TX', 'Does Not Exist')
puts "The city for the state 'TX' is: #{city}"
