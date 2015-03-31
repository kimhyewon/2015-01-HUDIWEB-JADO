<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <%@ include file="/include/main.jspf" %>
    <!-- <h2>지금 가입하시면<br>Ne#에서 운영하는<br>모든 쇼핑블로그를<br>이용할 수 있습니다!</h2> -->
    <c:choose>
     <c:when test="${empty userId}">
		 <%@ include file="/include/signForm.jspf" %>
	   </c:when>
	   <c:otherwise>
		 <h1>WELCOME!!!</h1>
	   </c:otherwise>
	  </c:choose>
</body>
<!-- index.jsp && dummp blog 필요 && top.jspf 있는곳 에서 필요-->
<!-- <script src="/javascripts/submit.js"></script> -->

<script src="/js/lib/sha256.js"></script>
<script src="/js/encrypt_password.js"></script>

<script src="/js/form_verify.js"></script>
<!-- <script src="/javascripts/show_seller_enroll.js"></script> -->

<!-- rsa 추가 -->
<script src="/js/lib/jsbn.js"></script>
<script src="/js/lib/rsa.js"></script>
<script src="/js/lib/ec.js"></script>
<script src="/js/lib/rng.js"></script>
<script src="/js/lib/prng4.js"></script>
<script src="/js/lib/base64.js"></script>
</html>