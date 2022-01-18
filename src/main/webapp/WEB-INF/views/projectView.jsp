<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<div class="dropdown">
			<button class="dropbtn">
				Manage Project <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="createPro">Add New Project</a> <br> <a
					href="ViewProjectDetails.jsp">View Projects</a>
			</div>
		</div>
	</div>
	<h3>Manage Project Page</h3>
	<p>You can manage your project here</p>
	<c:if test="${isCreated == true}">
		<p>Project Created Successfully</p>
	</c:if>
	<div class="back">
		<input type="button" value="Back" onclick="history.back()">
	</div>
</body>
</html>