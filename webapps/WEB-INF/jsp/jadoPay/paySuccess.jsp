<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/jadoCSS/pay.css">
<link rel="stylesheet" type="text/css" href="/css/lib/grid.css">
<link rel="stylesheet" type="text/css" href="/css/lib/reset.css">
</head>
<body>
<div id="payContainer">
	<div class="row">
		<div class="banner col l2 m2 s12">
			<span>결제 완료</span>
		</div>
		<div class="info col l2 m122 s12">
			<span>
				기분좋은 구매! JADO와 함께<br>
				${userId} 고객님의 결제 요청 처리 결과 입니다
			</span>
		</div>
	</div>

	<div class="row">
			<div class="payInfoTable col l12 m12 s12">
		
			<table class="col l12 m12 s12">
				<thead>
					<tr>
						<th>결제 요청 처리 결과</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>구매한 상점</td>
						<td>${shopUrl}</td>
					</tr>
					<tr>
						<td>구매한 상품</td>
						<td>${productName} </td>
					</tr>
					<tr>
						<td>구매 회원</td>
						<td>${userId}</td>
					</tr>
					<tr>
						<td>결제 금액</td>
						<td>${price}  원</td>
					</tr>
					<tr>
						<td>카드사 선택</td>
						<td>${cardCompany}</td>
					</tr>
				</tbody>
			</table>
			
		</div>	
	</div>
		
		<input type="hidden" name="userId" value="${userId}" />
		<input type="hidden" name="shopUrl" value="${payInfo.shopUrl}"/>
		<input type="hidden" name="productId" value="${payInfo.productId}"/>
		<input type="hidden" name="price" type="hidden" value="${payInfo.price}" />
		
		<button onclick="javascript:location.href='/shop/${shopUrl}'">쇼핑몰로 돌아가기</button>
</div>
</body>
</html>
