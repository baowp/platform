$(function() {
	$("#categories li a").click(function() {
		var li = $(this).parent();
		$("#categories li.current").removeClass("current");
		li.addClass("current");
		$("div[rel]").hide();
		$("div[rel=" + li.text() + "]").show().css( {
			left : li.position().left + li.width()
		});
	})

	$("div[rel]").css( {
		top : $("#categories").position().top
	})
	// $("#categories li:first").click();
})