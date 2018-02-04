<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Student List</title>
</head>
<body>
	<%@ page import="java.util.*"%>
	<a href="index.html">Return</a>
	<p>
	<h1>Show Student List</h1>

	<%
		ArrayList<Vector<Object>> students = (ArrayList<Vector<Object>>)request.getAttribute( "data" );
		if ((students != null) && (students.size() > 0))
		{
	%>
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Sex</th>
			<th>Age</th>
			<th>Score</th>
		</tr>
		<%
			for (int i = 0; i < students.size(); i++)
				{
					Vector<Object> v = (Vector<Object>)students.get( i );
		%>
		<tr>
			<td><%=v.elementAt( 0 )%></td>
			<td><%=v.elementAt( 1 )%></td>
			<td><%=v.elementAt( 2 )%></td>
			<td><%=v.elementAt( 3 )%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
		else
		{
	%>
	No student information found.
	<%
		}
	%>
</body>
</html>
