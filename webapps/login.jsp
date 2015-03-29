<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ne#</title>
</head>
<body onClick='rng_seed_time();' onKeyPress='rng_seed_time();'>
	<form class="form_horizontal encrypt_form"  action="/user/login"  method="post">
	 	<c:if test="${not empty errorMessage}">
		<div class="control-group">
			<label class="error">${errorMessage}</label>
		</div>
		</c:if> 

		
		<div class="title"><h1>로그인</h1></div>
		<div class="control-group">
			<label class="control-label" for="userId">사용자 아이디</label>
			<div class="controls">
				<input type ="text" name="userId">
				<input type="hidden" name="idEncryption" value="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">비밀번호</label>
			<div class="controls">
				<input type ="password" id="password" name="password" placeholder="">
				<input type="hidden" name="pwEncryption" value="">

			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="hidden" name="rsaPublicKeyModulus" value="${publicKeyModulus}>" />
            	<input type="hidden" name="rsaPublicKeyExponent" value="${publicKeyExponent}>" />
				<input type="hidden" name="url" value="${url}">
				<input type="submit" class="btn btn-primary" value="로그인"/>	
				<a href = "/user"> 회원가입</a>
			</div>
		</div>
	</form>
</body>
<!-- index.jsp && sinUp.jsp && login.jsp 필요 -->
<script src="/javascripts/lib/sha256.js"></script>
<script src="/javascripts/encrypt_password.js"></script>

<!-- rsa 추가 -->
<script src="/javascripts/lib/jsbn.js"></script>
<script src="/javascripts/lib/rsa.js"></script>
<script src="/javascripts/lib/ec.js"></script>
<script src="/javascripts/lib/rng.js"></script>
<script src="/javascripts/lib/prng4.js"></script>
<script src="/javascripts/lib/base64.js"></script>

</html>