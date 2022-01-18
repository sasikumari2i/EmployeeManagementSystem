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
				<a href="projectView.jsp">Projects</a> <a href="employeeView.jsp">Employee</a>
			</div>
		</div>
	</div>

	<h3>Employee Details</h3>
	<br>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>DOB</th>
				<th>salary</th>
				<th>Email</th>
				<th>Contact</th>
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
			</tr>
		</tbody>
	</table>
	<p>Address List</p>
	<a href="EmployeeServlet?id=<c:out value='${employeeId}'/>&servletId=5">Add
		Address</a>

	<c:if test="${addressList.isEmpty()}">
		<p>Address List is Empty !!</p>
	</c:if>
	<c:if test="${!addressList.isEmpty()}">
		<form method="post" action="EmployeeServlet">
			<input type="hidden" name="servletId" value="11"> <input
				type="hidden" name="id" value="<c:out value='${employeeId}' />" />
			<table border="1">
				<thead>
					<tr>
						<th></th>
						<th>ID</th>
						<th>Door No</th>
						<th>Street</th>
						<th>Landmark</th>
						<th>City</th>
						<th>Pincode</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="address" items="${addressList}">
						<tr>
							<td><input type="checkbox" name="selected"
								value="${address.addressId}" /></td>
							<td>${address.serialId}</td>
							<td>${address.doorNo}</td>
							<td>${address.street}</td>
							<td>${address.landMark}</td>
							<td>${address.city}</td>
							<td>${address.pincode}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<p>Choose the addresses to be deleted</p>
			<input type="submit" value="Delete" />
		</form>
	</c:if>
	<p>Assigned Projects</p>
	<c:if test="${projectDTOSet.isEmpty()}">
		<p>No Projects available to Assign !!</p>
	</c:if>
	<c:if test="${!projectDTOSet.isEmpty()}">
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
				<input type="submit" value="UnAssign" />
			</div>
		</form>
	</c:if>
	<h3>Other Projects</h3>
	<c:if test="${projectDTOList.isEmpty()}">
		<p>No projects !!</p>
	</c:if>
	<c:if test="${!projectDTOList.isEmpty()}">
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
			<div class="back">
				<input type="submit" value="Assign" />
			</div>
		</form>
		<br>
	</c:if>
	<div class="back">
		<input type="button" value="Back" onclick="history.back()">
	</div>
</body>
</html>