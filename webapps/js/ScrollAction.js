var ScrollAaction = (function() {

    var ScrollAction = function(elSections) {
        this.elSections = elSections;
        this.viewHeight = 0;
        this.scrollHeight = 0;
        this.lastPage = 0;
        this.recentPage = 0;
    }

    ScrollAction.prototype.getViewportH = function() {
        var docElem = window.document.documentElement;
        var clientHeight = docElem.clientHeight,
            innerHeight = window.innerHeight;
		this.viewHeight = Math.max(clientHeight, innerHeight);
    };

    ScrollAction.prototype.runOnScroll = function() {
        window.addEventListener("scroll", run.bind(this));
    };

    ScrollAction.prototype.removePage = function() {
        if (this.lastPage < 1 || this.lastPage > 3) return;
        this.elSections[this.lastPage-1].classList.remove( 'animation' );
    };

    ScrollAction.prototype.startPage = function() {
        if (this.recentPage < 1 || this.recentPage > 3) return;
        this.elSections[this.recentPage-1].classList.add( 'animation' );
    };

    var run = function() {
        this.recentPage = getPage(window.pageYOffset, this.viewHeight);
        if (this.lastPage !== this.recentPage) {
            this.removePage();
            this.startPage();
            this.lastPage = this.recentPage;
        }
    };

    var getPage = function(posY, view) {
        var temp = (view + posY - 30) / view;
        return parseInt(temp);
    };
    return ScrollAction;
})();

window.addEventListener("load", function() {
    var elSections = document.querySelectorAll('article');
    if (elSections == null || elSections == undefined) return;
    var oScrollAaction = new ScrollAaction(elSections);
    oScrollAaction.getViewportH();
    oScrollAaction.runOnScroll();
})
