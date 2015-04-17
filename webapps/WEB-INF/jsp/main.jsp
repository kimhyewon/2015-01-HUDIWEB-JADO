<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/header.jspf" %>
<body class="main">
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

<script src="/js/jado_encrypt.js"></script>
<script src="/js/jado.js"></script>
</html>