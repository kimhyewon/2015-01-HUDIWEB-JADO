<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "show_article">
	<%@ include file="include/top.jspf" %>

	<div id = "header" class="row">
		<div id = "title" class="col s12 m12 l12">${shop.title}
		</div>
	</div>
	<div id = "nav_bar" class="row">
		<div id = "white_block" class="col shide m3 l3"></div>
		<div id ="nav_content_con" class ="col s12 m6 l6">
			<div class = "nav_content col s2 m2 l2"><a href="/user">LOGIN</a></div>
			<div class = "nav_content col s2 m2 l2"><a href="/user/login">JOIN</a></div>
			<div class = "nav_content col s2 m2 l2"><a href="">MYPAGE</a></div>
			<div class = "nav_content col s2 m2 l2"><a href="">CART</a></div>
			<c:forEach var="board" items="${shop.boards}">
				<div class = "nav_content col s2 m2 l2"><a href="/board/${board.id}">
					${board.name}
				</a></div>
			</c:forEach>

			<div class = "nav_content col s2 m2 l2"><a href="/setting">SETTING</a></div>
			<div id = "nav_content col s2 m2 l2">
			</div>
		</div>
		<div id = "white_block" class="col shide m3 l3"></div>
	</div>
	<div id = "body_con" class="row">
		<div id = "white_block" class="col shide m1 l1"></div>
		<div id = "category" class="row col s12 m2 l2">
			<c:forEach var="category" items="${shop.categorys}">
				<div class = "category_list row col s1.5 m12 l12">
					<a href="/category/product/${shop.url}/${category.id}">${category.name}</a>
				</div>
			</c:forEach>
		</div>
	</div>



	

	<!-- article 본문 부분 --> 
	<input type="hidden" name="boardId" value="${board.id}" />
	<input type="hidden" name="shopUrl" value="${shop.url}" />
	<div id = "show_article_title">${article.title}</div>
	<div id = "show_article_author"></div> 
	<div id = "show_article_date">${article.articleTime}<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="" /></div>
	<div id = "show_article_content">${article.content}</div>
	<input type="hidden" name="boardId" value="${boardId}" />


	<!-- 댓글 작성 부분 -->
	<div class="answerWrite">
		<form method="post" action="/board/answer/save" >
			<input type="hidden" name="boardId" value="${board.id}" />
			<input type="hidden" name="articleId" value="${article.id}" />
			<input type="hidden" name="shopUrl" value="${shop.url}" />
			<div id = "comment_writer">아이디</div>
			<input type="text" name="userId" id="userId" />
			<div id = "comment_content">내용</div>
			<textarea name="content" id="content"></textarea>
			<input type="submit" value="댓글 저장" />
		</form>
	</div>

	<!-- 댓글 리스트 -->
	<div class="comments">
		<c:forEach var="articleComment" items="${comments}">
			<form method="post" action="/board/answer/delete" >
				<input type="hidden" name="shopUrl" value="${shop.url}" />
				<input type="hidden" name="boardId" value="${board.id}" />
				<input type="hidden" name="articleId" value="${article.id}" />
				<input type="hidden" name="userId" value="${articleComment.userId}" />
				<input type="hidden" name="commentTime" value="${articleComment.commentTime}" />
				<div class="comment">
					<div class="comment-metadata">
						<span class="comment-author">${articleComment.userId}</span> 
						<span class="comment-date" value="">${articleComment.commentTime}</span>
					</div>
					<div class="comment-content">
						<div class="about">내용 : ${articleComment.content}</div> 
						<div class="comment-delete-button" >
							<input type="submit" value="댓글 삭제"/>
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
	</div>

	<!-- article 본문 수정, 삭제  --> 
	<div id ="update_button"><a href="/board/update/${shop.url}/${board.id}/${article.id}">글 수정</a></div>

	<form method="post" action="/board/delete" >
		<input type="hidden" name="shopUrl" value="${shop.url}" />
		<input type="hidden" name="boardId" value="${board.id}" />
		<input type="hidden" name="articleId" value="${article.id}" />
		<input type="submit" value="글 삭제" />
	</form>
</body>
</html>