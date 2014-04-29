function Visit() {
	jsonContent.visit = jsonContent.visit || {};
}
Visit.prototype = {
	clear : function() {
		$("#visitName").val('');
		$("#visitHref").val('');
		delete jsonContent.visit;
	},

	ok : function() {
		var name = $("#visitName").val().trim();
		var href = $("#visitHref").val().trim();
		if (!name)
			return;
		if (!href.match(/^http/))
			href = "http://" + href;
		jsonContent.visit = jsonContent.visit || {};
		jsonContent.visit.name = name;
		jsonContent.visit.href = href;
	}
}
var visit = new Visit();
$(function() {
	$(".visitName").blur(function() {
		visit.ok();
	});
})