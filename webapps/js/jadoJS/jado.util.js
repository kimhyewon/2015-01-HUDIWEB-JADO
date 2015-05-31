// ##### Util #####
function Util() {};

Util.prototype.getElement = function(element) {
	if (!element) return;
	return document.querySelector(element);
};

Util.prototype.getElementAll = function(element) {
	if (!element) return;
	return document.querySelectorAll(element);
};

Util.prototype.saveUrl = function() {
	var url = location.href;
	var shopRegexp = /\/shop\//g;
	if (shopRegexp.test(url)) {
		var shopAddress = (url.split("\/shop\/"))[1];
		console.log(shopAddress);
	}
};