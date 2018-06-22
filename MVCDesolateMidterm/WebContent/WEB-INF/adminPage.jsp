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

	<!-- cycle through the events and be able to link over to the page in order to delete -->


	<c:forEach items="${events }" var="event">
		<br>
		<a href="getEventId.do?fid=${event.id }">Status: ${event.status }</a>
		${event.location }<br>
		${event.game.title}<br>
		${event.game.platform }<br>
		${event.status }
		<%-- ${event.users.userName }<br> --%>
		<form action="deactivateEvent.do" method="POST">
			<input type="submit" value="Deactivate Event" />
			<input type="hidden" name="id" value="${event.id }"/>
		</form>
		
		<form action="reactivateEvent.do" method="POST">
			<input type="submit" value="Reactivate Event" />
			<input type="hidden" name="id" value="${event.id }"/>
		</form>

		<br>
		<c:forEach items="${event.users }" var="user">
		${user.userName }
		</c:forEach>
	</c:forEach>

</body>
</html>