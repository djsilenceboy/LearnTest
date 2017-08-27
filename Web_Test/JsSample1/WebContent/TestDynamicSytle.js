var div1 = document.getElementById("div1");
var div2 = document.getElementById("div2");

div1.onmouseover = function ()
{
	div1.style.backgroundColor = "#00FF00";
	div2.style.backgroundColor = "#FFFFFF";
};

div1.onmouseout = function ()
{
	div1.style.backgroundColor = "#FFFFFF";
	div2.style.backgroundColor = "#00FF00";
};
