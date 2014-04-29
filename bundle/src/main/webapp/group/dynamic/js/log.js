function registerSuccess() {
	var messageSuccess = $(
			"<div>",
			{
				'class' : "msgSendSucc",
				innerHTML : "<div style='padding:0 0 0 20px;'>"
						+ lan['var.regSuccess'] + "</div>"
			}).dialog({
		width : 280,
		modal : true,
		close : function() {
			messageSuccess.remove();
			$("#registerDiv").dialog("close")
			location.reload();
		},
		buttons : {
			确定 : function() {
				messageSuccess.dialog('close');
			}
		}
	})
}
$(function() {
	function checkUser(label) {
		isUserExist = 0;
		$.ajax({
			url : $form.attr("action").replace(/register$/, 'checkUser'),
			dataType : "script",
			data : {
				username : form.elements['username'].value,
				enterpriseId : form.elements['enterpriseId'].value
			},
			success : function() {
				if (isUserExist) {
					label.text(lan['var.userExist']);
					var $element = $("#registerId");
					$element.addClass("error");
					validator.invalid[$element[0].name] = true;
				} else {
					label.text("ok!").addClass("correct");
				}
			}
		})
	}

	var validator = $("form[name=register]").validate({
		errorElement : "div",
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div").next("div"));
		},
		success : function(label) {
			if (label.attr("htmlfor") == "registerId") {
				checkUser(label);
			} else
				label.text("ok!").addClass("correct");
		},
		submitHandler : function() {
			var options = {
				type : "get",
				// resetForm : true,
				dataType : "script",
				data : {
					userDomain : document.domain
				},
				beforeSubmit : function() {
				}
			}
			$form.ajaxSubmit(options);
		}
	});
	var form = validator.currentForm;
	var $form = $(form);
	$form.find(":reset").bind("click", function() {
		$form.find(".tip").empty();
		$form.find(".error").removeClass("error");
	})
})

function loginBack(record) {
	if (record > 0)
		location.reload();
	else
		$("#logintip").show(300);
}
$(function() {
	var form = $("form[name=login]").validate({
		errorElement : "div",
		errorPlacement : function(error, element) {
			// error.appendTo(element.parent("div").next("div"));
		},
		success : function(label) {
			label.text("ok!").addClass("correct");
		},
		submitHandler : function() {
			var options = {
				type : "get",
				dataType : "script",
				data : {
					userDomain : document.domain
				}
			}
			$form.ajaxSubmit(options);
		}
	}).currentForm;
	var $form = $(form);
	$form.find(":reset").bind("click", function() {
		$form.find(".tip").empty();
		$form.find(".error").removeClass("error");
		$("#logintip").hide(300)
	})
})