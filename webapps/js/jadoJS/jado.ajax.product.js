//product 댓글 작성 
var formList = document.querySelectorAll('#answerWrite input[type=submit]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', writeComments, false);
}

function writeComments(e) {
	 e.preventDefault();
	 var commentForm = e.currentTarget.form;
	 var url = "/api/comment/create";
	 var params = "productId=" + commentForm[0].value + "&userId=" + commentForm[1].value +
	 "&content=" + commentForm[2].value;

	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
		 	var ajaxResult = request.responseText;
		 	ajaxResult = JSON.parse(ajaxResult);
		 	var commentsMaker = new CommentsMaker(ajaxResult);
		 }
	 }
	 request.send(params);
}

//product 댓글 삭제 
var formList = document.querySelectorAll('#comments input[type=image]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', deleteComments, false);
}

function deleteComments(e) {
	 e.preventDefault();
	 var commentForm = e.currentTarget.form;
	 var url = "/api/comment/delete";
	 var params = "productId=" + commentForm[0].value + "&userId=" + commentForm[1].value + "&commentTime=" + commentForm[2].value;
	 
	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
		 	var ajaxResult = request.responseText;
		 	ajaxResult = JSON.parse(ajaxResult);
		 	var commentsMaker = new CommentsMaker(ajaxResult);
		 }
	 }
	 request.send(params);
}
function CommentsMaker (jComments) {
	jComments = jComments;
	result = "";
	for (var i = 0; i < jComments.length; i++) {
		var comment = new Comment(jComments[i]);
		result += comment.template;
	};
	console.log(result);
	document.getElementById("comments").innerHTML = result;

	// for (var jComment in jComments) {
	// 	// console.log(jComment);
	// 	var sComment = new Comment(jComment);
	// 	// console.log(sComment);
	// }
	// 이거 왜 않 먹히는 건지 잘 모르겠어요
}

function Comment (jComment) {
	this.jComment = jComment;
	this.template = this.setTemplate();
}


Comment.prototype.setTemplate = function() {
	var template = "<form><input type='hidden' name='productId' value='${productComment.productId}' /><input type='hidden' name='userId' value='${productComment.userId}' /><input type='hidden' name='commentTime' value='${productComment.commentTime}' /><div class='comment'><table><tr><td style='width:15%; table-layout:fixed; word-break:break-all;'><span class='comment-author'>${productComment.userId}</span></td> <td style='width:65%; table-layout:fixed; word-break:break-all;' align='left' ><div class='about'>${productComment.content}</div></td> <td style='width:24%; table-layout:fixed; word-break:break-all;'><span class='comment-date' value=''>${productComment.commentTime}</span></td><td style='width:5%; table-layout:fixed; word-break:break-all;' ><input type='image' src='/img/xbutton.png' style='width:10px; height=10px;'></td></tr></table></div></form>";
	template = template.replace("${productComment.productId}", ""+this.jComment.productId);
	template = template.replace("${productComment.userId}", this.jComment.userId);
	template = template.replace("${productComment.commentTime}", this.jComment.commentTime);
	template = template.replace("${productComment.userId}", this.jComment.userId);
	template = template.replace("${productComment.content}", this.jComment.content);
	template = template.replace("${productComment.commentTime}", this.jComment.commentTime);
	return template;
};

