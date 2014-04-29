
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
	pageInfo.args.page = n;
	$.ajax({
		url : pageInfo.url,
		data : pageInfo.args,
		complete : removeBusy,
		beforeSend : function() {
			busyOverlay(pageInfo.cont[0], {
				opacity : 0
			}, {
				color : '#00f',
				size : 192,
				type : 'c',
				iradius : 20,
				weight : 6,
				count : 12,
				speed : 2
			});
		},
		success : function(data) {
			pageInfo.cont.html($(data).find(".bodyContContent").html());
		}
	})
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