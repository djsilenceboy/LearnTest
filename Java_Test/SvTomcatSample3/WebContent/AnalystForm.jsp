<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Analyst Management</title>
</head>
<body>
	<%@ page import="java.util.*"%>
	<h1>Analyst Management Form</h1>
	<form action="ProcessAnalyst" method="POST">
		<table>
		<%
		ArrayList<String> analysts = (ArrayList<String>)request.getAttribute( "data" );
		if (analysts == null)
		{
		%>
			<h2>Attribute is null</h2>
		<%
		}
		else
		{
			for (int i = 0; i < analysts.size(); i++)
			{
				String analyst = (String)analysts.get( i );
		%>
			<tr>
				<td><input type="checkbox" name="checkbox" value="<%=analyst%>"></td>
				<td><%=analyst%></td>
			</tr>
		<%
			}
		}
		%>
		</table>
		<input type="submit" name="delete" value="Delete Selected">
		<p>
		<input type="text" name="addname" size="40">
		<input type="submit" name="add" value="Add New Analyst">
	</form>
</body>
</html>
