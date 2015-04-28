window.addEventListener("load", function() {
    jado.util.saveUrl();
    jado.formManager.showSellerEnroll("#chkBox");
    
    //Encryption Code
    if (jado.util.getElement(".encrypt_form")) {
		jado.formManager.encrypt.init();
		jado.formManager.encrypt.elSubmit.addEventListener("click", (function(e) {
			jado.formManager.encrypt.putSecuredPassword();
			jado.formManager.encrypt.putSecuredRsa();
			jado.formManager.encrypt.removeInput();
		}));
    }
})