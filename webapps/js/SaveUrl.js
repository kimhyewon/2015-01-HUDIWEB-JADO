var SaveUrl = (function() {
    var SaveUrl = function() {
        this.urlList = document.querySelectorAll('input[name=url]');
        if (!this.urlList) return;

        for (var j = 0; j < this.urlList.length; j++) {
            var stInput = this.urlList[j].value;
            // 결국 if (!stInput) 과 같은 코드입니다.
            if (stInput == "" || stInput == null || stInput == undefined) {
                this.urlList[j].value = window.location.pathname;
            };
        }
    }
    return SaveUrl;
})();

window.addEventListener("load", function() {
    var oSU = new SaveUrl();
})