<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="twitter.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Game</title>
</head>
<body>
<h1>Update Game</h1>
<form action="updateGameInfo.do" method="POST">
Title:<input type="text" name="title" value="${game.title }"/>
Platform: <input type="text" name="platform" value="${game.platform.platformName}"/><br>
	<input type ="hidden" name = "id" value="${game.id }"/>
	<input type="submit" value="Update Game"/> 
	</form>
	<a href="profileView.do">Back</a>
</body>
</html>