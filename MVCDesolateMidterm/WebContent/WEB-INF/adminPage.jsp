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
					<a href="profileView.do"><span class="glyphicon glyphicon-user"></span> Profile</a>
				</li>
					<c:if test="${userCurrent.admin}">

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

							<a href="logout.do">
				<button class="btn btn-primary" type="submit" aria-label="Left Align">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"> </span> Logout
				</button>
					</a>
			</div>
		</div>
	</div>
</div>		
		<div class="col-sm-6">
			<h3>Events List</h3>
			<div class="panel panel-info">				
					<c:forEach items="${events }" var="event">
				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake">
							<img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
						</a>
					
					
						<div class="media-body">
							
							<h3>Status:
							
							<c:if test="${event.status}">
							Active
							</c:if>
							<c:if test="${!event.status}">
							Inactive
							</c:if>
							
							</h3>
							<h4 class="media-heading">${event.game.title }</h4>
							<p>	<c:if test="${empty event.location }">
								${event.creator.userName } is playing ${event.game.title} on ${event.game.platform.platformName }<br>
								</c:if>
		
		 
								<c:if test="${not empty event.location}">
								${event.creator.userName } is playing ${event.game.title} at ${event.location }<br>
								</c:if></p>
								
										<form action="deactivateEvent.do" method="POST">
										<input type="submit" class="btn btn-primary" value="Deactivate Event" /> <input
										type="hidden" name="id" value="${event.id }" />
										</form>

										<form action="reactivateEvent.do" method="POST">
										<input type="submit" class="btn btn-primary" value="Activate Event" /> <input
										type="hidden" name="id" value="${event.id }" />
										</form>
							<ul class="nav nav-pills nav-pills-custom">
								<li><a href="#"><span class="glyphicon glyphicon-share-alt"></span></a></li>
								<li><a href="#"><span class="glyphicon glyphicon-retweet"></span></a></li>
								<li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
								<li><a href="#"><span class="glyphicon glyphicon-option-horizontal"></span></a></li>
							</ul>
						</div>
					</div>
					</div>
				
				</c:forEach>
				</div>
				</div>

	<!-- output all users so that admin can deactivate and reactivate them at will -->
	
	
	
			<div class="col-sm-6">
				<h3>User List</h3>
			<div class="panel panel-info">				
					<c:forEach items="${completeUserList }" var="user">
				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake">
							<img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
						</a>
					
					
						<div class="media-body">
							
							<h3>${user.userName }</h3>
							<h4 class="media-heading">							
							Status:
							
							<c:if test="${user.status}">
							Active
							</c:if>
							<c:if test="${!user.status}">
							Inactive
							</c:if>
							<c:if test="${user.admin}">
							Administrator
							</c:if>
							
							</h4>
							
		
	
								
							<form action="deactivateUser.do" method="POST">
								<input type="submit" class="btn btn-primary" value="Deactivate User" /> <input type="hidden"
									name="id" value="${user.id }" />
							</form>
					
							<form action="reactivateUser.do" method="POST">
								<input type="submit" class="btn btn-primary" value="Activate User" /> <input type="hidden"
									name="id" value="${user.id }" />
							</form>
							<form action="deactivateAdmin.do" method="POST">
								<input type="submit" class="btn btn-primary" value="Remove As Admin" /> <input type="hidden"
									name="id" value="${user.id }" />
							</form>
					
							<form action="activateAdmin.do" method="POST">
								<input type="submit" class="btn btn-primary" value="Set as Admin" /> <input type="hidden"
									name="id" value="${user.id }" />
							</form>
							
						</div>
					</div>
					</div>
				
				</c:forEach>
				</div>
				</div>
	

</body>
</html>