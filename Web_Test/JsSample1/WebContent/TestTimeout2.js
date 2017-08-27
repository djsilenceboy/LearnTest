function doConfirm2(message)
{
	var isKeep = window.confirm("Keep in this page " + message + "?");

	if (!isKeep)
	{
		document.open();
		document.writeln("<h1>Welcome to new page 2!</h1>");
		document.close();
	}
}

var theConfirmHandle2 = window.setTimeout(function()
{
	doConfirm2("food");
}, 5000);

document.write("Confirm handle 2 = " + theConfirmHandle2 + "<br>");
