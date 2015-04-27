function JADO() {
	this.util = new Util();
};

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
	url = this.getElement('input[name=url]');
	url.value = document.location.href;
}

