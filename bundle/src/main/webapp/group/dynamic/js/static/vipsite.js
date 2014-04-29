
$(function() {
	$("span.titleName").css("cursor", "default");

	loadProductsById = function(categoryId) {
		location = "/product-"+categoryId.replace(/^[^1-9]+/,"")+"-1.html";
	}

	loadNewsById = function(categoryId) {
		location = "/news-"+categoryId.replace(/^[^1-9]+/,"")+"-1.html";
	}
})

