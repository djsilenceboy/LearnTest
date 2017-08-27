function newPage()
{
	var theName = document.getElementById("yourname").value
	document.writeln("Your name is " + theName+ ".<br>");
	document.open();
	document.writeln("<H1>Welcome! " + theName + "!</H1>");
	document.close();
}
