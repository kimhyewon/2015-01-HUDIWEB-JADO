var form_temp = document.querySelector('.form_recent input[type=submit]');
form_temp.addEventListener('click', submit_form, false);

function submit_form(e) {
	e.preventDefault();
	var myform = e.currentTarget.form;
	// myform.elements["url"].value = window.location.pathname;
	myform.action = "/user/login";
	myform.submit();
}



