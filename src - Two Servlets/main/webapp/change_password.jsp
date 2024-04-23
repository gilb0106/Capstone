<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.capstone.beans.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="loginstyle.css">
<head>
<meta charset="UTF-8">
<title>User Registration</title>
</head>
<body>
	<h1>Please update your password</h1>
	<%
	request.getAttribute("userID");
	%>

	<form action="LibraryServlet" method="post">
		<div>
			<label for="userID">UserID: </label> <input type="text" id="userID"
				name="userID" value=<%=request.getAttribute("userID")%> readonly>
		</div>
		<div>
		<input type="hidden" name="action" value="changePassword">
			<label for="password">Password:</label> <input type="password"
				id="password" name="password"
				value=<%=request.getAttribute("password")%>>
		</div>
		<div>
			<button type="submit" value="Update">Update</button>
		</div>
	</form>
</body>
</html>