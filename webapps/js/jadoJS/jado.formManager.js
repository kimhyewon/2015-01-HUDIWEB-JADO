// ##### Form Manager #####
// Form Manager는 encrypt 기능을 포함합니다.

function FormManager() {
	this.encrypt = new Encrypt();
};

FormManager.prototype.isNull = function(inputElement) {
    var inputEl = jado.util.getElement(inputElement);
    if (!inputEl || inputEl.tagName != "INPUT") return;
    if (inputEl.value.trim().length)
        return false;
    return true;
}

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