function SaveUrl (url_list) {
    this.url_list = url_list;
}

SaveUrl.prototype.save = function() {
    for (var j = 0; j < this.url_list.length; j++) {
        console.log(this.url_list[j]);
        console.log(this.url_list[j].href);
        var p = "?url=" + window.location.pathname;
        this.url_list[j].href += p;
    }
};

window.addEventListener("load", function() {
    var url_list = document.querySelectorAll('a[name=url]');

    var oSU = new SaveUrl(url_list);
    oSU.save();

})