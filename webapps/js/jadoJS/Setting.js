var Setting = (function() {

    var Setting = function(elTarget) {
        this.elTarget = document.querySelector(elTarget);
        if (!this.elTarget) return;
        this.elBoard = this.elTarget.querySelector('#boards');
        this.elCategory = this.elTarget.querySelector('#categories');
    }
    return Setting;
})();

window.addEventListener("load", function() {
    var oSetting = new Setting('#setting');
    if (oSetting.elTarget) {}

    var eRadios = document.querySelectorAll(".previewContainer input[type='radio']");
    console.log(eRadios.length);
});



// 이거 코드 중복 제거하고 oop로 만들어야 함
var boardNum = 0;
var elBoards = document.querySelector('#boardAfter');

function boardNew() {
    elBoards.innerHTML = elBoards.innerHTML + "<li name=" + boardNum + ">" + "<input type='text' name='board' placeholder='제목을 입력해 주세요'>" + "<button type='button' onclick='boardDelete(" + boardNum + ")'>삭제</button>" + "</li>";
    boardNum++;
}

function boardDelete(index) {
    var elBoard = elBoards.querySelector('li[name="' + index + '"]');
    elBoards.removeChild(elBoard);
}

var categoryNum = 0;
var elCategorys = document.querySelector('#categoryAfter');

function categoryNew() {
    elCategorys.innerHTML = elCategorys.innerHTML + "<li name=" + categoryNum + "><input type='text' name='category' placeholder='제목을 입력해 주세요'>" + "<button type='button' onclick='categoryDelete(" + categoryNum + ")'>삭제</button></li>";
    categoryNum++;
}

function categoryDelete(index) {
    var elCategory = elCategorys.querySelector('li[name="' + index + '"]');
    elCategorys.removeChild(elCategory);
}