var FV = (function () {
	var FormVerify = function (elTarget) {
		this.elForm = elTarget;
		this.elId = this.elForm.querySelector("input[name=userId]");
		this.elPw1 = this.elForm.querySelector("input[name=password]");
		this.elPw2 = this.elForm.querySelector("input[name=checkPassword]");
		this.elPwEncryption = this.elForm.querySelector("input[name=pwEncryption]");
		this.elName = this.elForm.querySelector("input[name=name]");
		this.elPhone = this.elForm.querySelector("input[name=phone]");
		this.elAddress = this.elForm.querySelector("input[name=address]");
		this.elSubmit = this.elForm.querySelector("input[type=submit]");
	}

	FormVerify.prototype.validate_when_submit = function() {
		this.elSubmit.addEventListener("click", function (e) {
			put_secured_password();
			elForm.submit();
		}, false);
	};

	function put_secured_password () {
		console.log("lulu");
		var securedPassword = sha256_digest(this.elPw1.value);
	    this.elPw1.value = "";
	    this.elPw2.value = "";
		this.pwEncryption.value = securedPassword;
	};

	return FormVerify;
})();



//service code
(function () {
	var elTarget = document.querySelector("#signup_form");
	var oFV = new FV(elTarget);
	oFV.validate_when_submit();

})();