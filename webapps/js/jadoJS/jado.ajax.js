//댓글 작성 
var formList = document.querySelectorAll('#answerWrite input[type=submit]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', writeComments, false);
}

function writeComments(e) {
	 e.preventDefault();
	 var commentForm = e.currentTarget.form;
	 var url = "/api/article/comment/create";
	 var params = "articleId=" + commentForm[0].value + "&userId=" + commentForm[1].value + "&content=" + commentForm[2].value;
	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);
		 }
	 }
	 request.send(params);
}

//댓글 삭제 
var formList = document.querySelectorAll('#comments input[type=image]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', deleteComments, false);
}

function deleteComments(e) {
	 e.preventDefault();
	 var commentForm = e.currentTarget.form;
	 var url = "/api/article/comment/delete";
	 var params = "articleId=" + commentForm[0].value + "&userId=" + commentForm[1].value + "&commentTime=" + commentForm[2].value;
	 
	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
			 location.reload(true);
		 }
	 }
	 request.send(params);
}

