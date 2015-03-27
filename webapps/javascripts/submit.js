function SaveUrl (url_list) {
	this.url_list = url_list;
}

SaveUrl.prototype.save = function() {
	for (var j = 0; j < this.url_list.length; j++) {
		if (this.url_list[j].value==="") {
	    	this.url_list[j].value = window.location.pathname;
		};
	}	
};

window.addEventListener("load", function () {
	var url_list= document.querySelectorAll('input[name=url]');

	var oSU = new SaveUrl(url_list);
	oSU.save();
	
})