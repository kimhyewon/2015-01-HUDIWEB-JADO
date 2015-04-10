var EncryptPassword = (function() {
    var EncryptPassword = function(elTarget) {
        this.elForm = document.querySelector(elTarget);
        if (!this.elForm) return;
        var elements = {
            elId: 'userId',
            elPw1: 'password',
            elPw2: 'checkPassword',
            elIdEncryption: 'idEncryption',
            elPwEncryption: 'pwEncryption',
            elSubmit: 'submit',
            rsaPublicKeyModulus: 'rsaPublicKeyModulus',
            rsaPublicKeyExponent: 'rsaPublicKeyExponent'
        };

        for (var prop in elements) {
            if (elements.hasOwnProperty(prop)) {
                this[prop] = this.elForm.querySelector('input[name="' + elements[prop] + '"]');
            }
        }
    }

    EncryptPassword.prototype.validateWhenSubmit = function() {
        if (!this.elForm) return;
        this.elSubmit.addEventListener("click", (function(e) {
            // 아래의 세 함수는 아무리 봐도 method인데 왜 굳이 따로 빼둔 건가요?
            putSecuredPassword.call(this);
            putSecuredRsa.call(this);
            removeInput.call(this);
        }).bind(this));
    };

    function removeInput() {
        if (this.elPw1) {
            this.elPw1.value = "";
        }
        if (this.elPw2) {
            this.elPw2.value = "";
        }
        if (this.elId) {
            this.elId.value = "";
        }
    }

    function putSecuredRsa() {
        var rsaPublicKeyModulus = this.rsaPublicKeyModulus.value;
        var rsaPublicKeyExponent = this.rsaPublicKeyExponent.value;

        var rsa = new RSAKey();
        rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);

        console.log(this.elId.value);
        this.elIdEncryption.value = rsa.encrypt(this.elId.value);
        this.elPwEncryption.value = rsa.encrypt(this.elPwEncryption.value);
    }

    function putSecuredPassword() {
        var securedPassword = sha256_digest(this.elPw1.value);
        this.elPwEncryption.value = securedPassword;
    };

    return EncryptPassword;

})();

//service code
(function() {
    var oEncryptPassword = new EncryptPassword(".encrypt_form");
    oEncryptPassword.validateWhenSubmit();
})();