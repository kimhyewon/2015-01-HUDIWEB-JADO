// form_verify.js에 조금 더 자세한 코멘트를 해두었습니다.
// 겹치는 부분은 따로 적지 않을테니 해당 파일을 확인하세요.
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
		// 폼의 submit 이벤트에 추가했으면 굳이 반복문을 사용하지 않아도 됩니다.
        for (var i = this.elSubmitList.length - 1; i >= 0; i--) {
            this.elSubmitList[i].addEventListener("click", (function(e) {
				// 아래의 세 함수는 아무리 봐도 method인데 왜 굳이 따로 빼둔 건가요?
                put_secured_password.call(this);
                put_secured_rsa.call(this);
                remove_input.call(this);
            }).bind(this));
        };
    };

	// 일부러 var remove_input = function() ... 형태를 취하는 이유가 있나요?
	// 그냥 function remove_input() 으로 해도 될테고, 호이스팅 특성때문에 그 편이 여러 모로 더 안전합니다.
    var remove_input = function() {
		// 어차피 this.elPw1에는 querySelector에서 반환된 값이 저장되므로
		// this.elPw1의 값은 엘리먼트 객체 아니면 null입니다. 다른 falsy value와 헷갈릴만한 여지가 없으므로
		// if (this.elPw1) 정도로만 사용해도 충분합니다.
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
		// 굳이 폼 서브밋 이벤트를 명시적으로 실행해주는 이유가 있나요?
        this.elForm.submit();
    }

    var put_secured_rsa = function() {
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
	// == 두 개로 비교하면 null == undefined 입니다. 정확히 하려면 ===를 사용하세요.
    if (elTarget == null || elTarget == undefined) return;

    var oEP = new EP(elTarget);
    oEP.validate_when_submit();
})();
