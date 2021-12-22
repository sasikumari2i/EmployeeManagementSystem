<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>View Employee Details</title>
</head>
<body>
	<div class="navbar">
		<a href="index.html">Home</a>
		<div class="dropdown">
			<button class="dropbtn">
				Go To <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="projectView.html">Projects</a>
			</div>
		</div>
	</div>
	<c:choose>
	<c:when test="${isDeleted == true}">
	<p>Removed Successfully</p>
	</c:when>
	<c:when test="${isAdded == true}">
	<p>Address Successfully added</p>
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
	<c:when test="${notDeleted == true || notUnAssigned == true || notAssigned == true || notMerged == true || notAdded == true}">
	<p>Something Went wrong try again!!</p>
	</c:when>
	</c:choose>
	<!-- <c:if test="${isDeleted == true}">
		<p>Removed Successfully</p>
	</c:if>
	<c:if test="${notDeleted == true}">
		<p>Something Went wrong try again!!</p>
	</c:if>
	<c:if test="${isAdded == true}">
		<p>Address Successfully added</p>
	</c:if>
	<c:if test="${notAdded == true}">
		<p>Something Went wrong try again!!</p>
	</c:if>
	<c:if test="${notMerged == true}">
		<p>Something Went wrong try again!!</p>
	</c:if>
	<c:if test="${isMerged == true}">
		<p>Updated Successfully</p>
	</c:if>
	<c:if test="${notAssigned == true}">
		<p>Something Went wrong try again!!</p>
	</c:if>
	<c:if test="${isAssigned == true}">
		<p>Assigned Successfully</p>
	</c:if>
	<c:if test="${notUnAssigned == true}">
		<p>Something Went wrong try again!!</p>
	</c:if>
	<c:if test="${unAssigned == true}">
		<p>UnAssigned Successfully</p>
	</c:if> -->
	<h3>Display Employee Details page</h3>
	<p>Click here to display All the Employees</p>
	<a href="EmployeeServlet?servletId=14">View All Employees</a>
	<br>
	<p>Click here to display a specific Employee</p>
	<a href="ViewSpecificEmployee.jsp">View By Employee ID</a>
	<br>
	<div class="back">
		<input type="button" value="Back" onclick="history.back()">
	</div>
</body>
</html>