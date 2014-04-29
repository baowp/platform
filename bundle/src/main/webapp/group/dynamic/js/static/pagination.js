
function prevPage() {
	privateToPageBefore();
	privateToPage(pageInfo.meta.currentPage - 1);
}

function nextPage() {
	privateToPageBefore();
	privateToPage(pageInfo.meta.currentPage + 1);
}

function toPage(n) {
	if (typeof n == "number")
		privateToPageBefore();
	else {
		privateToPageBefore(n);
		n = $("#pageNum").val();
		if (!$.trim(n))
			return;
		if (isNaN(n) || n < 1)
			n = 1;
		if (n > pageInfo.meta.pageCount)
			n = pageInfo.meta.pageCount;
	}
	privateToPage(n);
}

function privateToPageBefore(node) {
	pageInfo.cont = $(node || eventNode).parents(".bodyContContent:first");
	var meta = pageInfo.meta = pageInfo.cont.data("meta");
	pageInfo.url = pageInfo.meta.url;
	pageInfo.args = {
		enterpriseId : enterpriseId
	}
	if (meta.parentValue)
		pageInfo.args[meta.parentKey || "categoryId"] = meta.parentValue;
}

function privateToPage(n) {
	var pathname = location.pathname;
	if (pathname.match(/-/)) {
		location = pathname.replace(/(.+-)\d+/, "$1" + n);
	} else {
		location = pathname.replace(/([^.]+)/, "$1-" + n);
	}
}

$(function() {
	$("a[class^=danaiPage]").live("click", function() {
		eventNode = this;
	});
	if (msie6 && $(".danaiPageUp,.danaiPageDown").html()
			&& $(".danaiPageUp,.danaiPageDown").html().match(/e/i))
		$("<style>a.danaiPageUp,a.danaiPageDown{line-height:28px}</style>")
				.appendTo("head");
})

var pageInfo = {};