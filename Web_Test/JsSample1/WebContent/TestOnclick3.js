function clickMe()
{
	window.alert("Do not click me!");
}

function clickYou()
{
	window.alert("Please click you!");
}

var clickMeButton = document.getElementById("clickMe");
clickMeButton.onclick = clickMe;

var clickYouButton = document.getElementById("clickYou");
clickYouButton.onclick = clickYou;
