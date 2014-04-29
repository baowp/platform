/**
 * 
 */
$(function() {
	$("#d").html(function() {
		return $(this).data("v");
	});

	var doc = document.documentElement, body = document.body;
	var container = $(".w952")[0];
	function left() {
		return container.offsetLeft + container.offsetWidth + 5
	}
	var backtop = $("<div>", {
		className : "go-topbtn",
		css : {
			left : left(),
			display : doc.scrollTop || body.scrollTop || "none"
		},
		click : function() {
			doc.scrollTop = body.scrollTop = 0;
			backtop.fadeOut("slow");
		}
	}).appendTo("body");
	onresize = function() {
		backtop.css("left", left());
	}
	onscroll = function() {
		if (doc.scrollTop || body.scrollTop)
			backtop.fadeIn("slow");
		else
			backtop.fadeOut("slow");
	}
})

function bottom() {
	documentElement = document.documentElement
	return  documentElement.scrollTop
			+ documentElement.clientHeight -76
}