<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Employee</title>
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
				<a href="projectView.html">Projects</a> <a href="employeeView.jsp">Employee</a>
			</div>
		</div>
	</div>
	<br>
	<c:if test="${employeeDTOList.isEmpty()}">
		<p>No Projects available to Assign !!</p>
	</c:if>
	<c:if test="${!employeeDTOList.isEmpty()}">
		<h3>Employee List</h3>
		<p>Choose from the below Employees to assign for this project</p>
		<br>
		<form method="post" action="ProjectServlet">
			<input type="hidden" name="servletId" value="1"> <input
				type="hidden" name="id" value="<c:out value='${projectId}' />" />
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<c:forEach var="employee" items="${employeeDTOList}">
					<tr>
						<td><input type="checkbox" name="selected"
							value="${employee.id}" /></td>
						<td>${employee.id}</td>
						<td>${employee.name}</td>
					<tr>
				</c:forEach>
			</table>
			<div class="back">
				<input type="submit" value="Submit" />
			</div>
		</form>
		<br>
	</c:if>
</body>
</html>