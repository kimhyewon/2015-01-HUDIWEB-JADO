var ShowSellerEnroll = (function() {
    var ShowSellerEnroll = function() {
        var chkBox = document.querySelector("#chkBox");
        if (!chkBox) return;
        chkBox.addEventListener("click", function() {
            var target = document.querySelector("#signUpContainer");
            if (chkBox.checked) {
                target.classList.add('scrollSellerEnroll');
            } else {
                target.classList.remove('scrollSellerEnroll');
            }
        })
    }
    return ShowSellerEnroll;
})();

window.addEventListener("load", function() {
    var oShowSellerEnroll = new ShowSellerEnroll();
})