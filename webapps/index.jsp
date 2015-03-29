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
    <c:choose>
    <c:when test="${empty userId}">
    	<%@ include file="/include/main.jspf" %>
    	<%@ include file="/include/signForm.jspf" %>
	</c:when>
	<c:otherwise>
		<h1>Welcome!! Redirect 처리 추가 예정</h1>
	</c:otherwise>
	</c:choose>
</body>

<!-- index.jsp && dummp blog 필요 -->
<script src="/javascripts/submit.js"></script>

<!-- index.jsp && sinUp.jsp && login.jsp 필요 -->
<script src="/javascripts/lib/sha256.js"></script>
<script src="/javascripts/encrypt_password.js"></script>

<!-- index.jsp && sinUp.jsp 필요 -->
<script src="/javascripts/form_verify.js"></script>
<script src="/javascripts/show_seller_enroll.js"></script>

<script>

</script>
</html>