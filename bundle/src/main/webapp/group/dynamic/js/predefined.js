function Predefined() {
}
Predefined.prototype = {
	compatibleCss : "/group/dynamic/css/compatible.css",
	qqmsie6 : "/group/dynamic/js/qq.msie6.js",
	topbarHiddenCss : "/group/dynamic/css/topbar.hidden.css",
	suffix : "",
	href : function(page) {
		return page;
	}
}
var p = new Predefined();