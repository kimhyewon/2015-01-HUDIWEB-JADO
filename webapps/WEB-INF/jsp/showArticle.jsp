<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "show_article">
	<%@ include file="include/top.jspf" %>
	<div id = "board_name">${board.name}</div>
	<div id = "show_article_title">${article.title}</div>
	<div id = "show_article_content">${article.content}</div>
	<input type="hidden" name="boardId" value="${boardId}" />
	<ul>
		<li>
			<input type="submit" name="submit" formaction="" value="등록"/>
		</li>
		<li>
			<input type="submit" name="submit" formaction="" value="수정"/>
		</li>
		<li>
			<input type="submit" name="submit" formaction="" value="삭제"/>
		</li>	
	</ul>
</body>
</html>