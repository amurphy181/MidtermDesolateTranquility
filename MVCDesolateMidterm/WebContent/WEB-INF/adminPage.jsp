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
		<a href="getJobApp.do?fid=${jobApp.id }">${jobApp.company }</a>
		${event.location }<br>
		${event.game.title}<br>
		${event.game.platform }<br>
		<%-- ${event.users.userName }<br> --%>

		<br>
		<c:forEach items="${event.users }" var="user">
		${user.userName }
		</c:forEach>
	</c:forEach>

</body>
</html>