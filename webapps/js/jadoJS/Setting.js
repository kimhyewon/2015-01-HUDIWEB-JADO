var Setting = (function() {

    var Setting = function(elTarget) {
        this.elTarget = document.querySelector(elTarget);
        if (!this.elTarget) return;
        this.elBoard = this.elTarget.querySelector('#boards');
        this.elCategory = this.elTarget.querySelector('#categories');
    }

    Setting.prototype.boardController = function() {
        
    };

    Setting.prototype.runOnScroll = function() {
    };

    Setting.prototype.removePage = function() {
    };

    Setting.prototype.startPage = function() {
    };
    return Setting;
})();

window.addEventListener("load", function() {
    var oSetting = new Setting('#setting');
    if (oSetting.elTarget){
        oSetting.boardController;
        oSetting.runOnScroll();
    }

});