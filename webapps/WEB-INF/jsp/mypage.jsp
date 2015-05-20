<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "my_page">
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
            <%@ include file="include/shopCategory.jspf" %>
        </div>

    <div>
        ${user.id}<br>
        ${user.name}<br>
        ${user.phone}<br>
        ${user.address}<br>
    </div>
    <div>
        <c:forEach var="payment" items='${payments}'>
            ${payment.bank} <br>
            ${payment.realPrice} 원<br>
            ${payment.payTime}<br>
            ${payment.amount}개 <br>   
            ${payment.id} <br>
            ${payment.categoryId}<br>
            ${payment.name} <br>
            ${payment.price} 원<br>
        </c:forEach>
        <br>
        <br>
         총 ${paymentsTotal} 원 입니다.
    </div>

</body>

<script src="/js/jado_lib.js"></script>
<script src="/js/jado.js"></script>
</html>