<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EverspinFinalTask</title>
</head>
<body>
	<div>
		<h4>1)정확한 URL을 입력하세요</h4>
		
		<form action="/locationHref" method="post">
			<label>
				http://<input name="url" type="text" placeholder="정확한 URL을 입력하세요">
				<input type="submit" value="확인">
				<br>예시 : naver.com, nate.com
				<span style="color:blue;"><br>※ 단, <b>google</b>만 입력시 servlet필터에서 판별하여 <b>gmail로 이동</b>합니다.</span>
			</label>
		</form>
	</div>
	<hr>
	<div>
		<c:choose>
			<c:when test="${principal.user eq null }">
				<h4>2)로그인</h4>
				<a href="/loginForm">로그인</a>
			</c:when>
			
			<c:otherwise>
				<h4>2)로그아웃</h4>
				<a href="/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>
