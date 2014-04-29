/**
 * 
 */
if (msie6) {
	console = {
		info : function(n) {
			Id("div").innerHTML += "<br>" + n
		}
	}
}
$.ajax({
	url : "a.html",
	success : function() {
		console.info(1);
	},
	error : function() {
		console.info(2)
	}
})

$.ajax({
	url : "a.html"
}).done(function() {
	console.info(1);
}).fail(function() {
	console.info(2)
}).complete(function() {
	console.info(3)
})

jQuery(function($) {
	getScript("import.js");
	var css = "ajax.css";
	var link = getLink(css)
	setTimeout(function() {
		if (msie6)
			link.attr("href", "")
		link.remove();
	}, 1000)
	setTimeout(function() {
		link.appendTo("head")
		if (msie6)
			link.attr("href", css)
	}, 2000)
})