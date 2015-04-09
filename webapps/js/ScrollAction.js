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
		// 변수 이름을 clientHeight 또는 clientH 처럼 조금 더 명시적으로 만드는 편이 좋을 것 같습니다.
		// 점문법이 가능한데 굳이 각괄호 문법을 사용할 필요도 없을 것 같고요.
        var client = docElem['clientHeight'],
            inner = window['innerHeight'];
		// this.viewHeight = Math.max(client, inner); 정도로 만들면 더 단순해 보입니다.
        if (client < inner)
            this.viewHeight = inner;
        else
            this.viewHeight = client;
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
