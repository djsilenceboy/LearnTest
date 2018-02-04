<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock Ratings</title>
</head>
<body>
	<%@ page import="java.util.*"%>
	<h1>Stock Ratings</h1>

	<%
		ArrayList<Vector<String>> stocks = (ArrayList<Vector<String>>)request.getAttribute( "data" );
		if ((stocks != null) && (stocks.size() > 0))
		{
	%>
	<form action="AddRating" method="POST">
		<table border="1">
			<tr>
				<th>Analyst</th>
				<th>Ticker</th>
				<th>Rating</th>
			</tr>
			<%
				for (int i = 0; i < stocks.size(); i++)
					{
						Vector<String> v = (Vector<String>)stocks.get( i );
						String analyst = (String)v.elementAt( 0 );
						String ticker = (String)v.elementAt( 1 );
						String rating = (String)v.elementAt( 2 );
			%>
			<tr>
				<td><%=analyst%></td>
				<td><%=ticker%></td>
				<td><%=rating%></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td><select name="analysts">
						<%
							ArrayList<String> analysts = (ArrayList<String>)request.getAttribute( "analysts" );
								for (int i = 0; i < analysts.size(); i++)
								{
									String analyst = (String)analysts.get( i );
						%>
						<option value="<%=analyst%>">
							<%=analyst%>
							<%
								}
							%>
						
				</select></td>
				<td><select name="stocks">
						<%
							ArrayList<String> unratedStocks = (ArrayList<String>)request.getAttribute( "unrated" );
								for (int i = 0; i < unratedStocks.size(); i++)
								{
									String ticker = (String)unratedStocks.get( i );
						%>
						<option value="<%=ticker%>">
							<%=ticker%>
							<%
								}
							%>
						
				</select></td>
				<td><select name="ratings">
						<option value="Run away!">Run away!
						<option value="Cound be worse!">Cound be worse!
						<option value="A bit of OK!">A bit of OK!
						<option value="Take a chance!">Take a chance!
						<option value="Smashing!">Smashing!
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit Rating"></td>
			</tr>
		</table>
	</form>
	<%
		}
		else
		{
	%>
	No stock information found.
	<%
		}
	%>
</body>
</html>
