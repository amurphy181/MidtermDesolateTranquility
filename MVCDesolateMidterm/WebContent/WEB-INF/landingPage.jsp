<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Games List</title>
</head>
<body>
	<h1>Success</h1>
	
	<!-- testing to see if the user is an administrator -->
	<c:if test="${userCurrent.admin == 1}">
		<h3>Current user is admin</h3>
		<a href = "adminPage.do">Admin Page</a>
	</c:if>
	<c:if test="${userCurrent.admin == 0 }">
		<h3>Current user is not the admin</h3>
	</c:if>
	
	<p>The current user is: ${userCurrent }</p>

	<c:forEach items="${events }" var="event">
		<br>
		<c:if test="${event.visibility == 1 }">
		${event.location }<br>
		${event.game.title}<br>
		${event.game.platform }<br>
		${event.creator.userName }<br>
		${event.users }<br>
		<%-- ${event.users.userName }<br> --%>
		<br>
		<c:forEach items="${event.users }" var="user">
		${user.userName }
		</c:forEach>
	<form action="joinEvent.do" method="GET">
	<input type = "hidden" name = "userId" value = "${userCurrent.id }">
	<input type = "hidden" name = "eventId" value = "${event.id }">
	<input type="submit" value="Join Event" > <br>
	</form>
		</c:if>
	</c:forEach>
	<br>
<form action="createEvent.do" method="POST">
	<%-- Error messages --%>
	Game:<input name="game"/>
	Platform:<input name = "platform" value= "${platform.id }"/>
	Location:<input name="location"/>
	<input type = "hidden" name = "id" value = "${userCurrent.id }">
	<input type="submit" value="Add Event" > <br>
</form>

<a href="welcome.do">Home</a>
</body>
</html>