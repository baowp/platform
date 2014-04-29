function Visit() {
	jsonSign.visit = jsonSign.visit || {};		// 添加互访链接 json节点
}
Visit.prototype = {
	clear : function() {
		$("#visitName").val('');
		$("#visitHref").val('');
		delete jsonSign.visit;
	},

	ok : function() {
		var name = $("#visitName").val().trim();
		var href = $("#visitHref").val().trim();
		if (!name)
			return;
		if (!href.match(/^http/))
			href = "http://" + href;
		jsonSign.visit = jsonSign.visit || {};
		jsonSign.visit.name = name;
		jsonSign.visit.href = href;
	}
}
var visit = new Visit();
$(function() {
	$(".visitName").blur(function() {
		visit.ok();
	});
})