<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ideas2it.project.model.dto.EmployeeDTO,com.ideas2it.project.model.dto.AddressDTO,
	java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Details</title>
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
				<a href="createEmployee.jsp"> Add New Employee</a>
			</div>
		</div>
	</div>
	<br>
	<h3>Employee List</h3>
	<br>
	<table width="80%" border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>DOB</th>
				<th>salary</th>
				<th>Email</th>
				<th>Contact</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><c:out value="${employee.id}" /></td>
				<td><c:out value="${employee.name}" /></td>
				<td><c:out value="${employee.dob}" /></td>
				<td><c:out value="${employee.salary}" /></td>
				<td><c:out value="${employee.email}" /></td>
				<td><c:out value="${employee.contact}" /></td>
				<td><div class="dropdown">
						<div class="dropBackground">
							<button class="dropbtn">
								More <i class="fa fa-caret-down"></i>
							</button>
							<div class="dropdown-content">
								<a href="viewDetails?id=<c:out value='${employee.id}'/>">View
									Details</a> <a
									href="getEmpUpdated?id=<c:out value='${employee.id}'/>">Edit</a>
								<a href="deleteEmployee?id=<c:out value='${employee.id}'/>">Delete</a>
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