<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="loginstyle.css">
<head>
<meta charset="UTF-8">
<title>Forgot Password</title>
</head>
<body>
	<form action="LibraryServlet" method="post">
		<div>
			<label for="email">Enter your email: </label> <input type="text" id="email"
				name="email">
		</div>
		<div>
		<input type="hidden" name="action" value="forgotPassword">
			<button type="submit" value="forgotPassword">Submit</button>
		</div>
	</form>


	<div>
		<% if(request.getAttribute("dbMessage") != null) { %>
		<span style="color: red;"><%= request.getAttribute("dbMessage")%></span>
		<% } %>
	</div>
</body>
</html>