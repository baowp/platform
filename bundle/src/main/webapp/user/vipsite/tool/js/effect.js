/**
 * 
 */
function Effect() {
};
Effect.prototype = {}

var effect = new Effect();

$(function() {
	$(".other-settings").hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	})
	$(".list-other-settings div").hover(function() {
		$(this).addClass("hover");
	}, function() {
		$(this).removeClass("hover");
	})
})