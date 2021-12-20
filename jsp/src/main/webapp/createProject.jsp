<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Project</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	%>
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
				Project <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="ViewProjectDetails.jsp">View Projects</a> <br>
			</div>
		</div>
	</div>
	<c:if test="${projectDTO == null}">
		<form method="post" action="ProjectServlet">
			<input type="hidden" name="servletId" value='5'>
	</c:if>
	<c:if test="${projectDTO != null}">
		<form method="post" action="ProjectServlet">
			<input type="hidden" name="servletId" value='8'>
	</c:if>
	<c:if test="${projectDTO != null}">
		<h2>Edit Project</h2>
	</c:if>
	<c:if test="${projectDTO == null}">
		<h2>Create Project</h2>
	</c:if>
	<c:if test="${projectDTO != null}">
		<input type="hidden" name="id"
			value="<c:out value='${projectDTO.id}' />" />
	</c:if>
	<label> Name : </label>
	<input type="text" value="<c:out value='${projectDTO.name}'/>"
		name="name" required="required">
	<br>
	<label> Start Date : </label>
	<input type="date" value="<c:out value='${projectDTO.startDate}'/>"
		name="startDate" required="required">
	<br>
	<label> End Date : </label>
	<input type="date" value="<c:out value='${projectDTO.endDate}'/>"
		name="endDate" required="required">
	<br>
	<label> Status : </label>
	<select name="status" value="<c:out value='${projectDTO.status}'/>">
		<option value="active">Active</option>
		<option value="inactive">Inactive</option>
	</select>
	<br>
	<label> Domain : </label>
	<input type="text" value="<c:out value='${projectDTO.domain}'/>"
		name="domain" required="required">
	<br>
	<div class="back">
		<input type="submit" name="Submit"> <input type="button"
			value="Back" onclick="history.back()">
	</div>
	</form>

</body>
</html>