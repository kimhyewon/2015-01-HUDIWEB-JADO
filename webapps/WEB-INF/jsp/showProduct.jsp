<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "show_article">
	<%@ include file="include/top.jspf" %>
	<!-- product 본문 부분 --> 
	<input type="hidden" name="categorydId" value="${category.id}" />
	<input type="hidden" name="productId" value="${product.id}" />
	<div id = "show_product_url"><img src="${product.imgUrl}" style="width:100%; height=100%;"></div>
	<div id = "show_product_name">${product.name}</div>
	<div id = "show_product_price">${product.price}</div> 
	<div id = "select_product_count">수량 선택</div>
	<div id = "show_product_content">${product.desc}</div>


	<!-- 댓글 작성 부분 -->
	<div class="answerWrite">
		<form method="post" action="/category/product/answer/save" >
			<input type="hidden" name="categoryId" value="${category.id}" />
			<input type="hidden" name="productId" value="${product.id}" />
			<div id = "comment_writer">아이디</div>
			<input type="text" name="userId" id="userId" />
			<div id = "comment_content">내용</div>
			<textarea name="content" id="content"></textarea>
			<input type="submit" value="댓글 저장" />
		</form>
	</div>

	<!-- 댓글 리스트 -->
	<div class="comments">
		<c:forEach var="productComment" items="${comments}">
			<form method="post" action="/category/product/answer/delete" >
				<input type="hidden" name="categoryId" value="${category.id}" />
				<input type="hidden" name="productId" value="${product.id}" />
				<input type="hidden" name="userId" value="${productComment.userId}" />
				<input type="hidden" name="commentTime" value="${productComment.commentTime}" />
				<div class="comment">
					<div class="comment-metadata">
						<span class="comment-author">${productComment.userId}</span> 
						<span class="comment-date" value="">${productComment.commentTime}</span>
					</div>
					<div class="comment-content">
						<div class="about">내용 : ${productComment.content}</div> 
						<div class="comment-delete-button" >
							<input type="submit" value="댓글 삭제"/>
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
	</div>

	<!-- product 본문 수정, 삭제  --> 
	<div id ="update_button"><a href="/category/product/update/${category.id}/${product.id}">글 수정</a></div>

	<form method="post" action="/category/product/delete" >
		<input type="hidden" name="categoryId" value="${category.id}" />
		<input type="hidden" name="productId" value="${product.id}" />
		<input type="submit" value="글 삭제" />
	</form>
</body>
</html>