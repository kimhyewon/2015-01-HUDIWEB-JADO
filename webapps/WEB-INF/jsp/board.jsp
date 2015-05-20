<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="include/head.jspf" %>
<body id="board">
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
					<div id = "board_name" class="row">${board.name}</div>
					<div id = "article_list" class="row">
						<table>
							<tr>
								<td style="width:10%">NO</td>
								<td style="width:4%"></td>
								<td style="width:*">SUBJECT</td>
								<td style="width:15%">DATE</td>
							</tr>
							<tr style="height:3px;"></tr>
							<c:forEach var="article" items="${articles}">
								<tr>
									<td>${article.id}</td>
									<td></td>
									<td align="left"><a href="/board/show/${shop.url}/${board.id}/${article.id}">${article.title}</a></td>
									<td>${article.articleTime}</td>
								</tr>
							</c:forEach>
						</table>
					</div>	
					<div id = "write_button" class="row"><a href="/board/write/${shop.url}/${board.id}">글쓰기</a></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="/js/jado.js"></script>
<script src="/js/jadoJS/jado.navCategoryEffect.js"></script>
</html>