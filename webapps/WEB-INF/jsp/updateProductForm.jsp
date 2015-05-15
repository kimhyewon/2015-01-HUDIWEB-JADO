<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>

<body id = "update_product_form">
	<div id="header" class="row">
		<div id="title" class="col s12 m12 l12">${shop.title}</div>
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
		
	<form name="update_product_form" action="/category/product/update" method="post" enctype="multipart/form-data">
		<div id = "product_info">
			<div id ="product_img">
				대표 사진 ${product.imgUrl} <input type="file" name="file" value="${product.imgUrl}" />
			</div>
			<div id ="product_name">
				상품명 <input type="text" name="name" value="${product.name}"/> 
			</div>
			<div id ="product_price">
				가격 <input type="text" name="price" value="${product.price}"/> 
			</div>
			<div id ="product_stock">
				재고 <input type="text" name="stock" value="${product.stock}"/> 
			</div>
			<input type="hidden" name="categoryId" value="${category.id}" />
			<input type="hidden" name="shopUrl" value="${shop.url}" />
			<input type="hidden" name="productId" value="${product.id}" />
		</div>
		<div id = "product_desc">
			<textarea name="desc" />${product.desc}</textarea>
		</div>
		<div id = "submit"><input type="submit" name="submit" value="등록하기" />	</div>
	</form>
</body>
</html>