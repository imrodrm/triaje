<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>List Customers</title>
		
		<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css"/>
		
		<link type="text/javascript"
		href="${pageContext.request.contextPath}/resources/js/deleteCustomer.js"/>
		
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		<div id="conteiner">
			<div id="content">
				<div>
					<input type="button" value="Add Customer" onclick="window.location.href='showFormForAdd; return false;'" class="add-button"/>
				</div>
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Emilio</th>
						<th>Action</th>
					</tr>
					<c:forEach var="tempCustomer" items="${customers}">
						<tr>
							<c:url var="updateLink" value="/customer/showFormForUpdate">
								<c:param name="customerId" value="${tempCustomer.id}"/>
							</c:url>
							<c:url var="deleteLink" value="/customer/delete">
								<c:param name="customerId" value="${tempCustomer.id}"/>
							</c:url>
							<td>${tempCustomer.firstName}</td>
							<td>${tempCustomer.lastName}</td>
							<td>${tempCustomer.email}</td>
							<td><a href="${updateLink}">Update</a> | <a href="${deleteLink}" class="delete" onclick="if (!(confirm('Delete Customer?'))) return false;">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>