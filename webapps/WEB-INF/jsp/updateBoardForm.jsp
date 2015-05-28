<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "update_board_form">
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
			<!-- <div id = "nav_divide_line"></div> -->
			<%@ include file="include/shopCategory.jspf" %>
		</div>

		<div id = "board_section" class="col s12 m10 l9">
			<div id = "divide_line" class="row">
				<form name="updateBoardForm" action="/shop/${shop.url}/article/update" method="post">
				<div id = "board_name">${board.name}</div>
				<div id = "article_title"><input type="text" name="title" value="${article.title}"/></div>
				<div id = "article_content"><textarea name="content" />${article.content}</textarea></div>
				<input type="hidden" name="boardId" value="${board.id}" />
				<input type="hidden" name="id" value="${article.id}" />
				<div id = "submit"><input type="submit" name="submit" value="등록하기" />	</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>