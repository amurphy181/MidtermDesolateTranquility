<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Who's Playing?</title>
</head>
<body>
<form:form action="login.do" modelAttribute="user">
	<%-- Error messages --%>
	<form:input path="userName"/>
	<form:password path="password"/>
	<input type="submit" value="Log In" > <br>
	<form:errors path="password">Incorrect Username or Password</form:errors>
</form:form>

<a href="register.do">Register</a>
</body>
</html>