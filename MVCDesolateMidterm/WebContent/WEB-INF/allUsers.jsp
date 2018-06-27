<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find Friend</title>
</head>
<body>
<c:forEach items = "${allUsers}" var = "user">
<c:if test="${!userFriendList.contains(user)}">
<c:if test="${user.id != userCurrent.id }">
${user.userName }
<br>
<form action="sendRequest.do">
	Message: <input type="text" name="message">
	<input type="hidden" name="friendId" value="${user.id}">
	<input type="hidden" name="userId" value="${userCurrent.id}">
	<input type="submit" value="Add Friend">
</form>
</c:if>
	</c:if>
</c:forEach>

<a href="profileView.do">Back</a>
</body>
</html>