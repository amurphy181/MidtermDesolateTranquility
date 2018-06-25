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
<link rel="stylesheet" type="text/css" href="twitter.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Games List</title>
</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-collapse navbar-collapse-1 collapse" aria-expanded="true">
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="landingPage.do"><span class="glyphicon glyphicon-home"></span> Home</a>
				</li>
				
				<li>
					<a href="#fake"><span class="glyphicon glyphicon-envelope"></span> Messages</a>
				</li>
					<c:if test="${userCurrent.admin == 1}">

				<li>
					<a href="adminPage.do"><span class="glyphicon glyphicon-bell"></span> Admin</a>
				</li>
				</c:if>
			</ul>
			<div class="navbar-form navbar-right">
				<div class="form-group has-feedback">
					<input type="text" class="form-control-nav" id="search" aria-describedby="search1">
					<span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span>
				</div>

				<button class="btn btn-primary" type="submit" aria-label="Left Align">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"> </span> Tweet
				</button>
			</div>
		</div>
	</div>
</div>

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
			<input type="submit" value="Deactivate Event" /> <input
				type="hidden" name="id" value="${event.id }" />
		</form>

		<form action="reactivateEvent.do" method="POST">
			<input type="submit" value="Reactivate Event" /> <input
				type="hidden" name="id" value="${event.id }" />
		</form>

		<br>
	</c:forEach>

	<!-- output all users so that admin can deactivate and reactivate them at will -->
	<h3>User List</h3>
	<c:forEach items="${completeUserList }" var="user">
		User: ${user.userName }<br>
		Active: ${user.status }<br>

		<form action="deactivateUser.do" method="POST">
			<input type="submit" value="Deactivate User" /> <input type="hidden"
				name="id" value="${user.id }" />
		</form>

		<form action="reactivateUser.do" method="POST">
			<input type="submit" value="Reactivate User" /> <input type="hidden"
				name="id" value="${user.id }" />
		</form>
		<br>
	</c:forEach>

</body>
</html>