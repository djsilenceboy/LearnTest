<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of books</title>
</head>
<body>
	<h1 align="center">List of books in my library :</h1>
	<table width="50%" align="center">
		<tr>
			<th width="20%" align="center">S. No.</th>
			<th width="50%" align="center">Title</th>
			<th width="30%" align="center">Author</th>
		</tr>
		<%int i = 1; %>
		<c:forEach items="${books}" var="book">
			<tr>
				<td width="20%" align="center"><%= i++ %></td>
				<td width="50%" align="center"><a href="${pageContext.request.contextPath}/displayBookTOC.htm?isbn=${book.isbn}">${book.title.titleValue}</a></td>
				<td width="30%" align="center">${book.author}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
