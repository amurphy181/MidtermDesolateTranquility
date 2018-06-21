<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
<form:form action="registration.do" modelAttribute="user">
	<%-- Error messages --%>
	Username:<form:input path="userName"/>
	Password:<form:password path="password"/>
	<input type="submit" value="Create Account" > <br>
	<form:errors path="userName">Please select a unique Username</form:errors>
</form:form>
</body>
</html>