<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="paymentInfo" cssClass="" action="/pay/info" method="post">

		Shop Url <br>
		<form:input path="shopUrl" value="testShopUrl"/><br><br>
		<form:errors path="shopUrl"  cssClass=""   /><br><br>
		Product Id<br>
		<form:input path="productId" value="1"/><br><br>
		<form:errors path="productId"  cssClass=""   /><br><br>
		
		수량<br>
		<form:input path="productAmount" value="2"/><br><br>
		<form:errors path="productAmount"  cssClass=""   /><br><br>
		
		<button>결제하기</button>
	</form:form>
</body>
<script>
	document.querySelector("form button").addEventListener("click", function(){
		var e = document.querySelector("form input[name='productId']"); 
		if(e.value === "") {
			e.value = 0;
		} 
		document.querySelector("form").submit();
	});
</script>
</html>