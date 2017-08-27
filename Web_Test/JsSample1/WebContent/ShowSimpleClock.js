function startClock()
{
	var theTime = new Date();
	var hours = theTime.getHours();
	var minutes = theTime.getMinutes();
	var seconds = theTime.getSeconds();
	var ap = (hours >= 12) ? "pm" : "am";

	if (hours > 12)
	{
		hours -= 12;
	}

	if (hours < 1)
	{
		hours = 12;
	}

	if (minutes < 10)
	{
		minutes = "0" + minutes;
	}

	if (seconds < 10)
	{
		seconds = "0" + seconds;
	}

	var clockPlace = document.getElementById("my_clock");
	clockPlace.innerHTML = hours + ":" + minutes + ":" + seconds + " " + ap;

	window.setTimeout("startClock()", 1000);
}

startClock();
