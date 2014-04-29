function Predefined() {
}
Predefined.prototype = {
	compatibleCss : "css/compatible.css",
	qqmsie6 : "js/site/qq.msie6.js",
	topbarHiddenCss : "css/topbar.hidden.css",
	suffix : ".html",
	href : function(page) {
		if (!page.match(/:/))
			page += p.suffix;
		return page;
	}
}
var p = new Predefined();