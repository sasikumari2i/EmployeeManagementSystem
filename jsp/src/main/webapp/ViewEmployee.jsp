<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ideas2it.project.model.dto.EmployeeDTO,com.ideas2it.project.model.dto.AddressDTO,
	java.util.List,java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Insert title here</title>
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
		<div class="dropdown">
			<button class="dropbtn">
				Manage Employee <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="EmployeeServlet?servletId=1"> Add New Employee</a>
			</div>
		</div>
	</div>
	<br>
	<h3>Employee List</h3>
	<br>
	<c:if test="${employeeList.isEmpty()}">
		<p>No Employees Available!!</p>
	</c:if>
	<c:if test="${!employeeList.isEmpty()}">
		<table border="1">
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
				<c:forEach var="employee" items="${employeeList}">
					<tr>
						<td><c:out value="${employee.id}" /></td>
						<td><c:out value="${employee.name}" /></td>
						<td><c:out value="${employee.dob}" /></td>
						<td><c:out value="${employee.salary}" /></td>
						<td><c:out value="${employee.email}" /></td>
						<td><c:out value="${employee.contact}" /></td>
						<td>
							<div class="dropdown">
								<div class="dropBackground">
									<button class="dropbtn">
										More <i class="fa fa-caret-down"></i>
									</button>
									<div class="dropdown-content">
										<a
											href="EmployeeServlet?id=<c:out value='${employee.id}'/>&servletId=10">View
											Address</a> <a
											href="EmployeeServlet?id=<c:out value='${employee.id}'/>&servletId=13">Edit</a>
										<a
											href="EmployeeServlet?id=<c:out value='${employee.id}'/>&servletId=12">Delete</a>
										<a
											href="EmployeeServlet?id=<c:out value='${employee.id}'/>&servletId=7">Assign</a>
										<a
											href="EmployeeServlet?id=<c:out value='${employee.id}'/>&servletId=9">UnAssign</a>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="back">
			<input type="button" value="Back" onclick="history.back()">
		</div>
	</c:if>
</body>
</html>