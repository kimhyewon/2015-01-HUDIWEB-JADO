<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body class="main">
    <%@ include file="include/top.jspf" %>
    <c:choose>
    <c:when test="${empty userId}">
		<h1>잘못된 경로 입니다! 회원가입을 해주세요 </h1>
		<a href="/">홈으로</a>
	</c:when>
	<c:otherwise>
		<%@ include file="include/signForm.jspf" %>
	</c:otherwise>
	</c:choose>
</body>

<script src="/js/jado_lib.js"></script>
<script src="/js/jado.js"></script>
</html>