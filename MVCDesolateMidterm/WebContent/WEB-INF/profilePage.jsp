<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="twitter.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Page</title>
</head>
<body>

	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-collapse navbar-collapse-1 collapse"
				aria-expanded="true">
				<ul class="nav navbar-nav">
					<li class="active"><a href="landingPage.do"><span
							class="glyphicon glyphicon-home"></span> Home</a></li>

					<li><a href="profileView.do"><span
							class="glyphicon glyphicon-envelope"></span> Profile</a></li>
					<c:if test="${userCurrent.admin}">

						<li><a href="adminPage.do"><span
								class="glyphicon glyphicon-bell"></span> Admin</a></li>
					</c:if>
				</ul>
				<div class="navbar-form navbar-right">
					<div class="form-group has-feedback">
						<input type="text" class="form-control-nav" id="search"
							aria-describedby="search1"> <span
							class="glyphicon glyphicon-search form-control-feedback"
							aria-hidden="true"></span>
					</div>

					<a href="logout.do">
						<button class="btn btn-primary" type="submit"
							aria-label="Left Align">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true">
							</span> Logout
						</button>
					</a>
				</div>
			</div>
		</div>
	</div>
	<h1>${userCurrent.userName }'sprofile</h1>
	<div>
		<div class="col-sm-6">
			<h3>Events List</h3>
			<div class="panel panel-info">
				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake"> <img alt=""
							class="media-object img-rounded" src="${userCurrent.pictureURL }"
							width="175" height="175">
						</a>


						<div class="media-body">
							<h1>Hello Hello ${request.id }</h1>
							<c:if test="${request.friend.id == userCurrent.id}">
								<p>${request.message }</p>
								<form action="acceptFriendRequest.do" method="POST">
									<input type="submit" value="Accept Request"> <input
										type="hidden" name="requestId" value="${request.id}">
								</form>
							</c:if>
							<c:if test="${not empty addedGame}">
								<h3>${addedGame.title }for
									${addedGame.platform.platformName } was added to your list.</h3>
							</c:if>
							<c:if test="${not empty requestSent}">
								<h3>Request sent to ${requestSent.friend.userName }
									</h3>
							</c:if>
							<c:if test="${not empty removedGame}">
								<h3>${removedGame.title }for
									${removedGame.platform.platformName } was removed from your
									list.</h3>
							</c:if>
							<c:if test="${not empty success}">
								<h3>Password Successfully Changed</h3>
							</c:if>
							<c:if test="${not empty SummaryUpdated}">
								<h3>Profile Summary Set</h3>
							</c:if>
							<c:if test="${not empty SummaryNotUpdated}">
								<h3>Summary could not be changed. Limit 140 characters.</h3>
							</c:if>
							<c:if test="${not empty friend}">
								<h3>${friend.userName } was added to your friends list</h3>
							</c:if>
							<c:if test="${not empty byefriend}">
								<h3>${byefriend.userName } was removed from your friends list</h3>
							</c:if>

							<!-- Trigger the modal with a button -->
							<button type="button" class="btn btn-info btn-lg"
								data-toggle="modal" data-target="#myModal">CHANGE YOUR
								PROFILE PICTURE</button>

							<!-- Modal -->
							<div id="myModal" class="modal fade" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Change yo pic</h4>
										</div>
										<div class="modal-body">
											<p>Two plus two is four, minus one, that's three - quick
												maths.</p>


											<form action="setProfilePicture.do" method="POST">
												<input type="hidden" name="userId"
													value="${userCurrent.id }"> <input type="hidden"
													name="picURL"
													value="https://www.thewrap.com/sites/default/wp-content/uploads/files/snarf.jpg">
												<button>
													<img
														src="https://www.thewrap.com/sites/default/wp-content/uploads/files/snarf.jpg"
														width="200" height="200" />
												</button>
											</form>
											<form action="setProfilePicture.do" method="POST">
												<input type="hidden" name="userId"
													value="${userCurrent.id }"> <input type="hidden"
													name="picURL"
													value="http://digitalspyuk.cdnds.net/17/29/980x490/landscape-1500370357-1.jpg">
												<button>
													<img
														src="http://digitalspyuk.cdnds.net/17/29/980x490/landscape-1500370357-1.jpg"
														width="200" height="200" />
												</button>
											</form>

										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>
							<p>${userCurrent.summary }</p>

							<c:forEach items="${userCurrent.games }" var="userGames">
		${userGames.title }
		${userGames.platform.platformName }
		<form action="updateGame.do">
									<input type="submit" value="Update Game"> <input
										type="hidden" name="id" value="${userGames.id}">
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
		Game:<input name="game" /><br> Platform:<input name="platform"
			value="${platform.id }" /> <input type="hidden" name="id"
			value="${userCurrent.id }"><br> <input type="submit"
			value="Add Game"> <br>
	</form>
	<h2>Friends</h2>
	<hr>
	<c:forEach items="${userFriendList}" var="friend">
		${friend.userName }
		<form action="deleteFriend.do">
			<input type="submit" value="Remove Friend"> <input
				type="hidden" name="friendId" value="${friend.id}"> <input
				type="hidden" name="userId" value="${userCurrent.id}">
		</form>
		<br>
	</c:forEach>
	<h2>Joined Events</h2>
	<c:forEach items="${userCurrent.events }" var="event">

		<div class="panel-body">
			<div class="media">
				<a class="media-left" href="#fake"> <img alt=""
					class="media-object img-rounded" src="${userCurrent.pictureURL }">
				</a>


				<div class="media-body">
					<h4 class="media-heading">${event.game.title }</h4>
					<p>
						<c:if test="${empty event.location }">
								${event.creator.userName } is playing ${event.game.title} on ${event.game.platform.platformName }<br>
						</c:if>


						<c:if test="${not empty event.location}">
								${event.creator.userName } is playing ${event.game.title} at ${event.location }<br>
						</c:if>
					</p>
					<ul class="nav nav-pills nav-pills-custom">
						<li><a href="#"><span
								class="glyphicon glyphicon-share-alt"></span></a></li>
						<li><a href="#"><span class="glyphicon glyphicon-retweet"></span></a></li>
						<li><a href="#"><span class="glyphicon glyphicon-star"></span></a></li>
						<li><a href="#"><span
								class="glyphicon glyphicon-option-horizontal"></span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</c:forEach>



	<form:form action="changePassword.do" modelAttribute="passwordDTO"
		method="POST">
	Old Password  <form:input type="password" autocorrect="off"
			autocapitalize="none" path="oldPassword" />
		<br>
	New Password  <form:input type="password" autocorrect="off"
			autocapitalize="none" path="newPassword" />
		<br>
		<p class="button">
			<input type="submit" value="Change Password">
		</p>
		<br>
		<input type="hidden" name="id" value="${userCurrent.id }">
		<form:errors path="oldPassword">Mmmm that wasn't your old password</form:errors>
		<form:errors path="newPassword">That's literally the same password...</form:errors>
	</form:form>

	<form action="setProfileBlurb.do" method="POST">
		Profile Summary: <input type="text" name="blurb"><br>
		<h5>Limit 140 characters</h5>
		<input type="hidden" name="id" value="${userCurrent.id }"> <input
			type="submit" value="Set Summary"> <br>
	</form>

	<form action="viewAllUsers.do">
		<input type="hidden" name="id" value="${userCurrent.id }"> <input
			type="submit" value="Find Friends">
	</form>
</body>
</html>