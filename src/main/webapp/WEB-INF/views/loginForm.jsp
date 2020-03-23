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
		<h4>로그인 페이지</h4>
		<form action="/user/loginProcess" method="post" onsubmit="crypt()">
			USERNAME : <input id="username" type="text" required>
			<br>PASSWORD : <input id="password" type="password" required>
			<input type="hidden" value="${RSAModulus}" id="RSAModulus">
			<input type="hidden" value="${RSAExponent}" id="RSAExponent">
			<input type="hidden" name="username" id="USER_ID">
			<input type="hidden" name="password" id="USER_PW">
			<br><input type="submit" value="로그인">
		</form>
	</div>
	
<script src="http://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="js/jsbn.js" type="text/javascript"></script>
<script src="js/prng4.js" type="text/javascript"></script>
<script src="js/rng.js" type="text/javascript"></script>
<script src="js/rsa.js" type="text/javascript"></script>
<script>
	function crypt(){
		var rawPassword = $("#password").val();
		var username = $("#username").val();
		
		var rsa = new RSAKey();
		rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
		
		$("#USER_ID").val(rsa.encrypt(username));
		$("#USER_PW").val(rsa.encrypt(rawPassword));
		alert($("#USER_ID").val());
		alert($("#USER_PW").val());
		
		return true;
		
	}
</script>
</body>
</html>
