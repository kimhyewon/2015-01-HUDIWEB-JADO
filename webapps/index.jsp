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
    <h1>MAKE YOUR OWN SHOP!!!</h1>
    <c:choose>
    <c:when test="${empty userId}">
		<%@ include file="/include/signForm.jspf" %>
	</c:when>
	<c:otherwise>
		<h1>WELCOME!!!</h1>
	</c:otherwise>
	</c:choose>
</body>
<script>
	function showSellerEnroll() {
    	var contentSection = document.querySelector(".seller-container");
       	
       	if(contentSection.style.display == "none") {
            contentSection.style.display = "block";
 			contentSection.style.height = 0;

    		var nTime = setInterval(function() {
     			var _nPre = parseFloat(contentSection.style.height);
      			if(_nPre > 150) clearInterval(nTime);
      			contentSection.style.height = _nPre + 10 + "px";
   			}, 10);
        }
        else {
            contentSection.style.display="none";
        }
	}
</script>
</html>