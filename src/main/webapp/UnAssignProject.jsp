<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UnAssign Project</title>
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
	<br>
	<c:if test="${projectDTOSet.isEmpty()}">
		<p>No Projects assigned for the employee !!</p>
	</c:if>
	<c:if test="${!projectDTOSet.isEmpty()}">
		<h3>Project List</h3>
		<p>Choose the Projects to be UnAssigned from this Employee</p>
		<br>
		<form method="post" action="EmployeeServlet">
			<input type="hidden" name="servletId" value='8'> <input
				type="hidden" name="id" value="<c:out value='${employeeId}' />" />
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<c:forEach var="project" items="${projectDTOSet}">
					<tr>
						<td><input type="checkbox" name="selected"
							value="${project.id}" /></td>
						<td>${project.id}</td>
						<td>${project.name}</td>
					</tr>
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