function getXMLHTTPRequest() {
	var request = false;
	var handleType = "No handle";

	if (window.XMLHTTPRequest) {
		request = new XMLHTTPRequest();
		var handleType = "XMLHTTPRequest";
	} else if (window.ActiveXObject) {
		try {
			request = new ActiveXObject("Msml2.XMLHTTP");
			var handleType = "Msml2.XMLHTTP";
		} catch (ex) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
				var handleType = "Microsoft.XMLHTTP";
			} catch (ex2)
			{}
		}
	}

	document.getElementById("HandleType").innerHTML = handleType;

	return request;
}

function getServerTime() {
	var myurl = "getServerTime.php";
	var myrand = parseInt(Math.random()*999999);
	var modurl = myurl + "?rand=" + myrand;
	var http = getXMLHTTPRequest();

	http.open("GET", modurl, true);
	http.onreadystatechange = handleResponse;
	http.send(null);
}

function handleResponse {
	document.getElementById("AjaxStatus").innerHTML = http.readyState + "," + http.status;

	if (http.readyState == 4) {
		if (http.status == 200) {
			document.getElementById("ServerTime").InnerHtml = http.responseText;
		}
	}
}
