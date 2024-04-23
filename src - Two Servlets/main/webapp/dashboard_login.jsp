<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="loginstyle.css">
<head>
<meta charset="UTF-8">
<title>Access your dashboard panel</title>
</head>
<body>
	<h1>Please enter your username and password to access your
		dashboard</h1>
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
		<input type="hidden" name="action" value="userSettings">
			<button type="submit" value="Login">Login</button>
		</div>
	</form>

	<div>
		<%
		if (request.getAttribute("dbMessage") != null) {
		%>
		<span style="color: red;"><%=request.getAttribute("dbMessage")%></span>
		<%
		}
		%>
	</div>


	<%
	String debugUsername = request.getParameter("userID");
	String debugPassword = request.getParameter("password");
	%>


	<div>
		Print the username and password received (remove these in production)
		Username received:
		<%=debugUsername%>
		Password received:
		<%=debugPassword%>
	</div>

</body>
</html>