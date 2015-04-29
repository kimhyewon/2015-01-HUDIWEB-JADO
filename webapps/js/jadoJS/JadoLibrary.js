var jado = new JADO();

function JADO() {
	this.util = new Util();
	this.formManager = new FormManager();
	this.setting = new Setting();
};

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


// ##### Form Manager #####
// 아직 정확한 기능이 구현되지 않았습니다.
// Form Manager는 encrypt 기능을 포함합니다.

function FormManager() {
	this.encrypt = new Encrypt();
};

FormManager.prototype.showSellerEnroll = function(element) {
	var chkBox = jado.util.getElement(element);
	if (!chkBox) return;
	chkBox.addEventListener("click", function() {
		var target = jado.util.getElement(".formContainer");
		if (chkBox.checked) {
			target.classList.add('scrollSellerEnroll');
		} else {
			target.classList.remove('scrollSellerEnroll');
		}
	})
};

//경륜아, 이건 목적이 뭐인지 얘기해주게 ㅋㅋㅋ
FormManager.prototype.whatIsThis = function(element) {
	var elForm = jado.util.getElement(element);
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
            this[prop] = jado.util.getElement('input[name="' + elements[prop] + '"]');
        }
    }
};

// ##### Encrypt #####
function Encrypt() {
	this.elements = {
		elId: 'userId',
		elPw1: 'password',
		elPw2: 'checkPassword',
		elIdEncryption: 'idEncryption',
		elPwEncryption: 'pwEncryption',
		elSubmit: 'submit',
		rsaPublicKeyModulus: 'rsaPublicKeyModulus',
		rsaPublicKeyExponent: 'rsaPublicKeyExponent'
	};
};

Encrypt.prototype.init = function() {
	this.elForm = jado.util.getElement(".encrypt_form");
	if (!this.elForm) return;
	for (var prop in this.elements) {
		if (this.elements.hasOwnProperty(prop)) {
		    this[prop] = this.elForm.querySelector('input[name="' + this.elements[prop] + '"]');
		}
	}
};

Encrypt.prototype.removeInput = function() {
	if (this.elPw1) {
		this.elPw1.value = "";
	}
	if (this.elPw2) {
		this.elPw2.value = "";
	}
	if (this.elId) {
		this.elId.value = "";
	}
};

Encrypt.prototype.putSecuredRsa = function() {
	var rsaPublicKeyModulus = this.rsaPublicKeyModulus.value;
	var rsaPublicKeyExponent = this.rsaPublicKeyExponent.value;

	var rsa = new RSAKey();
	rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);

	console.log(this.elId.value);
	this.elIdEncryption.value = rsa.encrypt(this.elId.value);
	this.elPwEncryption.value = rsa.encrypt(this.elPwEncryption.value);
};

Encrypt.prototype.putSecuredPassword = function() {
	var securedPassword = sha256_digest(this.elPw1.value);
	this.elPwEncryption.value = securedPassword;
};



function Setting() {
	this.b = "board";
	this.c = "category";
	this.boardNum = 0;
	this.categoryNum = 0;
};

Setting.prototype.init = function() {
	this.elBoards = jado.util.getElement('#boardAfter');
	this.elCategorys = jado.util.getElement('#categoryAfter');
	console.log(this.elBoards);
	console.log(this.elCategorys);
};

Setting.prototype.addListItem = function(bORc, elementList, index) {
	elementList.innerHTML = elementList.innerHTML 
	+ "<li name="+index+">"
		+ "<input type='text' name='" + bORc + "' placeholder='제목을 입력해 주세요'>"
		+ "<button type='button' onclick='removeList(" + index + ")'>삭제</button>"
	+ "</li>";
	index++;
};

Setting.prototype.removeListItem = function(elementList, index) {
	var element= elementList.querySelector('li[name="'+ index +'"]');
    elementList.removeChild(element);
};

