<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Table of Content</title>
</head>
<body>
	<h1 align="center">Book : ${book.title.titleValue}, written by ${book.author}</h1>
	<h2>Table of content :</h2>
	<%int i = 1; %>
	<table width="100%">
		<c:forEach items="${book.chapters}" var="chapter">
			<tr>
				<td width="5%" align="center"><%= i++ %></td>
				<td width="95%" align="left">${chapter.title.titleValue}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
