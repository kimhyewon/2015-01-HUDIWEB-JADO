<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
	<link rel="shortcut icon" type="image/x-icon" href="/img/favicon.ico">
	<title>${shop.title}</title>
	<link rel="stylesheet" type="text/css" href="/css/jado.css">		
</head>
<%@ include file="include/needEmailVerifyInfo.jspf" %>
<body id="shop2">
	<div id="header" class="row">
		<div id="title" class="col s12 m12 l12"><a href="/shop/${shop.url}">${shop.title}</a></div>
	</div>
	<div id="nav_bar" class="row">
		<div id="white_block" class="col shide m3 l3"></div>
		<div id="nav_content_con" class="col s12 m6 l6">
			<%@ include file="include/shopNav.jspf" %>
		</div>
		<div id="white_block" class="col shide m3 l3"></div>
	</div>
	<div id="body_con" class="row">
		<div id="white_block" class="col shide m1 l1"></div>
		<div id="category" class="row col s12 m2 l2">
			<%@ include file="include/shopCategory.jspf" %>
		</div>
		<div id = "main_img_con" class=" col s12 m8 l8">
			<div id ="main_img" ><img src='${shop.mainUrl}' style="width:100%; "></div>
		</div>
		<div id = "white_block" class="col shide m1 l1"></div>
	</div>

	<div id = "special_product_con" class="row">
		<div id = "white_block" class="col shide m1 l1"></div>
		<div class = "special_product col shide m3 l3"><img src="${shop.subImg1Url}" style="width:100%; height=100%;"></div>
		<div class = "special_product col shide m3 l3"><img src="${shop.subImg2Url}" style="width:100%; height=100%;"></div>
		<div class = "special_product col shide m3 l3"><img src="${shop.subImg3Url}" style="width:100%; height=100%;"></div>
		<div id = "side_block" class="col shide m1 l1"><img src="/img/side_block.png" style="width:76%; height=70%;"></div>
	</div>

	<div id = "product_con" class="row">
		<div id = "black_line" class="row col s12 m12 l12"></div>
		<div id = "product_text">PRODUCT</div>

		<div id = "white_block2" class="col shide m1 l1"></div>
		<div id = "product_section" class="row">
			<c:forEach var="product" items="${products}">
				<div class = "product col s5 m3 l3">
					<a href="/shop/${shop.url}/product/${product.id}">
					<img src="${product.imgUrl}" style="width:100%; height=100%;">
					
					<div class="product_text_con">
						<div class="product_text">${product.name}</div>
						<div class="price">${product.price}원</div>
					</div>
					</a>
				</div>
			</c:forEach>
		</div>

	</div>
	<div id = "black_line" class="row col s12 m12 l12"></div>
	<div id = "footer" class="row">TEL. ${shop.phone}<br />
	${shop.footer}	
	<br />
	</div>
</body>
</html>