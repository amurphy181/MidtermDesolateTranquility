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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
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
							class="glyphicon glyphicon-user"></span> Profile</a></li>
					<c:if test="${userCurrent.admin}">

						<li><a href="adminPage.do"><span
								class="glyphicon glyphicon-bell"></span> Admin</a></li>
					</c:if>
				</ul>
				<div class="navbar-form navbar-right">
					<div class="form-group has-feedback"style:"float:left">
						<input type="text" class="form-control-nav" id="search"
							aria-describedby="search1"> <span
							class="glyphicon glyphicon-search form-control-feedback"
							aria-hidden="true"></span>
					</div>

					<a href="logout.do">
						<button class="btn btn-primary" type="submit"
							aria-label="Left Align">Logout</button>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid col-12">
		<div class="col-sm-6">
			<h1>${userCurrent.userName }'sProfile</h1>
			<c:forEach items="${requestList }" var="request">

				<p>${request.user.userName }</p>
				<p>${request.message }</p>
				<p>${request.timestamp }</p>
				<form action="acceptFriendRequest.do" method="POST">
					<input type="submit" value="Accept Request"> <input
						type="hidden" name="requestId" value="${request.id}">
				</form>
				<form action="denyRequest.do" method="POST">
					<input type="submit" value="Deny Request"> <input
						type="hidden" name="requestId" value="${request.id}">
				</form>
			</c:forEach>
			<c:if test="${not empty addedGame}">
				<h3>${addedGame.title }for${addedGame.platform.platformName }
					was added to your list.</h3>
			</c:if>
			<c:if test="${not empty requestSent}">
				<h3>Request sent to ${requestSent.friend.userName }</h3>
			</c:if>
			<c:if test="${not empty removedGame}">
				<h3>${removedGame.title }for
					${removedGame.platform.platformName } was removed from your list.</h3>
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
				<h3>${friend.userName }wasaddedtoyourfriendslist</h3>
			</c:if>
			<c:if test="${not empty byefriend}">
				<h3>${byefriend.userName }wasremovedfromyourfriendslist</h3>
			</c:if>
			<c:if test="${not empty alreadyFriend}">
				<h3>${alreadyFriend.userName }isalreadyafriendorhasa pending
					request</h3>
			</c:if>
			<div class="panel panel-info">
				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake"> <img alt=""
							class="media-object img-rounded" src="${userCurrent.pictureURL }"
							width="200" height="200">
						</a>



						<div class="media-body">


							<!-- Trigger the modal with a button -->
							<button type="button" class="btn btn-info btn-md"
								data-toggle="modal" data-target="#myModal">New Profile
								Picture</button>

							<!-- Modal -->
							<div id="myModal" class="modal fade" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Choose a new profile picture</h4>
										</div>
										<div class="modal-body row">
											<h4>
												<p>Pick a new profile picture</p>
											</h4>
											<!-- container for a 3 x 3 box of pictures -->
											<div class="row">
												<!-- column 1 -->
												<div class="col-sm-4">
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://www.thewrap.com/sites/default/wp-content/uploads/files/snarf.jpg">
														<button>
															<img
																src="https://www.thewrap.com/sites/default/wp-content/uploads/files/snarf.jpg"
																width="100" height="100" />
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
																width="120" height="100" />
														</button>
													</form>
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://vignette.wikia.nocookie.net/bobsburgerpedia/images/9/95/Bobs-Burgers-Wiki_Archer_Bob_01a.png/revision/latest?cb=20160712225426">
														<button>
															<img
																src="https://vignette.wikia.nocookie.net/bobsburgerpedia/images/9/95/Bobs-Burgers-Wiki_Archer_Bob_01a.png/revision/latest?cb=20160712225426"
																width="100" height="100" />
														</button>
													</form>
												</div>

												<!-- second column -->
												<div class="col-sm-4">
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://vignette.wikia.nocookie.net/archer/images/b/b9/Pam_Poovey.png/revision/latest?cb=20141013063722">
														<button>
															<img
																src="https://vignette.wikia.nocookie.net/archer/images/b/b9/Pam_Poovey.png/revision/latest?cb=20141013063722"
																width="100" height="115" />
														</button>
													</form>
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://vignette.wikia.nocookie.net/arresteddevelopment/images/6/60/Season_4_Poster_-_Tobias_F%C3%BCnke_01.jpg/revision/latest?cb=20130521213519">
														<button>
															<img
																src="https://vignette.wikia.nocookie.net/arresteddevelopment/images/6/60/Season_4_Poster_-_Tobias_F%C3%BCnke_01.jpg/revision/latest?cb=20130521213519"
																width="100" height="110" />
														</button>
													</form>
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://cdn.history.com/sites/2/2015/04/HITH-10-Things-Vladimir-Lenin-A.jpeg">
														<button>
															<img
																src="https://cdn.history.com/sites/2/2015/04/HITH-10-Things-Vladimir-Lenin-A.jpeg"
																width="115" height="100" />
														</button>
													</form>
												</div>

												<!-- column three -->
												<div class="col-sm-4">
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/CheHigh.jpg/220px-CheHigh.jpg">
														<button>
															<img
																src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/CheHigh.jpg/220px-CheHigh.jpg"
																width="100" height="100" />
														</button>
													</form>
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="https://yt3.ggpht.com/a-/ACSszfEJZY7Bc6tR_sFLpFlEqLOkPmIx9fvUO7to=s900-mo-c-c0xffffffff-rj-k-no">
														<button>
															<img
																src="https://yt3.ggpht.com/a-/ACSszfEJZY7Bc6tR_sFLpFlEqLOkPmIx9fvUO7to=s900-mo-c-c0xffffffff-rj-k-no"
																width="100" height="100" />
														</button>
													</form>
													<form action="setProfilePicture.do" method="POST">
														<input type="hidden" name="userId"
															value="${userCurrent.id }"> <input type="hidden"
															name="picURL"
															value="http://statici.behindthevoiceactors.com/behindthevoiceactors/_img/chars/fred-jones-whats-new-scooby-doo-96.jpg">
														<button>
															<img
																src="http://statici.behindthevoiceactors.com/behindthevoiceactors/_img/chars/fred-jones-whats-new-scooby-doo-96.jpg"
																width="100" height="100" />
														</button>
													</form>
												</div>
											</div>

										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>
							<p></p>
							<div>
								<!-- Trigger the profile summary modal with a button -->
								<button type="button" class="btn btn-info btn-md"
									data-toggle="modal" data-target="#profileSummaryModal">Set
									Profile Summary</button>

								<!-- Modal -->
								<div id="profileSummaryModal" class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Set your profile summary below</h4>
											</div>
											<div class="modal-body">
												<h4>
													<p>Tell us about yourself</p>
												</h4>
												<form action="setProfileBlurb.do" method="POST">
													Profile Summary: <input type="text" name="blurb"><br>
													<h5>Limit 140 characters</h5>
													<input type="hidden" name="id" value="${userCurrent.id }">
													<input type="submit" value="Set Summary"> <br>
												</form>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>
							<p></p>
							<div>
								<!-- Trigger the change password modal -->
								<button type="button" class="btn btn-info btn-md"
									data-toggle="modal" data-target="#changePassword">Change
									Password</button>

								<!-- Modal -->


								<div id="changePassword" class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Change Your Password</h4>
											</div>
											<div class="modal-body">
												<h4>
													<p></p>
												</h4>
												<form:form action="changePassword.do"
													modelAttribute="passwordDTO" method="POST">
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
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>

							<p>${userCurrent.summary }</p>


						</div>
					</div>
				</div>
			</div>
			<div class="friendBlock">
				<h2>Friends</h2>
				<!-- Trigger the find friends modal -->
				<button type="button" class="btn btn-info btn-md"
					data-toggle="modal" data-target="#findFriends">Find
					Friends</button>

				<!-- Modal -->
				<div id="findFriends" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Find new friends</h4>
							</div>
							<div class="modal-body">
								<c:forEach items="${allUsers}" var="user">
									<c:if test="${!userFriendList.contains(user)}">
										<c:if test="${user.id != userCurrent.id }">
