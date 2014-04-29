$(function() {
	var but = $("#patButton");
	var link;
	but.click(function() {
		if (but.hasClass("global")) {
			but.removeClass("global");
			but.val("缩略模式");
			if ($.browser.msie) {
				$("#link_pattern").attr("href", "none.css");
			} else {
				link.remove();
			}
		} else {
			but.addClass("global");
			but.val("完整模式");
			if ($.browser.msie) {
				$("#link_pattern").attr("href",
						"/group/dynamic/tool/pattern/css/whole.css");
			} else {
				if (link) {
					link.appendTo("head");
				} else
					link = $("<link>", {
						type : "text/css",
						rel : "stylesheet",
						href : "/group/dynamic/tool/pattern/css/whole.css"
					}).appendTo("head");
			}
		}
	});
	if (msie6) {
		$(".toolBarTop .pat").hover(function() {
			$(this).addClass("hover")
		}, function() {
			$(this).removeClass("hover")
		})
	}
});
