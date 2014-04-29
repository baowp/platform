/**
 * 
 */
$(function() {
	if (document.getElementById("registerDiv")) {
		$("#registerLink").bind("click", function() {
			$("#registerDiv").dialog({
				modal : true,
				width : 600,
				title : lan['var.vipRegister'],
				autoOpen : true
			})
		})
	}
	if (document.getElementById("loginDiv")) {
		$("#loginLink").bind("click", function() {
			$("#loginDiv").dialog({
				modal : true,
				width : 300,
				title : lan['var.vipLogin'],
				autoOpen : true
			})
		})
	}

	var $ctrl = $("#topbarCtrl"), $span = $("#topbarCtrl span"), $con = $(".topbar-container");
	var height = 27, padding = 33;
	var h = padding - height, time = 300;
	var fn = {
		show : function() {
			$con.slideDown(time);
			$(".outBg").animate({
				paddingTop : padding
			}, time, function() {
				$ctrl.attr("title", lan['var.hideToolbar']);
				$span.attr("class", "icon_toolbar_up");
				$.cookie("topbarTodo", "hide");
				$("#tbd-style").remove();
			})
		},
		hide : function() {
			$con.slideUp(time);
			$(".outBg").animate({
				paddingTop : h
			}, time, function() {
				$ctrl.attr("title", lan['var.displayToolbar']);
				$span.attr("class", "icon_toolbar_down");
				$.cookie("topbarTodo", "show");
			})
		}
	}
	$ctrl.click(function() {
		fn[$.cookie("topbarTodo") || "hide"]();
	});

});

(function() {
	if ($.cookie("topbarTodo") == "show") {
		document.write("<link rel=stylesheet href=" + p.topbarHiddenCss + ">");
		document.write('<style id="tbd-style" type="text/css">.icon_toolbar_up{background-position: -16px 0;}</style>');
	}
})();
