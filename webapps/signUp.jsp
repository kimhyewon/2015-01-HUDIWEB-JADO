<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jado</title>
</head>
<body>
    <%@ include file="/include/top.jspf" %>
    <h1>SHOPPING!!!</h1>
    <h2>지금 가입하시면<br>JADO에서 운영하는<p>&{numberOfShops}</p>개의 쇼핑블로그를<br>이용할 수 있습니다!</h2>
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