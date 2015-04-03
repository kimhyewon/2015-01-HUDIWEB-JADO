<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Ne#</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/top.css" />
	<link rel="stylesheet" type="text/css" href="/css/main.css" media="screen">
	<link rel="stylesheet" type="text/css" href="/css/login.css" media="screen">
</head>
<body onClick='rng_seed_time();' onKeyPress='rng_seed_time();'>
	<%@ include file="/include/top.jspf" %>
	<div id="loginContainer">
		<c:if test="${not empty errorMessage}">
			<label class="error">${errorMessage}</label>
		</c:if> 
		<form class="encrypt_form" method="post">
			<h2>로그인</h2>
			<ul>
				<li>
					<input type ="text" name="userId" placeholder="E-mail">
					<input type="hidden" name="idEncryption">
				</li>
				<li>
					<input type ="password" id="password" name="password" placeholder="비밀번호">
					<input type="hidden" name="pwEncryption" value="">
				</li>
				<li>
					<input type="hidden" name="rsaPublicKeyModulus" value="${publicKeyModulus}>" />
	            	<input type="hidden" name="rsaPublicKeyExponent" value="${publicKeyExponent}>" />
					<input type="hidden" name="url" value="${url}">
					<input type="submit" formaction="/user/login" value="로  그  인"/>
				</li>
			</ul>
		</form>
	</div>
</body>

<!-- index.jsp && dummp blog 필요 && top.jspf 있는곳 에서 필요-->
<script src="/js/submit.js"></script>

<!-- index.jsp && sinUp.jsp && login.jsp 필요 -->
<script src="/js/lib/sha256.js"></script>
<script src="/js/encrypt_password.js"></script>

<!-- rsa 추가 -->
<script src="/js/lib/jsbn.js"></script>
<script src="/js/lib/rsa.js"></script>
<script src="/js/lib/ec.js"></script>
<script src="/js/lib/rng.js"></script>
<script src="/js/lib/prng4.js"></script>
<script src="/js/lib/base64.js"></script>

</html>