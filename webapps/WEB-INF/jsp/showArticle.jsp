<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "show_article">
	<%@ include file="include/top.jspf" %>

	<!-- article 부분 --> 
	<div id = "show_article_title">${article.title}</div>
	<div id = "show_article_author"></div> 
	<div id = "show_article_date">${article.articleTime}<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="" /></div>
	<div id = "show_article_content">${article.content}</div>
	<input type="hidden" name="boardId" value="${boardId}" />


	<!-- 댓글 작성 부분 -->
	<div class="answerWrite">
		<form method="post" action="/board/saveanswer" method="post">
			<input type="hidden" name="articleId" value="${article.id}" />
			<div id = "comment_writer">아이디</div>
			<input type="text" name="userId" id="userId" />
			<div id = "comment_content">내용</div>
			<textarea name="content" id="content"></textarea>
			<input type="submit" value="저장" />
		</form>
	</div>

	<!-- 댓글 리스트 -->
	<div class="comments">
		<form method="post" action="">
			<c:forEach var="articleComment" items="${comments}">
				<input type="hidden" name="boardId" value="${boardId}">
				<input type="hidden" name="" value="">
				<div class="comment">
					<div class="comment-metadata">
						<span class="comment-author">아아아${articleComment.userId}</span> 
						<span class="comment-date" value="">${articleComment.commentTime}</span>
					</div>
					<div class="comment-content">
						<span class="about">내용 : ${articleComment.content}</span> 
						<span class="comment-delete-button" >
							<ul>
								<li>
									<input type="submit" name="submit" formaction="" value="수정"/>
								</li>
								<li>
									<input type="submit" name="submit" formaction="" value="삭제"/>
								</li>	
							</ul>
						</span>
					</div>
				</div>
			</c:forEach>
		</form>
	</div>
</body>
</html>