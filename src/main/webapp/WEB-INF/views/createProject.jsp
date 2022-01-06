<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Project</title>
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
				Project <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<a href="ViewProjectDetails.jsp">View Projects</a> <br>
			</div>
		</div>
	</div>
	<c:if test="${null == project.name}">
		<form method="post" action="savePro">
			<h2>Create Project</h2>
	</c:if>
	<c:if test="${null != project.name}">
		<form method="post" action="updateProject">
			<h2>Edit Project</h2>
			<input type="hidden" name="id"
				value="<c:out value='${project.id}' />" />
	</c:if>
	<div class="formSpacing">
		<spring:bind path="project.name">
			<td>Name :</td>
			<td><input type="text" name="name"
				value="<c:out value='${project.name}'/>"
				pattern="^?=.{3,100}$)[a-zA-Z]{3}[a-zA-Z]*\\s?[a-zA-Z]*\\s?[a-zA-Z]*$"
				required /></td>
		</spring:bind>
	</div>
	<div class="formSpacing">
		<spring:bind path="project.startDate">
			<td>Start Date :</td>
			<td><input type="date" name="startDate"
				value="<c:out value='${project.startDate}'/>" required /></td>
		</spring:bind>
	</div>

	<div class="formSpacing">
		<spring:bind path="project.endDate">
			<td>End Date :</td>
			<td><input type="date" name="endDate"
				value="<c:out value='${project.endDate}'/>" required /></td>
		</spring:bind>
	</div>
	<div class="formSpacing">
		<spring:bind path="project.status">
			<td>Status :</td>
			<select name="status" value="<c:out value='${project.status}'/>">
				<option value="active">Active</option>
				<option value="inactive">Inactive</option>
			</select>
		</spring:bind>
	</div>
	<div class="formSpacing">
		<spring:bind path="project.name">
			<td>Domain :</td>
			<td><input type="text" name="domain"
				value="<c:out value='${project.domain}'/>" required /></td>
		</spring:bind>
	</div>
	<div class="back">
		<input type="submit" name="Submit"> <input type="button"
			value="Back" onclick="history.back()">
	</div>
	</form>

</body>
</html>