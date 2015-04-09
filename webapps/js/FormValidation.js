var FormValidation = (function() {
    var FormValidation = function(elTarget) {
        this.elForm = document.querySelector(elTarget);
        if (!this.elForm) return;
        var elements = {
            elId: 'userId',
            elPw1: 'password',
            elPw2: 'checkPassword',
            elName: 'name',
            elPhone: 'phone',
            elAddress:'address',
            elSubmit: 'submit'
        };

        for (var prop in elements) {
            if (elements.hasOwnProperty(prop)) {
                this[prop] = this.elForm.querySelector('input[name="' + elements[prop] + '"]');
            }
        }

    }
        // 이름대로 submit을 할 때 validate를 할 것이라면
        // 폼의 submit 이벤트에 핸들러를 추가하는 것이 더 의미에 맞을 것입니다.

    return FormValidation;
})();

//service code
// 컴포넌트 혹은 라이브러리와 실사용 코드를 묶는 것은 좋지 않은 방법입니다.
// 재사용을 하지 않을 것이라면 굳이 클래스나 컴포넌트 형태를 취할 이유가 없을 것입니다.
// 서비스 코드는 별도의 파일로 분리해서 하나로 관리하세요.
(function() {
    var oFormValidation = new FormValidation("#signup_form");
})();