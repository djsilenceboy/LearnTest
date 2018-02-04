<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Process Analyst Request</title>
</head>
<body>
	<h1>Process Analyst Request</h1>
	<%
		String toBeDeleted = (String)request.getParameter( "checkbox" );
		String toBeAdded = (String)request.getParameter( "addname" );
	%>
	<h2><%=toBeDeleted%></h2>
	<br>
	<h2><%=toBeAdded%></h2>
</body>
</html>
