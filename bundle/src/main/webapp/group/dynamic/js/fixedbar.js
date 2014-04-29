$(function() {
	var bodyPaddingTop = $("body").css("padding-top");
	var time = 300;
	$("#pTbCtrl").toggle(function() {
		$("#divToolbarBody").hide(time);
		$("body").animate( {
			paddingTop : 0
		}, time, function() {
			$("#pTbCtrl").attr("title", lan['var.displayToolbar']);
			$("#imgTbCCtrl").attr("title", lan['var.displayToolbar']);
			$("#imgTbCCtrl").attr("class", "icon_toolbar_down");
		})
	}, function() {
		$("#divToolbarBody").show(time);
		$("body").animate( {
			paddingTop : bodyPaddingTop
		}, time, function() {
			$("#pTbCtrl").attr("title", lan['var.hideToolbar']);
			$("#imgTbCCtrl").attr("title", lan['var.hideToolbar']);
			$("#imgTbCCtrl").attr("class", "icon_toolbar_up");
		})
	})
});

$(function() {
	if (document.getElementById("registerDiv")) {
		$("#registerLink").bind("click", function() {
			$("#registerDiv").dialog( {
				modal : true,
				width : 600,
				title : lan['var.vipRegister'],
				autoOpen : true
			})
		})
	}
	if (document.getElementById("loginDiv")) {
		$("#loginLink").bind("click", function() {
			$("#loginDiv").dialog( {
				modal : true,
				width : 300,
				title : lan['var.vipLogin'],
				autoOpen : true
			})
		})
	}
});
