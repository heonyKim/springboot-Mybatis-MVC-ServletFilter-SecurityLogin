<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/join" method="post">
			USERNAME : <input name="username" type="text">
			<br>PASSWORD : <input name="password" type="password">
			<br><input type="submit" value="가입">
		</form>
	</div>
</body>
</html>
