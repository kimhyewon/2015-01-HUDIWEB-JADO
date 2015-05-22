//url 창에서의 고유 값(카테고리 id) 가져오기 
var categoryIdFromUrl = null;

var url = location.href;
if (url.indexOf("category") >= 0) {
	categoryIdFromUrl = url.split("/")[6];
};

var elCategory = document.querySelector('#categoryId');
if (elCategory) {
	categoryIdFromUrl = elCategory.value;
};

// jsp에서 고유 값(카테고리 id) 가져오기
var categoryList = document.querySelectorAll("#category .category_list a");

for (var i = 0; i < categoryList.length; i++) {
	var thisCountId = categoryList[i].href.split("/")[6];
	if (thisCountId === categoryIdFromUrl) {
		categoryList[i].classList.add("categoryOn");
	}
}
