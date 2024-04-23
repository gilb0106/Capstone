<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="loginstyle.css">
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
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
		<input type="hidden" name="action" value="login">
			<button type="submit" value="Login">Login</button>
		</div>
	</form>

	<form action="UserRegistration.jsp" method="get">
		<button type="submit" value="Register">Register</button>
	</form>
		<form action="ForgotPassword.jsp" method="get">
		<button type="submit" value="forgotPassword">Forgot Password</button>
	</form>

	<div>
		<% if(request.getAttribute("dbMessage") != null) { %>
		<span style="color: red;"><%= request.getAttribute("dbMessage")%></span>
		<% } %>
	</div>


	<% 
    String debugUsername = request.getParameter("userID");
    String debugPassword = request.getParameter("password");
    %>




</body>
</html>