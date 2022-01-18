<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>Create Employee</title>
</head>
<body>
	<div class="navbar">
		<a href="index.jsp">Home</a>
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
		<c:if test="${(null == employee.name) || isDuplicate}">
			<form method="post" action="saveEmp">Create Employee
		</c:if>
		<c:if test="${null != employee.name}">
			<form method="post" action="updateEmployee">
				Edit Employee <input type="hidden" name="id"
					value="<c:out value='${employee.id}' />" />
		</c:if>
		<div class="formSpacing">
			<spring:bind path="employee.name">
				<td>Name :</td>
				<td><input type="text" name="name"
					value="<c:out value='${employee.name}'/>"
					pattern="^?=.{3,100}$)[a-zA-Z]{3}[a-zA-Z]*\\s?[a-zA-Z]*\\s?[a-zA-Z]*$"
					required /></td>
			</spring:bind>
		</div>
		<spring:bind path="employee.dob">
			<td>DOB :</td>
			<td><input type="date" name="dob" min="1961-01-01"
				max="2003-01-01" value="<c:out value='${employee.dob}'/>"
				required="required" /></td>
		</spring:bind>
		<c:if test="${notValidDob == true}">
			<p>Age should be between 18 to 60</p>
		</c:if>
		<div class="formSpacing">
			<spring:bind path="employee.salary">
				<td>Salary :</td>
				<td><input type="text" name="salary"
					value="<c:out value='${employee.salary}'/>"
					pattern="^[0]*[1-9][0-9]{3,10}[.]?([0-9]+)?$" required /></td>
			</spring:bind>
		</div>
		<div class="formSpacing">
			<spring:bind path="employee.contact">
				<td>Phone Number :</td>
				<td><input type="tel" name="contact"
					value="<c:out value='${employee.contact}'/>"
					pattern="^[6-9][0-9]{9}$" required /></td>
			</spring:bind>
		</div>
		<c:if test="${isDuplicateContact}">
			<p>Phone number already available, try another</p>
		</c:if>
		<div class="formSpacing">
			<spring:bind path="employee.email">
				<td>Email :</td>
				<td><input type="email" name="email"
					value="<c:out value='${employee.email}'/>"></td>
			</spring:bind>
		</div>
		<c:if test="${isDuplicateEmail}">
			<p>Email already available, try another</p>
		</c:if>
	</c:if>
	<c:if
		test="${null == employee.name || employeeId != null || isDuplicate}">
		<c:if test="${employeeId != null}">
			<form method="post" action="addAddress">
				<header>Enter Address details</header>
				<input type="hidden" name="id"
					value="<c:out value='${employeeId}' />" />
		</c:if>
		<div class="formSpacing">
			<spring:bind path="address.doorNo">
				<td>Door No :</td>
				<td><input type="text" name="doorNo"
					value="<c:out value='${address.doorNo}'/>"></td>
			</spring:bind>
		</div>
		<div class="formSpacing">
			<spring:bind path="address.landMark">
				<td>Land Mark :</td>
				<td><input type="text" name="landMark"
					value="<c:out value='${address.landMark}'/>"></td>
			</spring:bind>
		</div>
		<div class="formSpacing">
			<spring:bind path="address.street">
				<td>Street :</td>
				<td><input type="text" name="street"
					value="<c:out value='${address.street}'/>"></td>
			</spring:bind>
		</div>
		<div class="formSpacing">
			<spring:bind path="address.city">
				<td>City :</td>
				<td><input type="text" name="city"
					value="<c:out value='${address.city}'/>"></td>
			</spring:bind>
		</div>
		<div class="formSpacing">
			<spring:bind path="address.pincode">
				<td>Pincode :</td>
				<td><input type="tel" name="pincode" pattern="[1-9][0-9]{5}"
					value="<c:out value='${address.pincode}'/>"></td>
			</spring:bind>
		</div>
	</c:if>
	<div class="back">
		<input type="submit" name="Submit"> <input type="button"
			value="Back" onclick="history.back()">
	</div>
	</form>
</body>
</html>