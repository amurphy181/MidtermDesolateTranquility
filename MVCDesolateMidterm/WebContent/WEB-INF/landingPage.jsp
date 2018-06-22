<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Success</h1>

	<c:forEach items="${events }" var="event">
		<br>
		${event.location }<br>
		${event.game.title}<br>
		${event.game.platform }<br>
		${event.user.userName }<br>
		
		<br>
		<c:forEach items="${event.users }" var="user">
		${user.userName }
		</c:forEach>
	</c:forEach>
	<br>
<form:form action="createEvent.do" modelAttribute="event" method="POST">
	<%-- Error messages --%>
	Game:<form:input path="game"/>
	Platform:<form:input path = "platform"/>
	Location:<form:password path="location"/>
	<input type = "hidden" name = "userId" value = "${user.id }">
	<input type="submit" value="Add Event" > <br>
</form:form>
</body>
</html>