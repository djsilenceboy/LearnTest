function doConfirm()
{
	var isKeep = window.confirm("Keep in this page?");

	if (!isKeep)
	{
		document.open();
		document.writeln("<h1>Welcome to new page!</h1>");
		document.close();
	}
}

var theConfirmHandle = window.setTimeout("doConfirm()", 5000);

document.write("Confirm handle = " + theConfirmHandle + "<br>");
