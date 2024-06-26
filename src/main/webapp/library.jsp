<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.capstone.beans.Books"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="style.css">
<head>

<meta charset="UTF-8">
<title>Library</title>
</head>
<body>
	<div class="background">
		<div class="transbox">
			<h1>Welcome to the Library</h1>

			<h2>Available Books:</h2>

			<h3>
				<a href="dashboard_login.jsp">Click here for user dashboard</a>
			</h3>
		</div>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>Title</th>
				<th>Author</th>
				<th>Genre</th>
				<th>Summary</th>
				<th>Stock</th>
				<!-- Added Stock Column -->
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<% 
    Object obj = request.getAttribute("buk");

    if (obj instanceof List) {
        List<?> genericList = (List<?>) obj;
        for(Object item : genericList) {
            if(item instanceof Books) {
                Books book = (Books) item;
%>
			<tr>
				<td><%= book.getTitle() %></td>
				<td><%= book.getAuthor() %></td>
				<td><%= book.getGenre() %></td>
				<td><%= book.getSummary() %></td>
				<td><%= book.getStock() %></td>
				<!-- Display the Stock value -->
				<td>
					<% if(book.getStock() == 0) { %> <span
					style="opacity: 0.5;">Reserve</span> <!-- Disabled Text --> <% } else { %>
					<a href="ReserveConfirm.jsp?bookId=<%= book.getBookId() %>">Reserve</a>
					<% } %>
					<button onclick="returnBook(<%= book.getBookId() %>)">Return</button>
				</td>
			</tr>
			<% 
            }
        }
    }
%>
		</tbody>
	</table>

	<script>
        function returnBook(bookId) {
            // Redirect to ReturnConfirm.jsp with bookId as a parameter
            window.location.href = 'ReturnConfirm.jsp?bookId=' + bookId;
        }
    </script>

	<div>
		<% if(request.getAttribute("errorMessage") != null) { %>
		<span style="color: red;"><%= request.getAttribute("errorMessage") %></span>
		<% } %>
	</div>

</body>
</html>