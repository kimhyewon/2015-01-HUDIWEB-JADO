<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Ne#</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="/css/lib/reset.css">
	<link rel="stylesheet" type="text/css" href="/css/lib/grid.css">
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	<link rel="stylesheet" type="text/css" href="/css/top.css">
	<link rel="stylesheet" type="text/css" href="/css/mainFront.css">
	<link rel="stylesheet" type="text/css" href="/css/mainInfo.css">
	<link rel="stylesheet" type="text/css" href="/css/signForm.css">
</head>
<body>
    <%@ include file="include/top.jspf" %>
    <c:choose>
    <c:when test="${empty userId}">
    	<%@ include file="include/main.jspf" %>
    	<%@ include file="include/info.jspf" %>
    	<%@ include file="include/signForm.jspf" %>
	</c:when>
	<c:otherwise>
		<h1>Welcome!! Redirect 처리 추가 예정</h1>
	</c:otherwise>
	</c:choose>
</body>

<!-- index.jsp && dummp blog 필요 && top.jspf 있는곳 에서 필요-->
<script src="/js/SaveUrl.js"></script>
<!-- index.jsp && sinUp.jsp && login.jsp 필요 -->
<script src="/js/lib/sha256.js"></script>
<script src="/js/EncryptPassword.js"></script>
<!-- index.jsp && sinUp.jsp 필요 -->
<script src="/js/FormValidation.js"></script>
<!-- rsa 추가 -->
<script src="/js/lib/jsbn.js"></script>
<script src="/js/lib/rsa.js"></script>
<script src="/js/lib/ec.js"></script>
<script src="/js/lib/rng.js"></script>
<script src="/js/lib/prng4.js"></script>
<script src="/js/lib/base64.js"></script>
<!-- scroll Animation -->
<script src="/js/ScrollAction.js"></script>
<script src="/js/ShowSellerEnroll.js"></script>
</html>