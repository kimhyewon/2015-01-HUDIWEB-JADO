<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id="setting">
	<%@ include file="include/top.jspf" %>
	<div class="row center formContainer">
		<div class="col m1 l2 dummy">dummy</div>
		<form action="/setting" method="post" autocomplete="off">
			<div class="col s12 m5 l4 ">
			<c:if test="${not empty errorMessage}">
				<label class="error">${errorMessage}</label>
			</c:if>
				<h2>기본 정보 </h2>
				<h3><a href="/shop/${shop.url}">내샵 돌아가기 </a></h3>
				<ul>
					<li>
					 	<input type="text" name="url" value="${shop.url}" readonly>
					</li>
					<li>
						<input type="text" name="title" value="${shop.title}" placeholder="제목">
					</li>
					<li>
						<input type="text" name="phone" value="${shop.phone}" placeholder="전화번호">
					</li>
					<li>
						<input type="text" name="footer" value="${shop.footer}" placeholder="세부정보">
					</li>
					<li>
						<input type="submit" name="submit" value="변 경 하 기"/>
					</li>
				</ul>
			</div>
			<div class="col s12 m5 l4">
					<h2 style="opacity:0;"> _ </h2>
					<h3>이미지 추가하기</h3>
					<ul>
						<li>
							<button>업로드</button>
						</li>
						<li>
							<input type="submit" name="submit" value="업 로 드"/>
						</li>
						<li>
							<input type="submit" name="submit" value="업 로 드"/>
						</li>
					</ul>
			</div>
		</form>
	</div>
	<div class="row center formContainer" >
		<div class="col m1 l2 dummy">dummy</div>
		<div class="col s12 m5 l4">
			<form action="/setting/api/theme">
				<h2>화면 꾸미기 </h2>
				<h3>테마 고르기</h3>
				<ul>
					<li>
						<input type="radio" name="theme" value="theme1" checked="checked" >심플한 테마
					</li>
					<li>
						<input type="radio" name="theme" value="theme2">화사한 테마
					</li>
					<li>
						<input type="radio" name="theme" value="theme3">봄같은 테마
					</li>
					<li>
						<input type="radio" name="theme" value="theme4">시크한 테마
					</li>
					<li>
						<input type="submit" name="submit" value="변 경 하 기"/>
					</li>
				</ul>		
			</form>
		</div>
		<div class="col s12 m5 l4">
			미리보기
			<img width ="100%"src="/img/shop_theme1.png">
			<img src="" alt="">
			<img src="" alt="">
		</div>
	</div>
	<div class="row center formContainer">
		<div class="col m1 l2 dummy">dummy</div>
		<div class="col s12 m5 l4">
			<h2>보드 설정 </h2>
			<h3>공지사항 , 이벤트 게시판을 만드세요</h3>
			<form action="/setting/api/board/insert">
				<input type="text" name="newBoard" value="보드 이름">
				<input type="submit" name="submit" value="추가 하기"/>

				<input type="text" name="hello" value="board2">
				<input type="submit" name="submit" value="삭제 하기"/>
				<input type="text" name="hello" value="board3">
				<input type="submit" name="submit" value="삭제 하기"/>
				<input type="text" name="hello" value="board4">
				<input type="submit" name="submit" value="삭제 하기"/>
			</form>
		</div>
		<div class="col s12 m5 l4">
				<h2>카테고리 설정 </h2>
				<h3>상품 카테고리를 만드세요 </h3>
				<ul id="categories">
					
				<form action="/setting/api/category">
				</ul>
				<input type="text" name="hello" value="board2">
				<input type="text" name="hello" value="board3">
				<input type="text" name="hello" value="board4">
				</form>
		</div>
	</div>
</body>
<script src="/js/jado.js"></script>
<script src="/js/jadoJS/Setting.js"></script>

</html>