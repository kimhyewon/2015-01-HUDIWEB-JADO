<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%@ include file="/include/top.jspf" %>
	<% if(session.getAttribute("userId") != null)
		System.out.println("로그인중");
		else
			System.out.println("로그아웃");
	%>
	<%@ include file="/include/signForm.jspf" %>
	<button><a href="/user/login">로그인</a></button>
	
</body>
</html>