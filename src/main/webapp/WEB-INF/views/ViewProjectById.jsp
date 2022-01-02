<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project List</title>
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
				<a href="createProject.jsp"> Add New Project</a>
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
				<th>Status</th>
				<th>Domain</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${project.id}" /></td>
				<td><c:out value="${project.name}" /></td>
				<td><c:out value="${project.startDate}" /></td>
				<td><c:out value="${project.endDate}" /></td>
				<td><c:out value="${project.status}" /></td>
				<td><c:out value="${project.domain}" /></td>
				<td><div class="dropdown">
						<div class="dropBackground">
							<button class="dropbtn">
								More <i class="fa fa-caret-down"></i>
							</button>
							<div class="dropdown-content">
								<a
									href="getProUpdated?id=<c:out value='${project.id}'/>">Edit</a>
								<a
									href="deleteProject?id=<c:out value='${project.id}'/>">Delete</a>
								<a
									href="viewProDetails?id=<c:out value='${project.id}'/>">Full Details</a>
							</div>
						</div>
					</div></td>
			</tr>
		</tbody>
	</table>
	<div class="back">
		<input type="button" value="Back" onclick="history.back()">
	</div>
</body>
</html>