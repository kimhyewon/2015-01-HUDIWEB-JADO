<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "update_board_form">
	<%@ include file="include/blog.jspf" %>
		</div>

		<div id = "board_section" class="col s12 m10 l9">
			<div id = "divide_line" class="row">
				<form name="updateBoardForm" action="/board/update" method="post">
				<div id = "board_name">${board.name}</div>
				<div id = "article_title"><input type="text" name="title" value="${article.title}"/></div>
				<div id = "article_content"><textarea name="content" />${article.content}</textarea></div>
				<input type="hidden" name="boardId" value="${board.id}" />
				<input type="hidden" name="articleId" value="${article.id}" />
				<input type="hidden" name="shopUrl" value="${shop.url}" />
				<div id = "submit"><input type="submit" name="submit" value="등록하기" />	</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>