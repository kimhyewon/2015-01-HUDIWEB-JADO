var EP = (function() {
    var EncryptPassword = function(elTarget) {
        this.elForm = elTarget;
        this.elPw1 = this.elForm.querySelector("input[name=password]");
        this.elPw2 = this.elForm.querySelector("input[name=checkPassword]");
        this.elPwEncryption = this.elForm.querySelector("input[name=pwEncryption]");
        this.elSubmitList = this.elForm.querySelectorAll("input[type=submit]");
    }

    EncryptPassword.prototype.validate_when_submit = function() {
        for (var i = this.elSubmitList.length - 1; i >= 0; i--) {
            this.elSubmitList[i].addEventListener("click", (function(e) {
                put_secured_password.call(this);
            }).bind(this));
        };
    };

    var put_secured_password = function() {
        console.log(this);
        var securedPassword = sha256_digest(this.elPw1.value);
        this.elPw1.value = "";
        if (this.elPw2 !== undefined && this.elPw2 !== null) {
            this.elPw2.value = "";
        }
        this.elPwEncryption.value = securedPassword;
        console.log(elPwEncryption.value);
    };

    return EncryptPassword;

})();


//service code
(function() {

    var elTarget = document.querySelector(".encrypt_form");
    var oEP = new EP(elTarget);
    oEP.validate_when_submit();


})();