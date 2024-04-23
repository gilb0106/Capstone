<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="loginstyle.css">
<head>
<meta charset="UTF-8">
<title>User Registration</title>
</head>
<body>
	<h1>Welcome to Library User Registration</h1>
	<h2>Please enter your details below</h2>
	<% if (request.getAttribute("errorMessage") != null) { %>
	<div style="color: red;">
		<%= request.getAttribute("errorMessage") %>
	</div>
	<% } %>

	<form action="LibraryServlet" method="post">
		<div>
			<label for="userID">UserID:</label> <input type="text" id="userID"
				name="userID">
		</div>
		<div>
			<label for="password">Password:</label> <input type="password"
				id="password" name="password">
		</div>
		<div>
			<label for="email">Email:</label> <br> <input type="email"
				id="email" name="email">
		</div>
		<div>
			<label for="fName">First Name:</label> <input type="text" id="fName"
				name="fName">
		</div>
		<div>
			<label for="lName">Last Name:</label> <input type="text" id="lName"
				name="lName">
		</div>
		<div>
		    <input type="hidden" name="action" value="userRegistration">
			<button type="submit" value="Register">Register</button>
		</div>
	</form>
</body>
</html>