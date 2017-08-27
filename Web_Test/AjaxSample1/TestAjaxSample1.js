function getXMLHTTPRequest() {
	var xmlhttp;
	var handleType = "None";

	try {
		if (window.XMLHttpRequest) { // For IE7+, Firefox, Chrome, Opera, Safari.
			xmlhttp = new XMLHttpRequest();
			handleType = "XMLHttpRequest";
		} else { // For IE6, IE5.
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			handleType = "Microsoft.XMLHTTP";
		}
   	} catch (e) {
	}

	console.log("Handle type = " + handleType);

	return xmlhttp;
}

function getServerTime() {
	var myurl = "/Web_Test/PhpSample1/test_getTimeXml.php";
	var myrand = parseInt(Math.random()*999999);
	myurl += "?rand=" + myrand;
	startTimer = true;
	
	console.log("URL = " + myurl);

	xmlhttp.open("GET", myurl, true);
	xmlhttp.onreadystatechange = handleResponse;
	xmlhttp.send();
}

function stopServerTime() {
	startTimer = false;
	clearTimeout(myTimer);
}

function handleResponse() {
	console.log("Ajax status = " + xmlhttp.readyState + "," + xmlhttp.status);

	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			console.log("Server time = " + xmlhttp.responseText);
			document.getElementById("ServerTime").innerHTML = xmlhttp.responseText;

			if (startTimer) {
				myTimer = window.setTimeout("getServerTime()", 1000);
			}
		}
	}
}

var xmlhttp = getXMLHTTPRequest();
var startTimer = true;
var myTimer = null;