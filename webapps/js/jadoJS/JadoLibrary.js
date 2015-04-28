function JADO() {
	this.util = new Util();
	this.formManager = new FormManager();

};

// ##### Util #####
// 프로젝트에서 쓰이는 Util 함수들입니다.
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
}

// ##### Form Manager #####
// 아직 정확한 기능이 구현되지 않았습니다.
// Form validate 역할을 합니다.
function FormManager() {};

FormManager.prototype.showSellerEnroll = function(element) {
	var chkBox = document.querySelector(element);
	if (!chkBox) return;
	chkBox.addEventListener("click", function() {
		var target = document.querySelector(".formContainer");
		if (chkBox.checked) {
			target.classList.add('scrollSellerEnroll');
		} else {
			target.classList.remove('scrollSellerEnroll');
		}
	})
}

FormManager.prototype.whatIsThis = function(element) {
	var elForm = document.querySelector(element);
    if (!elForm) return;
    var elements = {
        elId: 'userId',
        elPw1: 'password',
        elPw2: 'checkPassword',
        elName: 'name',
        elPhone: 'phone',
        elAddress: 'address',
        elSubmit: 'submit'
    };

    for (var prop in elements) {
        if (elements.hasOwnProperty(prop)) {
            this[prop] = document.querySelector('input[name="' + elements[prop] + '"]');
        }
    }
}

