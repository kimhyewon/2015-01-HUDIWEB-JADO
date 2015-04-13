<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="boardForm" action="board/write" method="post">
		<div id = "board_name">공지사항</div>
		<div id = "title"><input type="text" name="title" size="70"/></div>
		<div id = "content"><textarea name="content" rows="5" cols="130"></textarea></div>
		<div id = submit><input type="submit" a href="board/write" value="등록하기" />	</div>
	</form>
</body>
</html>