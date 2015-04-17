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
                elAddress: 'address',
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