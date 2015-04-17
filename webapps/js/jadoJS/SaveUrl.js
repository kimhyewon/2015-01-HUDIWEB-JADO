var SaveUrl = (function() {
    var SaveUrl = function() {
        this.urlList = document.querySelectorAll('input[name=url]');
        if (!this.urlList) return;

        for (var j = 0; j < this.urlList.length; j++) {
            var stInput = this.urlList[j].value;
            if (!stInput) {
                this.urlList[j].value = window.location.pathname;
            };
        }
    }
    return SaveUrl;
})();