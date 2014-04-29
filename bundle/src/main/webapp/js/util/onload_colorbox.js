/**
 * 
 */
document
		.write('<link rel="stylesheet" type="text/css" href="/js/jquery/colorbox/colorbox.css" media="screen" />');

$.getScript("/js/jquery/colorbox/jquery.colorbox-min.js").success(function() {
	setTimeout(function() {
		$("#various3,.various3").colorbox({
			width : "80%",
			height : "80%",
			iframe : true
		})
	}, 100)
});