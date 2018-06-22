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
	
	<!-- testing to see if the user is an administrator -->
	<c:if test="${user.admin == 1}">
		<h3>Current user is admin</h3>
	</c:if>
	<c:if test="${user.admin == 0 }">
		<h3>Current user is not the admin</h3>
	</c:if>
	
	<p>The current user is: ${user }</p>

	<c:forEach items="${events }" var="event">
		<br>
		${event.location }<br>
		${event.game.title}<br>
		${event.game.platform }<br>
		<%-- ${event.users.userName }<br> --%>
		
		<br>
		<c:forEach items="${event.users }" var="user">
		${user.userName }
		</c:forEach>
	</c:forEach>
	<br>
<form action="createEvent.do" method="POST">
	<%-- Error messages --%>
	Game:<input name="game"/>
	Platform:<input name = "platform" value= "${platform.id }"/>
	Location:<input name="location"/>
	<input type = "hidden" name = "id" value = "${user.id }">
	<input type="submit" value="Add Event" > <br>
</form>
</body>
</html>