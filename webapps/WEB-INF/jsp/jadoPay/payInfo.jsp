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
			<span>결제 진행</span>
		</div>
		<div class="info col l2 m122 s12">
			<span>
				기분좋은 구매! JADO와 함께<br>
				${userId} 고객님의 결제 요청 정보입니다
			</span>
		</div>
	</div>

	<form action="/pay/process" method="post">
	<div class="row">
			<div class="payInfoTable col l12 m12 s12">
		
			<table class="col l12 m12 s12">
				<thead>
					<tr>
						<th>결제요청 내역</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>구매 상점</td>
						<td>${payInfo.shopUrl}</td>
					</tr>
					<tr>
						<td>구매할 상품</td>
						<td>${payInfo.productId}</td>
					</tr>
					<tr>
						<td>구매 회원</td>
						<td>${userId}</td>
					</tr>
					<tr>
						<td>결제 예정 금액</td>
						<td>${payInfo.price} 원</td>
					</tr>
					<tr>
						<td>카드사 선택</td>
						<td>
							<span>
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
							</span>
						</td>
					</tr>
				</tbody>
			</table>
			
		</div>	
	</div>
		
		<input type="hidden" name="userId" value="${userId}" />
		<input type="hidden" name="shopUrl" value="${payInfo.shopUrl}"/>
		<input type="hidden" name="productId" value="${payInfo.productId}"/>
		<input type="hidden" name="price" type="hidden" value="${payInfo.price}" />
		
		<button type="submit">결제하기</button>
	</form>
</div>
</body>
</html>