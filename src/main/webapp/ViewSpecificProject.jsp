<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Specific Project</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="navbar">
		<a href="index.html">Home</a>
		<div class="dropdown">
			<button class="dropbtn">
				Go To <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="projectView.html">Projects</a> <a href="employeeView.jsp">Employee</a>
			</div>
		</div>
	</div>
	<c:if test="${notAvailable == true}">
		<p>Enter an available Project ID</p>
	</c:if>
	<form method="get" action="ProjectServlet">
		<h4>Enter the Project ID to be displayed</h4>
		<input type="hidden" name="servletId" value="11"> Project ID :
		<input type="number" name="id" max="9999"><br>
		<div class="back">
			<input type="submit" name="Submit"><br> <input
				type="button" value="Back" onclick="history.back()">
		</div>
	</form>
</body>
</html>