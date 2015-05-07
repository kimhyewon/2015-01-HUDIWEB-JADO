<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "show_article">
	<%@ include file="include/top.jspf" %>

	<!-- article 본문 부분 --> 
	<input type="hidden" name="boardId" value="${board.id}" />
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
	<div id ="update_button"><a href="/board/update/${board.id}/${article.id}">글 수정</a></div>

	<form method="post" action="/board/delete" >
		<input type="hidden" name="boardId" value="${board.id}" />
		<input type="hidden" name="articleId" value="${article.id}" />
		<input type="submit" value="글 삭제" />
	</form>
</body>
</html>