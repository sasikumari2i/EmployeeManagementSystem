<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Project Details</title>
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
				<a href="employeeView.jsp">Employees</a>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${isRemoved == true}">
			<p>Removed Successfully</p>
		</c:when>
		<c:when test="${isMerged == true}">
			<p>Updated Successfully</p>
		</c:when>
		<c:when test="${isAssigned == true}">
			<p>Assigned Successfully</p>
		</c:when>
		<c:when test="${unAssigned == true}">
			<p>UnAssigned Successfully</p>
		</c:when>
		<c:when
			test="${notUnAssigned == true || notRemoved == true || notMerged == true || notAssigned == true}">
			<p>Something Went wrong try again!!</p>
		</c:when>
	</c:choose>
	<h3>Display Project Details page</h3>
	<p>Click here to display All the Projects</p>
	<a href="ProjectServlet?servletId=10">View All Projects</a>
	<br>
	<p>Click here to display a specific Project</p>
	<a href="ViewSpecificProject.jsp">View By Project ID</a>
	<br>
	<div class="back">
		<input type="button" value="Back" onclick="history.back()">
	</div>
</body>
</html>