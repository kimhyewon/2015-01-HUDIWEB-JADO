<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body class="main">
    <%@ include file="include/top.jspf" %>
    <div class="row"> 
    	<div class="col m1 l2 dummy">dummmy</div>
    	<div class="col s12 m8 l6 noticeContainer">
    		<h2>${notice.header}</h2>
    		<div class="message">
    			${notice.message}
    		</div>
    		<a class="home" href="/">홈 으 로</a>
    	</div>
    </div>
</body>

<script src="/js/jado_lib.js"></script>
<script src="/js/jado.js"></script>
</html>