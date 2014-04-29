/**
 * 
 */

function showMessageBox() {
	var $box = $("#ecustomerboxFF");
	if (!$box.data("minwidth"))
		$box.data("minwidth", $box.width());
	if ($box.width() < 222) {
		$box.width($box.width() + 12);
		setTimeout(showMessageBox, 10);
	} else {
		$("#ecustomerbuttonFF").css("background-position", "-226px -215px");
		$("#ecustomerbuttonFF").one("click", hideMessageBox)
	}
}

function hideMessageBox() {
	var $box = $("#ecustomerboxFF");
	if ($box.width() > $box.data("minwidth")) {
		$box.width($box.width() - 12);
		setTimeout(hideMessageBox, 10);
	} else {
		$("#ecustomerbuttonFF").css("background-position", "-168px -215px");
		$("#ecustomerbuttonFF").one("click", showMessageBox)
	}
}

function leaveSuccess() {
	var $form = $("#leave");
	$form[0].reset();
	$form.find(":input").blur();
	$form.find("div.error").remove();
	$("#leaveWord").removeData("input").css("color", '').val(
			lan["leave.success"]);
	refreshCode('checkPic');
}

function leaveFailure() {
	$("<div>", {
		'class' : 'error',
		innerHTML : lan["leave.valiCodeError"]
	}).appendTo("#authcodeLi");
	refreshCode('checkPic');
}

$(function() {
	$("#ecustomerbuttonFF").one("click", showMessageBox);

	$("#leaveWord").focus(function() {
		if (!$(this).data("input")) {
			this.value = "";
			$(this).data("input", 1);
			$(this).css("color", "black");
		}
	});

	$("#leave").validate( {
		errorElement : "div",
		errorPlacement : function(error, element) {
			error.appendTo(element.parents("li:first").next("li"));
		},
		success : function(label) {
			label.remove();
		},
		submitHandler : function(form) {
			var $form = $(form);
			$form.ajaxSubmit( {
				type : "get",
				dataType : "script"
			});
		}
	});
});

(function() {
	$.validator.addMethod("leaveWord", function(value, element) {
		return $(element).data("input");
	});
})();
