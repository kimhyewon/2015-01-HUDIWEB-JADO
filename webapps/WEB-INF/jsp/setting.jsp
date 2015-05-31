<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id="setting">
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


		<div id = "body_con">	
			<div id = "board_section" class="col s12 m9 l9">
				<div id = "divide_line" class="row">
					


					<div id = "setting_con" class="row">
						<div id = "board_name" class="row">기본 정보 설정</div>
						<div id = "basic_info" class="col s12 m5 l6 ">
							<h3>샵 정보</h3>
							<form method="post" autocomplete="off">
								<c:if test="${not empty errorMessage}">
								<label class="error">${errorMessage}</label>
								</c:if>
								
								<ul>
									<input type="hidden" name="url" value="${shop.url}" readonly>
									<li id ="info_line">
										<div class="info_left">타이틀</div>
										<div class="info_right">
											<input type="text" name="title" value="${shop.title}" placeholder="제목">
										</div>
									</li>
									<li id ="info_line">
										<div class="info_left">전화번호</div>
										<div class="info_right">
											<input type="text" name="phone" value="${shop.phone}" placeholder="전화번호">
										</div>
									</li>
									<li id ="info_line">
										<div class="info_left">세부정보</div>
										<div class="info_right">
											<input type="text" name="footer" value="${shop.footer}" placeholder="세부정보">
										</div>
									</li>
									<li id ="info_line">
										<input type="submit" name="submit" formaction="/setting" value="변 경 하 기"/>
									</li>
								</ul>
							</form>
						</div>
						<div id = "basic_img" class="col s12 m5 l6">
							<h3>이미지 변경하기</h3>
							<ul>
								<li>

									<li id ="info_line">
										<div class="info_left">메인 이미지</div>
										<div class="info_right">
											<form id="upload" method="post" enctype="multipart/form-data">
											<input type="hidden" name="url" value="${shop.url}">
											<input type="hidden" name="localLocation" value="/userImg/shop/main">
											<input type="hidden" name="type" value="MAIN_URL">
											<input type="file" name="file" style="width:180px; margin-left:10px;" />
											<input type="submit" name="submit" formaction="/setting/api/image" value="업 로 드"/>
									</form>
										</div>
									</li>
									<li id ="info_line">
										<div class="info_left">프로필 이미지</div>
										<div class="info_right">
											<form id="upload" method="post" enctype="multipart/form-data">
											<input type="hidden" name="url" value="${shop.url}">
											<input type="hidden" name="type" value="PROFILE_URL">
											<input type="hidden" name="localLocation" value="/userImg/shop/profile">
											<input type="file" name="file" style="width:180px; margin-left:10px;" />
											<input type="submit" name="submit" formaction="/setting/api/image" value="업 로 드"/>
									</form>
										</div>
									</li>
									<li id ="info_line">
										<div class="info_left">서브 이미지1</div>
										<div class="info_right">
											<form id="upload" method="post" enctype="multipart/form-data">
											<input type="hidden" name="url" value="${shop.url}">
											<input type="hidden" name="type" value="SUB_IMG1_URL">
											<input type="hidden" name="localLocation" value="/userImg/shop/subImg1">
											<input type="file" name="file" style="width:180px; margin-left:10px;" />
											<input type="submit" name="submit" formaction="/setting/api/image" value="업 로 드"/>
									</form>
										</div>
									</li>
									<li id ="info_line">
										<div class="info_left">서브 이미지2</div>
										<div class="info_right">
											<form id="upload" method="post" enctype="multipart/form-data">
											<input type="hidden" name="url" value="${shop.url}">
											<input type="hidden" name="type" value="SUB_IMG2_URL">
											<input type="hidden" name="localLocation" value="/userImg/shop/subImg2">
											<input type="file" name="file" style="width:180px; margin-left:10px;" />
											<input type="submit" name="submit" formaction="/setting/api/image" value="업 로 드" />
									</form>
										</div>
									</li>
									<li id ="info_line">
										<div class="info_left">서브 이미지3</div>
										<div class="info_right">
											<form id="upload" method="post" enctype="multipart/form-data">
											<input type="hidden" name="url" value="${shop.url}">
											<input type="hidden" name="type" value="SUB_IMG3_URL">
											<input type="hidden" name="localLocation" value="/userImg/shop/subImg3">
											<input type="file" name="file" style="width:180px; margin-left:10px;" />
											<input type="submit" name="submit" formaction="/setting/api/image" value="업 로 드" />
									</form>
										</div>
									</li>
								</li>
							</ul>
						</div>
					</div>


					<div id ="setting_con" class="row" >
						<div id = "board_name" class="row">테마 설정</div>
						<div class="col s12 m5 l4">
							<form  action="/setting/api/theme">
								<h3>테마 고르기</h3>
								<ul>
									<li>
										<input type="radio" name="theme" value="1" checked="checked" >A형
									</li>
									<li>
										<input type="radio" name="theme" value="2">B형
									</li>
									<li>
										<input type="submit" name="submit" value="변 경 하 기"/>
									</li>
								</ul>		
							</form>
						</div>
						<div class="col s12 m5 l4">
							<h4>미리보기</h4>
							<img src="/img/shop_theme1.png" style="width:145px; height:200px; ">		
							<img src="/img/shop_theme2.png" style="width:145px; height:200px;">	
						</div>
					</div>


					<div id ="setting_con" class="row center">
						<div id = "board_name" class="row">게시판, 카테고리 설정</div>
						<div class="col s12 m5 l4">
							<h3>공지사항 , 이벤트 게시판을 만드세요</h3>
							<button onclick="boardNew()">추가하기</button>
							<ul id="boardBefore">
								<c:forEach var="board" items="${shop.boards}">
									<li>
										<form action="/setting/api/board/delete">
											<input type="hidden" name="shopUrl" value="${shop.url}">
											<input type="text" name="name" value="${board.name}" readonly>
											<input type="submit" name="submit" value="삭제하기">
										</form>
									</li>
								</c:forEach>
							</ul>
							<form action="/setting/api/board/insert" method="post">
								<ul id="boardAfter">
									
								</ul>
								<input type="hidden" name="shopUrl" value="${shop.url}">
								<input type="submit" name="submit" value="변 경 하 기">
							</form>
						</div>
						<div class="col s12 m5 l4">
							<h2>카테고리 설정 </h2>
							<h3>상품 카테고리를 만드세요 </h3>
							<button onclick="categoryNew()">추가하기</button>
							<ul id="categoryBefore">
								<c:forEach var="category" items="${shop.categorys}">
									<li>
										<form action="/setting/api/category/delete">
											<input type="hidden" name="id" value="${category.id}">
											<input type="text" name="name" value="${category.name}">
											<input type="submit" name="submit" value="삭제하기">
										</form>
									</li>
								</c:forEach>
							</ul>
							<form action="/setting/api/category/insert" method="post">
								<ul id="categoryAfter">
									
								</ul>
								<input type="hidden" name="shopUrl" value="${shop.url}">
								<input type="submit" name="submit" value="변 경 하 기">
							</form>
						</div>
					</div>

					<h3><a href="/shop/${shop.url}">완료</a></h3>

				</div>
			</div>
		</div>




	
</body>
<script src="/js/jado.js"></script>
<script src="/js/jadoJS/Setting.js"></script>

</html>