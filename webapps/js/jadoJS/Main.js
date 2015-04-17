window.addEventListener("load", function() {
    //모든 코드에서 필요 하지만 spring으로 옮길 예정 
    var oSaveUrl = new SaveUrl();
    
    //main.jsp 에서만 필요
    var oScrollAaction = new ScrollAaction('#mainInfo');
    if (oScrollAaction.elTarget){
    	oScrollAaction.getViewportH();
    	oScrollAaction.runOnScroll();
    }

    //회원가입, 회원정보 페이지에서 필요
    var oFormValidation = new FormValidation("#signup_form");

    //회원가입, 회원정보 페이지에서 필요
    var oShowSellerEnroll = new ShowSellerEnroll();
})