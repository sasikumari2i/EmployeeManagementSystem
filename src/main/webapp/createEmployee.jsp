<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.ideas2it.project.model.dto.EmployeeDTO,java.util.List,java.util.ArrayList,com.ideas2it.project.model.dto.AddressDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Create Employee</title>
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
				<a href="projectView.jsp">Projects</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn">
				Employee <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="ViewEmployeeDetails.jsp">View Employees</a> <br>
			</div>
		</div>
	</div>
	<c:if test="${employeeId == null}">
		<c:if test="${employeeDTO == null}">
			<form method="post" action="EmployeeServlet">
				<input type="hidden" name="servletId" value='2'>
		</c:if>
		<c:if test="${employeeDTO != null}">
			<form method="post" action="EmployeeServlet">
				<input type="hidden" name="id"
					value="<c:out value='${employeeDTO.id}' />" /> <input
					type="hidden" name="servletId" value='3'>
		</c:if>
		<c:if test="${employeeDTO != null}">
			<h2>Edit Employee</h2>
		</c:if>
		<c:if test="${employeeDTO == null}">
			<h2>Create Employee</h2>
		</c:if>
		<c:if test="${notCreated == true}">
			<p>Employee not created</p>
		</c:if>
		<c:if test="${notCreated == true}">
			<p>Employee not created</p>
		</c:if>
		<c:if test="${isDuplicate == true}">
			<p>Email or mobile is already given for another user</p>
		</c:if>
		<c:if test="${employeeDTO != null}">
			<input type="hidden" name="id"
				value="<c:out value='${employeeDTO.id}' />" />
			<input type="hidden" name="addressList"
				value="<c:out value='${addressList}' />" />
		</c:if>
		<div class="formSpacing">
			<label> Name : </label> <input type="text"
				value="<c:out value='${employeeDTO.name}'/>" name="name"
				required="required"> <br>
		</div>
		<div class="formSpacing">
			<label> DOB : </label> <input type="date"
				value="<c:out value='${employeeDTO.dob}'/>" name="dob"
				required="required"> <br>
		</div>
		<c:if test="${notValidDob == true}">
			<form method="post" action="EmployeeServlet">
				<input type="hidden" name="servletId" value='2'>
				<p>Age should be between 18 to 60</p>
		</c:if>
		<div class="formSpacing">
			<label> Salary : </label> <input type="number"
				value="<c:out value='${employeeDTO.salary}'/>" name="salary"
				max="10000000" required="required"> <br>
		</div>
		<div class="formSpacing">
			<label> Mobile : </label> <input type="tel"
				value="<c:out value='${employeeDTO.contact}'/>" name="contact"
				pattern="[6789][0-9]{9}" required="required"> <br>
		</div>
		<c:if test="${isDuplicateContact}">
			<form method="post" action="EmployeeServlet">
				<input type="hidden" name="servletId" value='2'>
				<p>Phone number already available, try another</p>
		</c:if>
		<div class="formSpacing">
			<label> Email : </label> <input type="email"
				value="<c:out value='${employeeDTO.email}'/>" name="email"
				required="required"> <br>
		</div>
		<c:if test="${isDuplicateEmail}">
			<form method="post" action="EmployeeServlet">
				<input type="hidden" name="servletId" value='2'>
				<p>Email already available, try another</p>
		</c:if>
	</c:if>
	<c:if test="${employeeDTO != null}">
		<%
		EmployeeDTO employeeDTO = (EmployeeDTO) request.getAttribute("employeeDTO");
		%>
		<br>
	</c:if>
	<c:if test="${employeeDTO == null || employeeId != null}">
		<c:if test="${employeeId != null }">
			<form method="post" action="EmployeeServlet">
				<input type="hidden" name="servletId" value='4'>
				<header>Enter Address details</header>
				<input type="hidden" name="id"
					value="<c:out value='${employeeId}' />" />
		</c:if>
		<div class="formSpacing">
			<label> Door No : </label> <input type="text" name="doorNo"
				required="required"> <br>
		</div>
		<div class="formSpacing">
			Land Mark : <input type="text" name="landmark" required="required">
			<br>
		</div>
		<div class="formSpacing">
			<label>Street : </label> <input type="text" name="street"
				required="required"> <br>
		</div>
		<div class="formSpacing">
			<label> City : </label> <input type="text" name="city"
				required="required"> <br>
		</div>
		<div class="formSpacing">
			<label> Pincode : </label> <input type="tel" name="pincode"
				pattern="[1-9][0-9]{5}" required="required"> <br>
		</div>
	</c:if>
	<div class="back">
		<input type="submit" name="Submit"> <input type="button"
			value="Back" onclick="history.back()">
	</div>
	</form>
</body>
</html>