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
	<h3>Project Details</h3>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Domain</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${project.id}" /></td>
				<td><c:out value="${project.name}" /></td>
				<td><c:out value="${project.startDate}" /></td>
				<td><c:out value="${project.endDate}" /></td>
				<td><c:out value="${project.domain}" /></td>
				<td><c:out value="${project.status}" /></td>
			</tr>
		</tbody>
	</table>
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
			<input type ="submit" value="Assign"/>
			</div>
		</form>
		<br>
	</c:if>
	<c:if test="${employeeDTOSet.isEmpty()}">
		<p>No Employees assigned for the project !!</p>
	</c:if>
	<c:if test="${!employeeDTOSet.isEmpty()}">
		<h3>Employee List</h3>
		<p>Choose the Employees to be UnAssigned from this Project</p>
		<br>
		<form method="post" action="ProjectServlet">
			<input type="hidden" name="servletId" value='6'> <input
				type="hidden" name="id" value="<c:out value='${projectId}' />" />
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<c:forEach var="employee" items="${employeeDTOSet}">
					<tr>
						<td><input type="checkbox" name="selected"
							value="${employee.id}" /></td>
						<td>${employee.id}</td>
						<td>${employee.name}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="back">
				<input type="submit" value="UnAssign" />
			</div>
		</form>
	</c:if>
</body>
</html>