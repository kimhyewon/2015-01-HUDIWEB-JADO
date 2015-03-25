<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jado</title>
</head>
<body>
	<%@ include file="/include/top.jspf" %>
	
	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography"></section>
				<div class="page-header">
					<h1>개인정보수정</h1>
				</div>
				
				<form class="form-horizontal" action="/user/edit" method="post">
					<c:if test="${not empty errorMessage}">
					<div class="control-group">
						<label class="error">${errorMessage}</label>
					</div>
					</c:if>
					<div class="control-group">
						<label class="control-label" for="userId">email</label>
						<div class="controls">
							<input type="hidden" name="userId" value="${userId}" placeholder=""/>
							${userId}
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input type="password" id="password" name="password" value="" placeholder="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="checkPassword">비밀번호 확인</label>
						<div class="controls">
							<input type="password" id="checkPassword" name="checkPassword" value="" placeholder="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<input type="hidden" name="name" value="${customer.name}" placeholder="${customer.name}"/>
							${customer.name}
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="phone">전화번호</label>
						<div class="controls">
							<input type="text" id="phone" name="phone" value="${customer.phone}" placeholder="${customer.phone}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="address">주소</label>
						<div class="controls">
							<input type="text" id="address" name="address" value="${user.address}" placeholder="${user.address}">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<c:choose>
								<c:when test="${isSeller}">
									<input type="checkbox"  id="isSeller" name="isSeller" value="true" checked/>
									<p>판매자 등록을 원하시면 체크해주세요.</p>
								</c:when>
								<c:otherwise>
									<input type="checkbox"  id="isSeller" name="isSeller" value="true" />
									<p>판매자 등록을 원하시면 체크해주세요.</p>
								</c:otherwise>
							</c:choose>
						</div>			
					</div>
					<div class="control-group">
						<label class="control-label" for="shopUrl">쇼핑몰 URL</label>
						<div class="controls">
							<input type="text" id="shopUrl" name="shopUrl" value="${seller.shopUrl}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="shopPhone">쇼핑몰 대표전화</label>
						<div class="controls">
							<input type="text" id="shopPhone" name="shopPhone" value="${seller.shopPhone}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="bank">은행</label>
						<div class="controls">
							<select name="bank">
								<option value="국민">국민</option>
								<option value="신한">신한</option>
								<option value="하나">하나</option>
								<option value="우리">우리</option>
								<option value="외환">외환</option>
								<option value="기업">기업</option>
								<option value="농협">농협</option>
								<option value="산업">산업</option>
								<option value="수협">수협</option>
								<option value="한국수출입">한국수출입</option>
								<option value="씨티">씨티</option>
								<option value="SC제일">SC제일</option>
								<option value="새마을">새마을</option>
								<option value="신협">신협</option>
								<option value="우체국">우체국</option>
								<option value="경남">경남</option>
								<option value="광주">광주</option>
								<option value="대구">대구</option>
								<option value="부산">부산</option>
								<option value="전북">전북</option>
								<option value="제주">제주</option>
								<option value="기타">기타</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="bankAccount">계좌번호</label>
						<div class="controls">
							<input type="text" id="bankAccount" name="bankAccount" value="${seller.bankAccount}" placeholder="">
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">수정</button>
						</div>
					</div>
				</form>
				<form class="form-horizontal" action="/user/delete" method="get">
					<div class="control-group">
						<button type="submit" name="deleteUser" value="true">회원탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>