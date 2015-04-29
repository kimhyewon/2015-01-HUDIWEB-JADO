<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id="board">
	<%@ include file="include/top.jspf" %>
	<div id = "board_name">${shop.boards.board.name[1]}</div>
	<c:forEach var="board" items="${shop.board.articles}">
		<div class = "nav_content col s2 m2 l2"><a href="/board">
			${article.title}
		</a></div>
	</c:forEach>
	<div id = "write_button"><a href="board/write">글쓰기</a></div>
	
</body>
</html>