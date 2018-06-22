<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles.css">
  
</head>


<title>Who's Playing?</title>
</head>


<body>
<div>
<c:if test="${added == true}">You successfully registered </c:if>
<form:form action="login.do" modelAttribute="user" method="POST">
	<%-- Error messages --%>
	Username  <form:input path="userName"/><br>
	Password  <form:password path="password"/><br><br>
	<p class="button">
	<input type="submit" value="Log In" ></p> <br>
	<form:errors path="password">Incorrect Password</form:errors>
	<form:errors path="userName">Username not found</form:errors>
</form:form>

<a href="register.do">Register</a>
</div>
</body>
</html>