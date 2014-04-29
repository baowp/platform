$(function() {
	$(".thumb li").mouseover(function() {
		$(".thumb li").removeClass("selected");
		var $node = $(this);
		$node.addClass("selected");
		var src = $node.find("img").attr("src");
		$("#booth").attr("src", src.replace(/_\d/, "_3"));
	});
});