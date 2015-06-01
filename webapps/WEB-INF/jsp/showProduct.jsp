<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:scriptlet>
pageContext.setAttribute("space", " ");
pageContext.setAttribute("lf", "\n");
</jsp:scriptlet>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id = "show_product">
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
			<!-- <div id = "nav_divide_line"></div> -->
			<%@ include file="include/shopCategory.jspf" %>
		</div>

		<!-- product 부분 --> 
		<div id = "product_section" class="col s12 m10 l9">
			<div id = "divide_line" class="row">
				<input id="categoryId" type="hidden" name="categorydId" value="${product.categoryId}" />
				<input type="hidden" name="productId" value="${product.id}" />
				<input type="hidden" name="shopUrl" value="${shop.url}" />
				<div id = "product_intro" class="row">
					<div id = "product_img">
						<div id = "show_product_url"><img src="${product.imgUrl}" style="width:100%; height=100%;"></div>
					</div>
					<div id = "product_info">

						<table id ="info">
							<tr>
								<td style="width:30%;height:35px; table-layout:fixed; word-break:break-all;">상품명</td>
								<td style="width:70%; table-layout:fixed; word-break:break-all;">${product.name}</td>
							</tr>
							<tr>
								<td style="width:30%;height:35px; table-layout:fixed; word-break:break-all;">가격</td>
								<td style="width:70%; table-layout:fixed; word-break:break-all;">${product.price}원</td>
							</tr>
							<tr>
								<td style="width:30%;height:35px; table-layout:fixed; word-break:break-all;">수량</td>
								<td style="width:70%; table-layout:fixed; word-break:break-all;">
									<table id="select_product_count">
									    <tr>
									    	<td style="padding-left:0px; border: 1px solid #bcbcbc;"><button id ="down_button" style="height:24px;width:23px;text-align:center;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:white;color:#CFCFCF">-</button></td>
											<td style="padding-left:0px; border: 1px solid #bcbcbc;"><input id="count" type="text" value="1" style="width:23px;text-align:center;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px"/></td>
											<td style="padding-left:0px; border: 1px solid #bcbcbc;"><button id = "up_button" style="width:23px;text-align:center;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;background-color:white;color:#CFCFCF;">+</button></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<!-- 복사해옴 시작 -->
						<form:form modelAttribute="paymentInfo" cssClass="" action="/pay/info" method="post">

							<!-- Shop Url <br> -->
							<form:input type="hidden" path="shopUrl" value="${shop.url}"/>
							<form:errors path="shopUrl"  cssClass=""   />
							<!-- Product Id<br> -->
							<form:input type="hidden" path="productId" value="${product.id}"/>
							<form:errors path="productId"  cssClass=""   />
							
							<!-- 수량<br> -->
							<form:input type="hidden" path="productAmount" />
							<form:errors path="productAmount"  cssClass=""   />
							
							<!-- <button>결제하기</button> -->
							<button id = "buy_button" style="width:65%;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px" /><a>구매하기</a>
						</form:form>
						
						<form action="/cart/put" method="post">

							<!-- Shop Url <br> -->
							<input type="hidden" name="shopUrl" value="${shop.url}"/>
							<!-- Product Id<br> -->
							<input type="hidden" name="productId" value="${product.id}"/>
							
							<!-- 수량<br> -->
							<input type="hidden" name="amount" value="1"/>
							
							<!-- <button>결제하기</button> -->
							<button id = "buy_button" style="width:65%;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px" /><a>장바구니</a>
						</form>
					
					</div>

					<script>
						// 상품 개수 카운트
						var btn1 = document.querySelector("#down_button");
						var btn2 = document.querySelector("#up_button");
						var numEle = document.querySelector("#count");

						btn1.addEventListener("click", function(){
							if(numEle.value >1) {
								numEle.value--;	
							}
						},false);

						btn2.addEventListener("click", function(){
							console.log("증가");
							numEle.value++;
							
						},false);

						// 구매 클릭시 발생
						document.querySelector("#buy_button").addEventListener("click", function(){
							var e = document.querySelector("form input[name='productId']"); 
							if(e.value === "") {
								e.value = 0;
							} 
							
							var form = document.querySelector("#paymentInfo");
							form.productAmount.value = document.querySelector("#count").value;
							document.querySelector("form").submit();
						});
					</script>

				</div>
				<div id = "show_product_content">${fn:replace(product.desc, lf, "<br/>")}</div>


				<!-- 댓글 리스트 -->
				<div id="comments">
					<c:forEach var="productComment" items="${comments}">
						<form>
							<input type="hidden" name="productId" value="${productComment.productId}" />
							<input type="hidden" name="userId" value="${productComment.userId}" />
							<input type="hidden" name="commentTime" value="${productComment.commentTime}" />
							<input type="hidden" name="url" value="/api/comment/delete">
							<div class="comment">
								<table>
									<tr>
										<td style="width:15%; table-layout:fixed; word-break:break-all;"><span class="comment-author">${productComment.userId}</span></td> 
										<td style="width:65%; table-layout:fixed; word-break:break-all;" align="left" ><div class="about">${productComment.content}</div></td> 
										<td style="width:24%; table-layout:fixed; word-break:break-all;" ><span class="comment-date" value="">${productComment.commentTime}</span></td>
										<td style="width:5%; table-layout:fixed; word-break:break-all;" ><input type="image" src="/img/xbutton.png" style="width:10px; height=10px;">
										</td>
									</tr>	
								</table>
							</div>
						</form>
					</c:forEach>
				</div>

				<!-- 댓글 작성 부분 -->
				<div id="answerWrite">
					<form>
						<input type="hidden" name="productId" value="${product.id}" />
						<div id = "comment_writer">아이디<br />
							<input type="text" name="userId" id="userId" style="width:100px; height:17px;"/>
						</div>
						<div id = "comment_content">
							<textarea name="content" id="content" style="width:545px; height:47px;"></textarea>
						</div>
						<input type="hidden" name="url" value="/api/comment/create">
						<input type="submit" value="저장" />
					</form>
				</div>

				<!-- product 본문 수정, 삭제  --> 
				<div id ="update_button"><a href="/shop/${shop.url}/product/update?productId=${product.id}">글 수정</a></div>

				<div id ="delete_button">
					<form method="get" action="/shop/${shop.url}/product/delete" >
						<input type="hidden" name="productId" value="${product.id}" />
						<input type="submit" value="글 삭제" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="/js/jado.js"></script>
</html>