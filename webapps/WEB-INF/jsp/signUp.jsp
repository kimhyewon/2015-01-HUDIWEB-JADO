<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Ne#</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="/css/jado.css">
</head>
<body>
    <%@ include file="include/top.jspf" %>
    <c:choose>
    <c:when test="${empty userId}">
    	<%@ include file="include/signForm.jspf" %>
	</c:when>
	<c:otherwise>
		<h1>잘못된 경로 입니다! 이미 로그인한 사용자 예요 </h1>
		<a href="/">홈으로</a>
	</c:otherwise>
	</c:choose>
</body>

<script src="/js/jado_encrypt.js"></script>
<script src="/js/jado.js"></script>
</html>