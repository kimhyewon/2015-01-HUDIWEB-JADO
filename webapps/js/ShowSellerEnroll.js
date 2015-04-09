window.addEventListener("load", function() {
    var chkBox = document.querySelector("#chkBox");
    if (!chkBox) return;
    console.log(chkBox);
    chkBox.addEventListener("click", function() {
        var userForm = document.querySelector("#signUpUser");
        if (chkBox.checked) {
            userForm.style.borderTopRightRadius = "0px";
            userForm.style.borderBottomRightRadius = "0px";
            document.querySelector("#signUpSeller").style.visibility = "visible";
        } else {
            userForm.style.borderTopRightRadius = "12px";
            userForm.style.borderBottomRightRadius = "12px";
            document.querySelector("#signUpSeller").style.visibility = "hidden";
        }
    })
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