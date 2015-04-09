// FV는 이름만 봐서는 기능을 유추하기 힘든 이름이라 조금 더 명시적으로 바꾸는 편이 좋겠습니다.
var FV = (function() {
	// 클래스 이름은 보통 명사형 작성합니다.
	// FormVerify 보다는 Validation 혹은 FormValidation이 더 적당해보입니다.
    var FormVerify = function(elTarget) {
        this.elForm = elTarget;
		// 아래와 같이 반복적으로 거의 동일한 코드를 사용해서 엘리먼트를 가져올 것이라면
		// 이 역시 자바스크립트의 유연함을 활용해 조금 더 깔끔하게 만들 수 있습니다.
		// var elements = {elId: 'userId', elPw1: 'password' ...  };
		// for (var prop in elements) {
		//   if (elements.hasOwnProperty(prop)) {
		//     this[prop] = this.elForm.querySelector('input[name="'+elements[prop]+'"]');
		//   }
		// }
		// 이 방식의 장점은 나중에 가져와야 할 폼이 늘어나도 쉽게 추가할 수 있다는 점입니다.
		// 가져오는 코드를 변경해야 할 때도 쉽게 변경할 수 있다는 점 또한 장점입니다.
        this.elId = this.elForm.querySelector("input[name=userId]");
        this.elPw1 = this.elForm.querySelector("input[name=password]");
        this.elPw2 = this.elForm.querySelector("input[name=checkPassword]");
        this.elName = this.elForm.querySelector("input[name=name]");
        this.elPhone = this.elForm.querySelector("input[name=phone]");
        this.elAddress = this.elForm.querySelector("input[name=address]");
        this.elSubmit = this.elForm.querySelector("input[type=submit]");
    }

	// 자바스크립트의 메소드 이름은 대체로 카멜 케이스를 따르고 있습니다.
    FormVerify.prototype.validate_when_submit = function() {
		// 이름대로 submit을 할 때 validate를 할 것이라면
		// 폼의 submit 이벤트에 핸들러를 추가하는 것이 더 의미에 맞을 것입니다.
        this.elSubmit.addEventListener("click", function(e) {

        }, false);
    };

    return FormVerify;
})();

//service code
// 컴포넌트 혹은 라이브러리와 실사용 코드를 묶는 것은 좋지 않은 방법입니다.
// 재사용을 하지 않을 것이라면 굳이 클래스나 컴포넌트 형태를 취할 이유가 없을 것입니다.
// 서비스 코드는 별도의 파일로 분리해서 하나로 관리하세요.
(function() {
	// 여기서부터
    var elTarget = document.querySelector("#signup_form");
    if (elTarget == null || elTarget == undefined) return;
	// 여기까지는 사실 폼을 읽어들일 때마다 해야하는 동작으로 보입니다.
	// 그렇다면 차라리 FV  생성자 안에 포함시키는 편이 나을 것 같습니다.
	// var oFV = new FV('#signup_form'); 과 같이 사용하도록 만드는 편이 더 나았을 것 같네요.
    var oFV = new FV(elTarget);
    oFV.validate_when_submit();

})();
