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
	결제 정보 입니다
	<br> 아래 정보대로 결제를 진행합니다.
	<br> 물건을 구매하신 shop
	<br> ${payInfo.shopUrl}
	<br> 구매하신 물건 번호
	<br> ${payInfo.productId}
	<br> 결제요청 회원 아이디
	<br>
	${userId}
	<br> 결제 금액
	<br> ${payInfo.price}
	<br>

	<br>
	<br> 위 정보를 가지고 결제를 진행할 카드사를 선택해 주세요
	<br>
	<br> 결제 요청 카드사
	<br>
	<form action="/pay/process" method="post">
		<select name="cardCompany" value="">
			<option value="국민">국민</option>
			<option value="신한">신한</option>
			<option value="하나">하나</option>
			<option value="우리">우리</option>
			<option value="외환">외환</option>
			<option value="기업">기업</option>
			<option value="농협">농협</option>
			<option value="산업">산업</option>
			<option value="수협">수협</option>
			<option value="한국수출입">한국수출입</option>
			<option value="씨티">씨티</option>
			<option value="SC제일">SC제일</option>
			<option value="새마을">새마을</option>
			<option value="신협">신협</option>
			<option value="우체국">우체국</option>
			<option value="경남">경남</option>
			<option value="광주">광주</option>
			<option value="대구">대구</option>
			<option value="부산">부산</option>
			<option value="전북">전북</option>
			<option value="제주">제주</option>
			<option value="기타">기타</option>
		</select>
		<input name="userId" value="${userId}" />
		<input name="shopUrl" value="${payInfo.shopUrl}"/>
		<input name="productId" value="${payInfo.productId}"/>
		<input name="price" type="hidden" value="${payInfo.price}" />
		<button type="submit">결제하기</button>
	</form>
</body>
</html>