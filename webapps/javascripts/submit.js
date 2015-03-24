var url_list= document.querySelectorAll('input[name=url]');
for (var j = 0; j < url_list.length; j++) {
	console.log(url_list[j].value)
	if (url_list[j].value==="") {
    	url_list[j].value = window.location.pathname;
	};
}