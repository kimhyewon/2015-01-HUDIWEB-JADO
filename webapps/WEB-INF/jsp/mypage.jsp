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

        <div id = "user_info_con">   
            <div id = "info_section" class="col s12 m9 l9">
                <div id = "divide_line" class="row">
                    <div id = "board_name" class="row">MY PAGE</div>
                    <div id = "info_list" class="row">
                        <div class = "info_title" class="row">회원 정보</div>
                        <table id = "user_info" class="row">
                            <tr>
                                <td style="width:35%; background-color:#E8E8E8;">ID</td>
                                <td style="width:75%">${user.id}</td>
                            </tr>
                            <tr>
                                <td style="width:35%; background-color:#E8E8E8">name</td>
                                <td style="width:75%">${user.name}</td>
                            </tr>
                            <tr>
                                <td style="width:35%; background-color:#E8E8E8">phone</td>
                                <td style="width:75%">${user.phone}</td>
                            </tr>
                            <tr>
                                <td style="width:35%; background-color:#E8E8E8">address</td>
                                <td style="width:75%">${user.address}</td>
                            </tr>
                        </table>
                    </div>
                    <div id = "order_history">
                        <div class = "info_title" class="row">구매 내역</div>
                        <table id = "order_info" class="row">
                            <tr>
                                <td>NO</td>
                                <td>상품명</td>
                                <td>수량</td>
                                <td>결재 금액</td>
                                <td>결제 은행</td>
                                <td>결제 시간</td>
                            </tr>
                            <c:forEach var="payment" items='${payments}'>
                            <tr>
                                <td>${payment.id}</td>
                                <td>${payment.name}</td>
                                <td>${payment.amount}</td>
                                <td>${payment.realPrice}</td>
                                <td>${payment.bank}</td>
                                <td>${payment.payTime}</td>
                            </tr>
                            </c:forEach>
                        </table>
                        <div id = "total_price" class="row">총 결제 금액은 ${paymentsTotal}원 입니다.</div>
                    </div>
                </div>
            </div>
        </div>
</body>

<script src="/js/jado_lib.js"></script>
<script src="/js/jado.js"></script>
</html>