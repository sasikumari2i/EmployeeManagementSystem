<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Project</title>
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
	<c:if test="${projectDTOList.isEmpty()}">
		<p>No Employees available to Assign !!</p>
	</c:if>
	<c:if test="${!projectDTOList.isEmpty()}">
		<h3>Project List</h3>
		<p>Choose from the below Projects to assign for the Employee</p>
		<br>
		<br>
		<form method="post" action="EmployeeServlet">
			<input type="hidden" name="servletId" value='6'> <input
				type="hidden" name="id" value="<c:out value='${employeeId}' />" />
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<c:forEach var="project" items="${projectDTOList}">
					<tr>
						<td><input type="checkbox" name="selected"
							value="${project.id}" /></td>
						<td>${project.id}</td>
						<td>${project.name}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<div class="back">
			<input type="submit" value="Submit" />
		</div>

	</c:if>
</body>
</html>