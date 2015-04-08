<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ne#</title>
</head>
<body>
	${errorMessage}
	<br> 죄송합니다.
	<br> 암호화 준비 과정에 문제가 발생하여 요청하신 작업을 취소하였습니다.
	<br> 다시 시도해 주세요.
	<br>
	<a href="/">홈으로 </a>
	<a href="/user/login">로그인 다시 하기</a>
</body>
</html>
