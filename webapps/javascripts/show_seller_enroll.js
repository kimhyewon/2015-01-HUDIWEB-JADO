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