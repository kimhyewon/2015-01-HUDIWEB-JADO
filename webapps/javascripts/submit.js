function SaveUrl (url_list) {
	this.url_list = url_list;
}

SaveUrl.prototype.save = function() {
	for (var j = 0; j < this.url_list.length; j++) {
		var stInput = this.url_list[j].value;
		if (stInput == "" || stInput == null || stInput == undefined) {
	    	this.url_list[j].value = window.location.pathname;
		};
	}	
};

window.addEventListener("load", function () {
	var url_list= document.querySelectorAll('input[name=url]');
	console.log("input tag");
	console.log(url_list);

	var oSU = new SaveUrl(url_list);
	oSU.save();
	
})