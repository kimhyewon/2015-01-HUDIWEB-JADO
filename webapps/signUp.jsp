<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ne#</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="/css/top.css" />
	<link rel="stylesheet" type="text/css" href="/css/main.css" media="screen">
	<link rel="stylesheet" type="text/css" href="/css/signForm.css" />
</head>
<body>
    <%@ include file="/include/top.jspf" %>
    <h2>지금 가입하시면<br>Ne#에서 운영하는<br>모든 쇼핑블로그를<br>이용할 수 있습니다!</h2>
    <c:choose>
     <c:when test="${empty userId}">
		 <%@ include file="/include/signForm.jspf" %>
	   </c:when>
	   <c:otherwise>
		 <h1>WELCOME!!!</h1>
	   </c:otherwise>
	  </c:choose>
</body>
<script src="/javascripts/lib/sha256.js"></script>
<script src="/javascripts/encrypt_password.js"></script>

<script src="/javascripts/form_verify.js"></script>
<script src="/javascripts/show_seller_enroll.js"></script>
</html>