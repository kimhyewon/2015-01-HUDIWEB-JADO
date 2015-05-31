window.addEventListener("load", function() {
    jado.formManager.showSellerEnroll("#chkBox");
    
    //Encryption Code
    if (jado.util.getElement(".encrypt_form")) {
		jado.formManager.encrypt.init(".encrypt_form");
		jado.formManager.encrypt.elSubmit.addEventListener("click", (function(e) {
			jado.formManager.encrypt.putSecuredPassword();
			jado.formManager.encrypt.putSecuredRsa();
			jado.formManager.encrypt.removeInput();
		}));
    }

    //Animation Code
    if (jado.util.getElement('#mainInfo')) {
    	jado.animation.scrollAction.getViewportH();
    	jado.animation.scrollAction.runOnScroll();
    }
})