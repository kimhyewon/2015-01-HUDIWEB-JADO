//<!-- 댓글 작성 부분 -->
//<div id="answerWrite">
//	<form method="post" action="/board/answer/save" >
//		<input type="hidden" name="boardId" value="${board.id}" />
//		<input type="hidden" name="articleId" value="${article.id}" />
//		<input type="hidden" name="shopUrl" value="${shop.url}" />
//		
//		<div id = "comment_writer">아이디<br />
//			<input type="text" name="userId" id="userId" style="width:100px; height:17px;" />
//		</div>
//		<div id = "comment_content">
//			<textarea name="content" id="content" style="width:545px; height:47px;"></textarea>
//		</div>
//		
//		<input type="submit" value="저장" />
//	</form>
//</div>
//
//<!-- 답변 쓰기 --> 
//<div class="answerWrite">
//	<form method="post" action="/api/addanswer.next">
//		<input type="hidden" name="questionId" value="${question.questionId}">
//		<label for="author">이름: </label> <input type="text" name="writer" id="writer" />
//		<label for="content">내용 : </label>
//		<textarea name="content" id="content"></textarea>
//		<input type="submit" value="저장" />
//	</form>
//</div>
//<!-- 답변 쓰기 끝  -->

var formList = document.querySelectorAll('#answerWrite input[type=submit]');
for ( var j=0 ; j < formList.length ; j++) {
	formList[j].addEventListener('click', writeComments, false);
}

function writeComments(e) {
	 e.preventDefault();
	 
	 var commentForm = e.currentTarget.form;
	 var url = "/board/answer/save";
	 var params = "&boardId=" + commentForm[0].value + "&articleId=" + commentForm[1].value + "&shopUrl=" + commentForm[2].value +
	 "&userId=" + commentForm[3].value + "&content=" + commentForm[4].value;
	 //var params = "questionId=" + commentForm[0].value + "&writer=" + commentForm[1].value + "&contents=" + commentForm[2].value;

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
