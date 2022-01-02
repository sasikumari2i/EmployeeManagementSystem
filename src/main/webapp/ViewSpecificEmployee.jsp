<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Specific Employee</title>
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
				<a href="projectView.jsp">Projects</a> <a href="employeeView.jsp">Employee</a>
			</div>
		</div>
	</div>
	<c:if test="${notAvailable == true}">
		<p>Employee ID not available, try another</p>
	</c:if>
	<form method="get" action="viewEmpById">
		<h4>Enter the Employee ID to be displayed</h4> Employee ID
		: <input type="number" name="id" max="9999" required="required"><br>
		<div class="back">
			<input type="submit" name="Submit"><br> <input
				type="button" value="Back" onclick="history.back()">
		</div>
	</form>
</body>
</html>