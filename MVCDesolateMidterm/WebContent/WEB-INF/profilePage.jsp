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
<title>Profile Page</title>
</head>
<body>

<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-collapse navbar-collapse-1 collapse" aria-expanded="true">
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="welcome.do"><span class="glyphicon glyphicon-home"></span> Home</a>
				</li>
				
				<li>
					<a href="profileView.do"><span class="glyphicon glyphicon-envelope"></span> Profile</a>
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
<h1>${userCurrent.userName }'s profile</h1>
<div>
<div class="col-sm-6">
			<h3>Events List</h3>
			<div class="panel panel-info">				
				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake">
							<img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
						</a>
					
					
						<div class="media-body">

<c:if test="${not empty addedGame}"><h3>${addedGame.title } for ${addedGame.platform.platformName } was added to your list.</h3>
</c:if>
<c:if test="${not empty removedGame}"><h3>${removedGame.title } for ${removedGame.platform.platformName } was removed from your list.</h3>
</c:if>

<c:forEach items="${userCurrent.games }" var="userGames">
		${userGames.title }
		${userGames.platform.platformName }
		<form action="updateGame.do">
		<input type="submit" value="Update Game">
		<input type="hidden" name="id" value="${userGames.id}">
		</form>
		<form action="deleteGameFromList.do">
		<input type="submit" value="Delete Game">
		<input type="hidden" name="gameId" value="${userGames.id}">
		<input type="hidden" name="userId" value="${userCurrent.id}">
		</form>
		<br>
		</c:forEach>
</div>
						</div>
					</div>
					</div>
				
				</div>
				</div>
<form action="addGameToList.do" method="POST">
	<%-- Error messages --%>
	Game:<input name="game"/>
	Platform:<input name = "platform" value= "${platform.id }"/>
	<input type = "hidden" name = "id" value = "${userCurrent.id }">
	<input type="submit" value="Add Game" > <br>
</form>
<h2>Joined Events</h2>
<c:forEach items = "${userCurrent.events }" var="event">

<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake">
							<img alt="" class="media-object img-rounded" src="http://placehold.it/64x64">
						</a>
					
					
						<div class="media-body">
							<h4 class="media-heading">${event.game.title }</h4>
							<p>	<c:if test="${empty event.location }">
								${event.creator.userName } is playing ${event.game.title} on ${event.game.platform.platformName }<br>
								</c:if>
		
		 
								<c:if test="${not empty event.location}">
								${event.creator.userName } is playing ${event.game.title} at ${event.location }<br>
								</c:if></p>
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
<a href="landingPage.do">Home</a>

</body>
</html>