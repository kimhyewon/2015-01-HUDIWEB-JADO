<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
	<title>블로그</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/blog.css" media="screen">	
	<link rel="stylesheet" type="text/css" href="css/lib/grid.css">

</head>
<body>
	<div id = "header" class="row">
		<div id = "title" class="col s12 m12 l12">HEY, JADO
		</div>
	</div>
	<div id = "nav_bar" class="row">
		<div id = "white_block" class="col shide m3 l3"></div>
		<div id ="nav_content_con" class ="col s12 m6 l6">
			<div class = "nav_content col s2 m2 l2">LOGIN </div>
			<div class = "nav_content col s2 m2 l2">JOIN</div>
			<div class = "nav_content col s2 m2 l2">MYPAGE</div>
			<div class = "nav_content col s2 m2 l2">CART</div>
			<div class = "nav_content col s2 m2 l2">INFO</div>
			<div id = "nav_content col s2 m2 l2">
			</div>
		</div>
		<div id = "white_block" class="col shide m3 l3"></div>
	</div>
	<div id = "body_con" class="row">
		<div id = "white_block" class="col shide m1 l1"></div>
		<div id = "category" class="row col s12 m2 l2">
			<div class = "category_list row col s1.5 m12 l12">OUTER</div>
			<div class = "category_list row col s1.5 m12 l12">TOP</div>
			<div class = "category_list row col s1.5 m12 l12">DRESS</div>
			<div class = "category_list row col s1.5 m12 l12">PANTS</div>
			<div class = "category_list row col s1.5 m12 l12">SKIRT</div>
			<div class = "category_list row col s1.5 m12 l12">ACC</div>
			<div class = "category_list col s1.5 m12 l12">SALE</div>
			<div id ="profile_con" class ="row col shide m10 l6">
				<div id ="profile_img"><img src="img/profile.png" style="width:55%; height:55%; "></div>
				<div id = "profile_name" >Grace lilac</div>
			</div>	
		</div>

		<div id = "main_img_con" class=" col s12 m8 l8">
			<div id ="main_img" ><img src="img/main_img.jpg" style="width:100%; "></div>
		</div>
		<div id = "white_block" class="col shide m1 l1"></div>
	</div>
	<div id = "special_product_con" class="row">
		<div id = "white_block" class="col shide m1 l1"></div>
		<div class = "special_product col shide m3 l3"><img src="img/4.jpg" style="width:100%; height=100%;"></div>
		<div class = "special_product col shide m3 l3"><img src="img/10.jpg" style="width:100%; height=100%;"></div>
		<div class = "special_product col shide m3 l3"><img src="img/3.jpg" style="width:100%; height=100%;"></div>
		<div id = "side_block" class="col shide m1 l1"><img src="img/side_block.png" style="width:76%; height=70%;"></div>
	</div>

	<div id = "product_con" class="row">
		<div id = "black_line" class="row col s12 m12 l12"></div>
		<div id = "product_text">PRODUCT</div>

		<div id = "white_block2" class="col shide m1 l1"></div> <!-- 프로덕트 왼쪽 흰 여백 -->
		<div id = "product_section" class="row">
			<div class = "product col s5 m3 l3">
				<img src="img/1.gif" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
				<img src="img/11.jpg" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
				<img src="img/3.gif" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
				<img src="img/12.gif" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
			 	<img src="img/7.gif" style="width:100%; height=100%;">
			 	<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
				<img src="img/6.gif" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
				<img src="img/12.jpg" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>	
			<div class = "product col s5 m3 l3">
				<img src="img/11.gif" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
			<div class = "product col s5 m3 l3">
				<img src="img/4.gif" style="width:100%; height=100%;">
				<div class="product_text_con">
					<div class="product_text">OPS2099/Color Block<br>Pleats-droped<br>Shift Dress</div>
					<div class="price">35,000원</div>
				</div>
			</div>
		</div>

	</div>
	<div id = "black_line" class="row col s12 m12 l12"></div>
	<div id = "footer" class="row">TEL. 1599-7408<br />MON-FRI AM11- PM5 / LUNCH PM12-PM1 / SAT.SUN.HOLIDAY OFF<br />국민 431801-01-178756 / 농협 301-0150-9002-41 / 신한 100-030-100444 / 우리 1005-102-534194<br />택품반품 주소. 서울시 성동구 마장동 781-2 동대문직영(리플리) -현대택배- [1588-2121]<br /><br />COMPANY. 패스워드 주식회사 TEL. 1599-7408 MASTER. 윤선영(SHEGEM@HANMAIL.NET)<br />OWNER. 강남훈 ADDRESS: 138-848 서울특별시 송파구 백제고분로 399 (송파동,영풍빌딩지하1층)<br />BUSINESS LICENSE. 261-81-07102 MAIL ORDER LICENSE. 2014-서울송파-1052호 [사업자정보확인] <br />COPYRIGHT © LEPLEY ALL RIGHTS RESERVED. DESIGN BY LALASHOW<br /><br /><br />
	</div>
</body>
</html>

