function Setting() {
	this.b = "board";
	this.c = "category";
	this.boardNum = 0;
	this.categoryNum = 0;
};

Setting.prototype.init = function() {
	this.elBoards = jado.util.getElement('#boardAfter');
	this.elCategorys = jado.util.getElement('#categoryAfter');
	console.log(this.elBoards);
	console.log(this.elCategorys);
};

Setting.prototype.addListItem = function(bORc, elementList, index) {
	elementList.innerHTML = elementList.innerHTML 
	+ "<li name="+index+">"
		+ "<input type='text' name='" + bORc + "' placeholder='제목을 입력해 주세요'>"
		+ "<button type='button' onclick='removeList(" + index + ")'>삭제</button>"
	+ "</li>";
	index++;
};

Setting.prototype.removeListItem = function(elementList, index) {
	var element= elementList.querySelector('li[name="'+ index +'"]');
    elementList.removeChild(element);
};
