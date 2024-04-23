<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.capstone.beans.User"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="style.css">
<head>

<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<div class="background">
		<div class="transbox">
			<h1>Welcome to your Dashboard</h1>

			<h2>Your User Settings:</h2>
		</div>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>UserID</th>
				<th>Password</th>
				<th>Email</th>
				<th>First Name</th>
				<th>Last Name</th>
				<!-- Added Stock Column -->
				<th>Change Password</th>
			</tr>
		</thead>
		<tbody>
			<% 
    Object obj = request.getAttribute("userInfo");

    if (obj instanceof List) {
        List<?> genericList = (List<?>) obj;
        for(Object item : genericList) {
            if(item instanceof User) {
                User user = (User) item;
%>
			<tr>
				<td><%= user.getUserID() %></td>
				<td><%= user.getPassword() %></td>
				<td><%= user.getEmail() %></td>
				<td><%= user.getFName() %></td>
				<td><%= user.getLName() %></td>
				<!-- Display the Stock value -->

				<td><a href='LibraryServlet?action=changePassword&userID=<%= user.getUserID()%>'>Change
						your password</a></td>
			</tr>
			<% 
            }
        }
    }
%>
		</tbody>
	</table>



</body>
</html>