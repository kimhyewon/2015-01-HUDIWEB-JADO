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
	
	var joinButton = document.getElementById('joinButton');
	var editButton = document.getElementById('editButton');
	if (!joinButton && !editButton) return;
	
	chkBox.addEventListener("click", function() {
		var target = jado.util.getElement(".formContainer");
		if (chkBox.checked) {
			if (joinButton)
				joinButton.style.marginLeft = "39%";
			if (editButton)
				editButton.style.marginLeft = "39%";
			target.classList.add('scrollSellerEnroll');
		} else {
			target.classList.remove('scrollSellerEnroll');
			if (joinButton)
				joinButton.style.marginLeft = "22%";
			if (editButton)
				editButton.style.marginLeft = "22%";
		}
	})
};