<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Small Town Library</title>
	<style>
		body {
			background-color: #f2f2f2;
			font-family: Arial, sans-serif;
		}
		.container {
			background-color: #fff;
			border-radius: 5px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
			margin: 100px auto;
			padding: 20px;
			width: 400px;
		}
		h1 {
			color: #444;
			text-align: center;
		}
		input[type=text], input[type=password] {
			border: none;
			border-radius: 3px;
			display: block;
			font-size: 16px;
			margin: 10px 0;
			padding: 10px;
			width: 100%;
		}
		button {
			background-color: #4CAF50;
			border: none;
			border-radius: 3px;
			color: #fff;
			cursor: pointer;
			display: block;
			font-size: 16px;
			margin: 10px 0;
			padding: 10px;
			width: 100%;
		}
		button:hover {
			background-color: #3e8e41;
		}
		.forgot-password {
			color: #777;
			display: block;
			font-size: 14px;
			margin-top: 20px;
			text-align: center;
		}
		.settings {
			background-color: #f44336;
			border: none;
			border-radius: 50%;
			color: #fff;
			cursor: pointer;
			display: inline-block;
			font-size: 16px;
			height: 50px;
			line-height: 50px;
			margin: 10px 10px 0 0;
			text-align: center;
			width: 50px;
		}
		.settings:hover {
			background-color: #d32f2f;
		}
		.new-member {
			background-color: #f44336;
			border: none;
			border-radius: 3px;
			color: #fff;
			cursor: pointer;
			display: block;
			font-size: 16px;
			margin: 10px 0;
			padding: 10px;
			width: 100%;
		}
		.new-member:hover {
			background-color: #d32f2f;
		}
		.already-member {
			background-color: #4CAF50;
			border: none;
			border-radius: 3px;
			color: #fff;
			cursor: pointer;
			display: block;
			font-size: 16px;
			margin: 10px 0;
			padding: 10px;
			width: 100%;
		}
		.already-member:hover {
			background-color: #3e8e41;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>Welcome to our Small Town Library!</h1>
    <button onclick="window.location.href='UserRegistration.jsp';">New Member?</button>
    <button onclick="window.location.href='login.jsp';">Already a member?</button>
	</div>
</body>
</html>