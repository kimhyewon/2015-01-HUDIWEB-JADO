<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico">
	<link rel="stylesheet" type="text/css" href="/css/jadoCSS/cart.css">
	</head>
<body class="paybody">
<div id="cart-container">
    <div id="top">
       <div class="top-content">
            <div class="logo">JADO 통합 장바구니</div>
            <div class="logout">로그아웃</div>
        </div>
    </div> 
    <div id="main">
       <div class="main-header">
            <div class="name">${userId }님 반갑습니다</div>
            <div class="store-count">
                <div class="title">장바구니에 담은 가게 개수</div>
                <div class="amount">10</div>
            </div>
            <div class="total-count">
                <div class="title">장바구니에 담은 물건의 총 개수</div>
                <div class="amount">20</div>
            </div>
        </div>
        <div class="main-body">
            <div class="title">장바구니</div>
            <div class="product-container">
                <c:forEach var="result" items="${items}" varStatus="status"> 
                <form action="/pay/info" method="post">
                <div class="product">
                    <div class="image"><img src="" alt=""></div>
                    <div class="name">${result.name }</div>
                    <div class="shop">${result.shopUrl }&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;
                        <%-- <div class="price">단가 : ${result.price }</div>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp; --%>
                        <div class="amount">수량 : ${result.amount }</div>
                    </div>
                    <div class="paybtn">구매</div>
                </div>
                <input path="shopUrl" value="${result.id }"/>
				<input path="productId" value=""/>
				<input path="productAmount"  value=""/>
                </form>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/jado.js"></script>
</html>