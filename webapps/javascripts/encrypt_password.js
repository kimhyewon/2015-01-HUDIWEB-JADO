var EP = (function() {
    var EncryptPassword = function(elTarget) {
        this.elForm = elTarget;
        this.elId = this.elForm.querySelector("input[name=userId]");
        this.elPw1 = this.elForm.querySelector("input[name=password]");
        this.elPw2 = this.elForm.querySelector("input[name=checkPassword]");
        this.elIdEncryption = this.elForm.querySelector("input[name=idEncryption]");
        this.elPwEncryption = this.elForm.querySelector("input[name=pwEncryption]");
        this.elSubmitList = this.elForm.querySelectorAll("input[type=submit]");

    }

    EncryptPassword.prototype.validate_when_submit = function() {
        for (var i = this.elSubmitList.length - 1; i >= 0; i--) {
            this.elSubmitList[i].addEventListener("click", (function(e) {
                put_secured_password.call(this);
                put_secured_rsa.call(this);
                remove_input.call(this);
            }).bind(this));
        };
    };

    var remove_input = function () {
        if (this.elPw1 !== undefined && this.elPw1 !== null) {
            this.elPw1.value = "";
        }
        if (this.elPw2 !== undefined && this.elPw2 !== null) {
            this.elPw2.value = "";
        }
        if (this.elId !== undefined && this.elId !== null) {
            this.elId.value = "";
        }
        console.log(this.elForm)
        this.elForm.submit();
    }

    var put_secured_rsa = function () {
        var rsaPublicKeyModulus = this.elForm.querySelector("input[name=rsaPublicKeyModulus]").value;
        var rsaPublicKeyExponent = this.elForm.querySelector("input[name=rsaPublicKeyExponent]").value;

        var rsa = new RSAKey();
        rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);

        console.log(this.elId.value);
        this.elIdEncryption.value = rsa.encrypt(this.elId.value);
        this.elPwEncryption.value = rsa.encrypt(this.elPwEncryption.value);
    }

    var put_secured_password = function() {
        var securedPassword = sha256_digest(this.elPw1.value);
        this.elPwEncryption.value = securedPassword;
    };

    return EncryptPassword;

})();


//service code
(function() {

    var elTarget = document.querySelector(".encrypt_form");
    var oEP = new EP(elTarget);
    oEP.validate_when_submit();


})();