<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>

<body id = "category_form">
	<%@ include file="include/blog.jspf" %>
		</div>

	<form name="category_form" action="/category/product/upload" method="post" enctype="multipart/form-data">
		<div id = "product_info">
			<div id ="product_img">
				대표 사진 <input type="file" name="file"/>
				<input type="hidden" name="url" value="${shop.url}">
				<input type="hidden" name="localLocation" value="/userImg/product/img">
				<input type="hidden" name="type" value="IMG_URL">
			</div>
			<div id ="product_name">
				상품명 <input type="text" name="name" />
			</div>
			<div id ="product_price">
				가격 <input type="text" name="price" />
			</div>
			<div id ="product_stock">
				재고 <input type="text" name="stock" />
			</div>
			<input type="hidden" name="categoryId" value="${category.id}" />
			<input type="hidden" name="shopUrl" value="${shop.url}" />
		</div>
		<div id = "product_desc">
			<textarea name="desc" placeholder="내용을 입력하세요." /></textarea>
		</div>
		<div id = "submit"><input type="submit" name="submit" value="등록하기" />	</div>
	</form>
</body>
</html>