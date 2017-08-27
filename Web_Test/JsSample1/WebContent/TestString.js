function testOne()
{
	var name = "Jack";
	var age = 18;

	testTwo(name, age);
}

function testTwo(name, age)
{
	var content = "<div id=" + name + ">" + name + "</div><br>";
	content += "<div id=" + age + ">" + age + "</div><br>";

	document.writeln(content);
}

testOne();
