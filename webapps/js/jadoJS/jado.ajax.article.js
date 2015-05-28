window.addEventListener("load", function() {
	setAddFormBoxByArticle();
	setDeleteFormBoxByArticle(); 
})
//article 댓글 작성 
function setAddFormBoxByArticle () {
	var formListForWrite = document.querySelectorAll('#show_article #answerWrite input[type=submit]');
	for ( var j=0 ; j < formListForWrite.length ; j++) {
		formListForWrite[j].addEventListener('click', writeArticleComments);
	}
}

//article 댓글 삭제 
function setDeleteFormBoxByArticle () {
	var formListsForDelete = document.querySelectorAll('#show_article #comments input[type=image]');
	for ( var j=0 ; j < formListsForDelete.length ; j++) {
		formListsForDelete[j].addEventListener('click', deleteArticleComments);
	}
}

function writeArticleComments(e) {
	 e.preventDefault();
	 var commentForm = e.currentTarget.form;
	 var url = commentForm[3].value;
	 var params = "articleId=" + commentForm[0].value + "&userId=" + commentForm[1].value +
	 "&content=" + commentForm[2].value;

	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
		 	var ajaxResult = request.responseText;
		 	ajaxResult = JSON.parse(ajaxResult);
		 	var commentsMaker = new ArticleCommentsMaker(ajaxResult);
		 	setFormNone();
		 }
	 }
	 request.send(params);
}

function deleteArticleComments(e) {
	 e.preventDefault();
	 var commentForm = e.currentTarget.form;
	 var url = commentForm[3].value;
	 console.log(url);
	 var params = "articleId=" + commentForm[0].value + "&userId=" + commentForm[1].value + 
	 "&commentTime=" + commentForm[2].value;
	 
	 var request = new XMLHttpRequest();
	 request.open("POST", url, true);
	 request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	 
	 request.onreadystatechange = function() {
		 if(request.readyState == 4 && request.status == 200) {
		 	var ajaxResult = request.responseText;
		 	ajaxResult = JSON.parse(ajaxResult);
			console.log("hello");
			console.log(ajaxResult);
		 	var commentsMaker = new ArticleCommentsMaker(ajaxResult);
		 }
	 }
	 request.send(params);
}
function ArticleCommentsMaker (jComments) {
	var jComments = jComments;
	var result = "";
	for (var i = 0; i < jComments.length; i++) {
		var comment = new ArticleComment(jComments[i]);
		result += comment.template;
	};
	document.getElementById("comments").innerHTML = result;
	setDeleteFormBoxByArticle();
}

function ArticleComment (jComment) {
	this.jComment = jComment;
	this.template = this.setTemplate();
}

ArticleComment.prototype.setTemplate = function() {
	var template = "<form><input type='hidden' name='articleId' value='${articleComment.articleId}' /><input type='hidden' name='userId' value='${articleComment.userId}' /><input type='hidden' name='commentTime' value='${articleComment.commentTime}' /><input type='hidden' name='url' value='/api/article/comment/delete'><div class='comment'><table><tr><td style='width:15%; table-layout:fixed; word-break:break-all;'><span class='comment-author'>${articleComment.userId}</span></td> <td style='width:65%; table-layout:fixed; word-break:break-all;' align='left' ><div class='about'>${articleComment.content}</div></td> <td style='width:24%; table-layout:fixed; word-break:break-all;'><span class='comment-date' value=''>${articleComment.commentTime}</span></td><td style='width:5%; table-layout:fixed; word-break:break-all;' ><input type='image' src='/img/xbutton.png' style='width:10px; height=10px;'></td></tr></table></div></form>";
	template = template.replace("${articleComment.articleId}", ""+this.jComment.articleId);
	template = template.replace("${articleComment.userId}", this.jComment.userId);
	template = template.replace("${articleComment.commentTime}", this.jComment.commentTime);
	template = template.replace("${articleComment.userId}", this.jComment.userId);
	template = template.replace("${articleComment.content}", this.jComment.content);
	template = template.replace("${articleComment.commentTime}", this.jComment.commentTime);
	return template;
};

