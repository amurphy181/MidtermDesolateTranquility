<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Page</title>
</head>
<body>
<h1>${userCurrent.userName }'s profile page</h1>
<div>
<c:forEach items="${userCurrent.games }" var="userGames">
		${userGames.title }
		</c:forEach>
</div>
<form action="addGameToList.do" method="POST">
	<%-- Error messages --%>
	Game:<input name="game"/>
	Platform:<input name = "platform" value= "${platform.id }"/>
	<input type = "hidden" name = "id" value = "${userCurrent.id }">
	<input type="submit" value="Add Game" > <br>
</form>

</body>
</html>