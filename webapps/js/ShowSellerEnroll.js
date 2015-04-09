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
/*
var showSeller = (function() {

	
})

	function showSellerEnroll() {
	    var contentSection = document.querySelector(".seller-container");

	    if (contentSection.style.display == "none") {
	        contentSection.style.display = "block";
	        contentSection.style.height = 0;

	        var nTime = setInterval(function() {
	            var _nPre = parseFloat(contentSection.style.height);
	            if (_nPre > 150) clearInterval(nTime);
	            contentSection.style.height = _nPre + 10 + "px";
	        }, 10);
	    } else {
	        contentSection.style.display = "none";
	    }
	}
*/