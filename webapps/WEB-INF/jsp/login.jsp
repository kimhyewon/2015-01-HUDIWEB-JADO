<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body class="main" onClick='rng_seed_time();' onKeyPress='rng_seed_time();'>
	<%@ include file="include/top.jspf" %>
	<div class="row center formContainer">
		<c:if test="${not empty errorMessage}">
			<label class="error">${errorMessage}</label>
		</c:if> 
		
		<c:url value="/j_spring_security_check" var="loginUrl"/>
		<form class="encrypt_form" action="${loginUrl}" method="post" autocomplete="off">
			<div class="col m1 l2 dummy">dummy</div>
			<div id="formUser" class="col s12 m5 l4 ">
				<h2>로그인</h2>
				<ul>
					<li>
					 	<input type ="text" name="userId" placeholder="E-mail">
						<input type="hidden" name="idEncryption">
					</li>
					<li>
						<input type="password" name="password" placeholder="비밀번호">
						<input type="hidden" name="pwEncryption">
					</li>
					<li>
						<input type="submit" name="submit" value="로  그  인"/>
					</li>
				</ul>
			</div>
			<c:choose>
			    <c:when test="${empty url}">
					<input type="hidden" name="url" value="">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="url" value="${url}">
				</c:otherwise>
			</c:choose>
			<input type="hidden" name="rsaPublicKeyExponent" value="${publicKeyExponent}" />
			<input type="hidden" name="rsaPublicKeyModulus" value="${publicKeyModulus}" />
		</form>
	</div>
</body>

<script src="/js/jado_lib.js"></script>
<script src="/js/jado.js"></script>

</html>