var formList = document.querySelectorAll('.form_recent input[type=submit]');
for (var j = 0; j < formList.length; j++) {
    formList[j].addEventListener('click', submit, false);
}


function submit() {
	e.preventDefault();
	console.log("1111");
	var questionForm = document.formList;
	questionForm.action = "/user/login";
	questionForm.submit();
}