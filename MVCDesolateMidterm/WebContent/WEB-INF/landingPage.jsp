<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Games List</title>
</head>
<body>


<!-- NAVBAR -->
<div class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-collapse navbar-collapse-1 collapse" aria-expanded="true">
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="welcome.do"><span class="glyphicon glyphicon-home"></span> Home</a>
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
				 Logout
				</button>
					</a>
			</div>
		</div>
	</div>
</div>
<!-- PROFILE PIC AND INFO UNDERNEATH -->
<div class="container">
	<div class="row">
		<div class="col-sm-3">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-xs-12">
							<a href="profileView.do"><img class="img-responsive" alt="" src="${userCurrent.pictureURL }" width="175" height="175"></a>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-3">
							<h5>
								<small>GAMES</small><br>
								<a href="#">${fn:length(userCurrent.games)}</a>
							</h5>
						</div>
						<div class="col-xs-4">
							<h5>
								<small>REQUESTS</small><br>
								<a href="profileView.do">${fn:length(userCurrent.friendList)}</a>
							</h5>
						</div>
						<div class="col-xs-5">
							<h5>
								<small>EVENTS</small><br>
								<a href="#">${fn:length(userCurrent.createdEvents)}</a>
							</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="media">
					
						<a class="media-left" href="#fake">
							<img alt="" class="media-object img-rounded" src="${userCurrent.pictureURL }" height="35" width ="35">
						</a>
						
					<!-- USER EVENT CREATION / INPUT -->
						<div class="media-body">
							<div class="form-group has-feedback">
							<form action="createEvent.do" method="POST">
							<i>
								<label class="control-label sr-only" for="inputSuccess5">Hidden label</label>
								<input type="text" name="game" class="form-control" id="search2" aria-describedby="search" placeholder="game">
								<input type="text" name="platform" class="form-control" id="search2" aria-describedby="search" placeholder="console" value="${platform.id }">	
								<input type="text" name="location" class="form-control" id="search2" aria-describedby="search" placeholder="location (optional)">
								<input type = "hidden" name = "id" value = "${userCurrent.id }">
								</i>
							<input type="submit" class="btn btn-primary" value="Post" > <br>

						
								</form>
						
						
							</div>
						</div>
					
					</div>
				</div>
				
				<!-- LISTING OUT EVENTS ON PAGE -->
				
				<c:forEach items="${events }" var="event">
				<c:if test="${event.visibility == 1 }">
				<c:if test="${event.status}">
				<%-- <c:if test="${fn:contains(userCurrent.friendList, event.creator) || userCurrent.admin || event.creator.id == userCurrent.id}"> --%>
					
				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake">
							<img alt="" class="media-object img-rounded" src="${event.creator.pictureURL }" width="64" height="64">
						</a>
					
					
						<div class="media-body">
							<h4 class="media-heading">${event.game.title }  <h6><fmt:formatDate pattern="MMM dd hh:mm a" value="${event.startDate}" /></h6></h4>
							<p>	<c:if test="${empty event.location }">
								${event.creator.userName } is playing ${event.game.title} on ${event.game.platform.platformName }<br>
								
								</c:if>
		
		 
								<c:if test="${not empty event.location}">
								${event.creator.userName } is playing ${event.game.title} at ${event.location }<br>
								</c:if><br>
								
								
								<c:forEach items="${event.users }" var="user">
								
								<h6>${user.userName} has joined!</h6> 
								</c:forEach>
								
								<!-- ADMIN BUTTON TO DEACTIVATE ON LANDING PAGE -->
								
								<c:if test="${userCurrent.admin}">
								
										<form action="deactivateEvent2.do" method="POST">
										<input type="submit" class="btn btn-primary" value="Deactivate Event" /> <input
										type="hidden" name="id" value="${event.id }" />
										</form>															
								</c:if>
								</p>
							<ul class="nav nav-pills nav-pills-custom">
								<c:if test="${!fn:contains(event.users, userCurrent) && !(userCurrent.id == event.creator.id)}">							
								<li><button onclick=location.href="joinEvent.do?userId=${userCurrent.id }&eventId=${event.id}" type="button" class="btn btn-info btn-sm" >Join</button></li>
								</c:if>
								<c:if test="${fn:contains(event.users, userCurrent) && !(userCurrent.id == event.creator.id)}">							
								<li><button onclick=location.href="leaveEvent.do?userId=${userCurrent.id }&eventId=${event.id}" type="button" class="btn btn-info btn-sm" >Leave</button></li>
								</c:if>
								<c:if test="${userCurrent.id == event.creator.id}">							
								<li><button onclick=location.href="userRemoveEvent.do?userId=${userCurrent.id }&eventId=${event.id}" type="button" class="btn btn-info btn-sm" >Done Playing?</button></li>
								</c:if>
								<li><button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal${event.id }">Comments</button></li>
							</ul>
						</div>
					</div>
					</div>
	<div id="myModal${event.id }" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">${event.game.title }</h4>
      </div>
<!--       BODY OF MESSAGE MODAL -->
      <div class="modal-body">
 
      <ul class="list-unstyled">
      	 <c:forEach items="${event.messages }" var="message">
			<li><div>
				<div class="media">
  <img class="align-self-start mr-3" src="${message.user.pictureURL }" height="35" width="35" alt="Generic placeholder image">
</div>
  <div class="media-body">
    
    <p><b>${message.user.userName}:</b> ${message.content}</p>
  </div></div>

				</li>			
			</c:forEach>
			</ul>
      		</div>
      
      
      <div class="modal-footer">
     <div class="row"> <form action="postMessage.do" method="POST"><i>
      <input type="text" name="messageContent" class="form-control" id="search2" aria-describedby="search" placeholder="message"></i>
        <input type="submit" class="btn btn-info btn-md" ><input type="hidden" name="eventId" value="${event.id }" type="hidden" name="user" value="${userCurrent }"  />
        </form>
        <button type="button" class="btn btn-info btn-md" data-dismiss="modal">Close</button>
     </div>
      </div>
    </div>

  </div>
</div>
					</c:if>
					</c:if>
				</c:forEach>
			</div>
			<br>
			
		</div> 
	</div>
</div>


</body>
</html>