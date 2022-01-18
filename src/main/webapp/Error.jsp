<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
	<h3>Sorry! Something went wrong</h3>
	<p><%=exception.getMessage()%><br />
	</p>
	<a href="welcome.jsp">Click here for home page</a>
</body>
</html>