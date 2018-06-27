<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="login.css">
<title>Registration</title>
</head>
<body>
	<main class="container">

	<div class="row">
		<h1>Registration</h1>
	</div>
	<div class="row">
		<img alt="Who's Playing Logo"
			src="http://d3nxyjdfmsp653.cloudfront.net/content/wp-content/uploads/2016/12/09214351/XY-Gaming-Logo-Glyph-Dark-BG.png"
			class="photo">
	</div>
	<div class="row">
		<div class="col login">
			<br>
			<h3>Welcome!</h3>
			<h5>Please select a unique username</h5>
			<form:form action="registration.do" modelAttribute="user"
				method="POST">
				<%-- Error messages --%>
	Username:<form:input path="userName" />
				<br>
	Password:<form:password path="password" />
				<br>
				<br>
				<input type="submit" value="Create Account" class = "btn btn-info btn-sm btn-md">
				<br>
				<form:errors path="userName">Username already exists</form:errors>
			</form:form>
		</div>
	</div>
	<div class="row register">
		<a href="welcome.do">Back</a>
	</div>
	</main>
</body>
</html>