<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<p>The time on the server is ${serverTime}.</p>
<a href="${pageContext.request.contextPath}/book_list">List all books.</a>
</body>
</html>
