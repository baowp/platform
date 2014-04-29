$(function() {
	var but = $("#patButton");
	var link;
	but.click(function() {
		if (but.hasClass("global")) {
			but.removeClass("global");
			if ($.browser.msie) {
				$("#link_pattern").attr("href", "");
			} else {
				link.remove();
			}
		} else {
			but.addClass("global");
			if ($.browser.msie) {
				$("#link_pattern").attr("href",
						"/user/vipsite/tool/pattern/css/whole.css");
			} else {
				if (link) {
					link.appendTo("head");
				} else
					link = $("<link>", {
						type : "text/css",
						rel : "stylesheet",
						href : "/user/vipsite/tool/pattern/css/whole.css"
					}).appendTo("head");
			}
		}
	});
});
