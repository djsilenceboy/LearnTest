var div1 = document.getElementById("div1");
var div2 = document.getElementById("div2");

div1.onmouseover = function ()
{
	div1.innerHTML = "Now, it is over me!";
	div2.innerHTML = "So, it is not over me!";
};

div1.onmouseout = function ()
{
	div1.innerHTML = "This is div1.";
	div2.innerHTML = "That is div2.";
};
