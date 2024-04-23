<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.capstone.beans.Books"%>
<!-- Import the Books class to use it in JSP -->
<%@ page import="java.util.List, com.capstone.beans.Books"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="style.css">
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
	<div class="background">
		<div class="transbox">

			<h2>Books List</h2>
			<h3>
				<a href="dashboard_login.jsp">Click here for manage user
					dashboard</a>
			</h3>
		</div>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>BookID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Genre</th>
				<th>Summary</th>
				<th>Stock</th>
				<th>Update Stock</th>
			</tr>
		</thead>
		<tbody>
			<% List<Books> booksList = (List<Books>) request.getAttribute("booksList"); %>
			<% if (booksList != null && !booksList.isEmpty()) { %>
			<% for (Books book : booksList) { %>
			<tr>
				<td><%= book.getBookId() %></td>
				<td><%= book.getTitle() %></td>
				<td><%= book.getAuthor() %></td>
				<td><%= book.getGenre() %></td>
				<td><%= book.getSummary() %></td>
				<td><%= book.getStock() %></td>

				<td>
					<form method="post" action="LibraryServlet">
					<input type="hidden" name="userID" value="<%=request.getAttribute("userID")%>">
						<input type="hidden" name="action" value="updateStock" /> <input
							type="hidden" name="bookId" value="<%= book.getBookId() %>" /> <input
							type="text" name="stock" min="0" />
						<button type="submit">Update</button>
					</form>
				</td>
			</tr>
			<% } %>
			<% } else { %>
			<tr>
				<td colspan="7">No books available.</td>
			</tr>
			<% } %>
		</tbody>
	</table>

	<% if (request.getAttribute("successMessage") != null) { %>
	<div style="color: green;"><%= request.getAttribute("successMessage") %></div>
	<% } %>
	<% if (request.getAttribute("errorMessage") != null) { %>
	<div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
		<% } %>
	<div class="background">
		<div class="transbox">
			<h2>Add New Book</h2>

			<form action="LibraryServlet" method="post">
			<input type="hidden" name="userID" value="<%=request.getAttribute("userID")%>">
				<input type="hidden" name="action" value="add"> Title: <input
					type="text" name="title"><br> Author: <input
					type="text" name="author"><br> Genre: <input
					type="text" name="genre"><br> Summary:
				<textarea style="width: 100%" name="summary"></textarea>
				<br>
				<button type="submit" value="Add Book">Add Book</button>
			</form>
		</div>
	</div>
</body>
</html>