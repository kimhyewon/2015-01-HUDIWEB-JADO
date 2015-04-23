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
	결제 성공<br><br>
	
	${userId} 고객님<br>
	상점 " ${shopUrl} " 에서 구매한<br><br>
	제품 "${productName} " 구매를 위해 <br><br>
	" ${cardCompany} 카드" 로 진행한 <br><br>
	" ${price} " 원 결제에 성공하였습니다<br> <br>
</body>
</html>