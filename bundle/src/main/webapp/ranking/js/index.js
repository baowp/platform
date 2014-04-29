$(function() {
	
	$(".gstop2").hide();
	$(".gstop3").hide();
	$("div[id^='gstop']").mouseover(
			function() {
				$(this).parent().find(".lt").attr("class", "nt");
				$(this).attr("class", "lt");
				$("." + $(this).attr("id")).parent().find(
						"div[class^='gstop']:visible").hide();
				$("." + $(this).attr("id")).show();
			})
	$(".cptop2").hide();
	$(".cptop3").hide();
	$("div[id^='cptop']").mouseover(
			function() {
				$(this).parent().find(".lt").attr("class", "nt");
				$(this).attr("class", "lt");
				$("." + $(this).attr("id")).parent().find(
						"div[class^='cptop']:visible").hide();
				$("." + $(this).attr("id")).show();
			})
})