${user.userName }
<br>
											<form action="sendRequest.do">
												Message: <input type="text" name="message"> <input
													type="hidden" name="friendId" value="${user.id}"> <input
													type="hidden" name="userId" value="${userCurrent.id}">
												<input type="submit" value="Add Friend">
											</form>
										</c:if>
									</c:if>
								</c:forEach>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
			<div class="clearBody"></div>
			<br>
			<div>
				<c:forEach items="${userFriendList}" var="friend">
		
		${friend.userName }
		<!-- remove friend modal tester -->

					<!-- Trigger the delete friend modal -->
					<button type="button" class="btn btn-info btn-md"
						data-toggle="modal" data-target="#deleteFriend">Remove Friend</button>

					<!-- Modal -->
					<div id="deleteFriend" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Are you sure you want to remove this friend?</h4>
								</div>
								<div class="modal-body">
									<form action="deleteFriend.do">
										<input type="submit" value="Remove Friend"> <input
											type="hidden" name="friendId" value="${friend.id}"> <input
											type="hidden" name="userId" value="${userCurrent.id}">
									</form>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
					<br>
				</c:forEach>
			</div>
		</div>

		<div class="col-sm-6">
			<h1>Games and Events</h1>





			<h2>Joined Events</h2>
			<c:forEach items="${userCurrent.events }" var="event">
							<c:if test="${event.status}">

				<div class="panel-body">
					<div class="media">
						<a class="media-left" href="#fake"> <img alt=""
							class="media-object img-rounded" src="${userCurrent.pictureURL }"
							height="100" width="100">
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
								<c:if test="${!fn:contains(event.users, userCurrent) && !(userCurrent.id == event.creator.id)}">							
								<li><button onclick=location.href="joinEvent.do?userId=${userCurrent.id }&eventId=${event.id}" type="button" class="btn btn-info btn-sm" >Join</button></li>
								</c:if>
								<c:if test="${fn:contains(event.users, userCurrent) && !(userCurrent.id == event.creator.id)}">							
								<li><button onclick=location.href="leaveEvent2.do?userId=${userCurrent.id }&eventId=${event.id}" type="button" class="btn btn-info btn-sm" >Leave</button></li>
								</c:if>
								<c:if test="${userCurrent.id == event.creator.id}">							
								<li><button onclick=location.href="userRemoveEvent2.do?userId=${userCurrent.id }&eventId=${event.id}" type="button" class="btn btn-info btn-sm" >Done Playing?</button></li>
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
     <div class="row"> <form action="postMessage2.do" method="POST"><i>
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
			</c:forEach>

			<div>
				<h2>Games on File</h2>
				<!-- Trigger the add games modal -->
				<button type="button" class="btn btn-info btn-md"
					data-toggle="modal" data-target="#addGame">Add Game</button>

				<!-- Modal -->
				<div id="addGame" class="modal fade" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add a Game</h4>
							</div>
							<div class="modal-body">
								<h4>
									<p>Add a game to your collection</p>
								</h4>
								<form action="addGameToList.do" method="POST">
									<%-- Error messages --%>
									Game:<input name="game" /><br> Platform:<input
										name="platform" value="${platform.id }" /> <input
										type="hidden" name="id" value="${userCurrent.id }"><br>
									<input type="submit" value="Add Game"> <br>
								</form>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
				<p></p>
				<c:forEach items="${userCurrent.games }" var="userGames">
				<div>
						<%-- ${userGames.title }
						${userGames.platform.platformName } --%>

					<!-- Trigger the update games modal -->
					<button type="button" class="btn btn-info btn-md"
						data-toggle="modal" data-target="#updateGame${userGames.id}">${userGames.title }
						${userGames.platform.platformName } -- Update?</button>

					<!-- Modal -->
					<div id="updateGame${userGames.id}" class="modal fade" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Update Game</h4>
								</div>
								<div class="modal-body">
									<form action="updateGameInfo.do" method="POST">
										Title:<input type="text" name="title"
											value="${userGames.title }" /> Platform: <input type="text"
											name="platform" value="${userGames.platform.platformName}" /><br>
										<input type="hidden" name="id" value="${userGames.id }" /> <input
											type="submit" value="Update Game" />
									</form>
									<br>
									<form action="deleteGameFromList.do">
										<input type="submit" value="Delete Game"> <input
											type="hidden" name="gameId" value="${userGames.id}">
										<input type="hidden" name="userId" value="${userCurrent.id}">
									</form>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
					<br>
					<br>
					</div>
				</c:forEach>


			</div>
			<p></p>
		</div>


	</div>
	<p></p>
</body>
</html>