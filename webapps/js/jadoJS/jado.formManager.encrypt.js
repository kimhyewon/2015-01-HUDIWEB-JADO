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