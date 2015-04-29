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
	var url = this.getElement('input[name=url]');
	if (!url) return;
	url.value = document.location.href;
};