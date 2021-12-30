<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Manage Employees</title>
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
				<a href="projectView.jsp">Projects</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">
				Manage Employee <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="createEmp"> Add New Employee</a> <a
					href="ViewEmployeeDetails.jsp">View Employees</a> <br>
			</div>
		</div>
	</div>
	<h3>Manage Employee Page</h3>
	<p>You can manage your employees here</p>
	<c:if test="${isCreated == true}">
		<p>Employee Created Successfully</p>
	</c:if>
	<div class="back">
		<input type="button" value="Back" onclick="history.back()">
	</div>
</body>
</html>