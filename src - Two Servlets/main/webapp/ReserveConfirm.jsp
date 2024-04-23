<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.capstone.beans.Books"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation Confirmation</title>
</head>
<body>
	<h2>Confirm Reservation</h2>
	<p>Do you want to reserve this book?" <%= request.getParameter("userID") %></p>
	<form action="LibraryServlet" method="post">
		<!-- Hidden input field to store the book ID -->
		<input type="hidden" name="userID" value="<%= request.getParameter("userID") %>">
		<input type="hidden" name="bookId"
			value="<%= request.getParameter("bookId") %>"> <input
			type="submit" value="Confirm">
<input type="hidden" name="action" value="reserveConfirm">
	</form>
	<a href="LibraryServlet">Cancel</a>
	<!-- Link back to the main library page -->
</body>
</html>