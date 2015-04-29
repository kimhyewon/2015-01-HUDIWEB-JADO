<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>

<body id = "board_form">
	<%@ include file="include/top.jspf" %>
	<form name="boardForm" action="" method="post">
		<div id = "board_name">공지사항</div>
		<div id = "article_title"><input type="text" name="article_title" placeholder="제목을 입력하세요."/></div>
		<div id = "article_content"><textarea name="article_content" placeholder="내용을 입력하세요." /></textarea></div>
		<div id = "submit"><input type="submit" a href="board/write" value="등록하기" />	</div>
	</form>
</body>
</html>