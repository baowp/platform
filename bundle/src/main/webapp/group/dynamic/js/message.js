
function messageSuccess(){
	var $form = $("#mainMessageForm");
	$form[0].reset();
	$form.find(":input").blur().removeClass("error");
	$form.find(".tip .error").remove();
	var messageSuccess = $("<div>", {
		'class' : "msgSendSucc",
		innerHTML : "<div style='padding:0 0 0 20px;'>"+lan['var.messageSuccess']+"</div>"
	}).dialog( {
		// title : "",
		 width : 480,
		// show : "blind",
		modal : true,
		close : function() {
			messageSuccess.remove();
		},
		buttons : {
			确定 : function() {
				messageSuccess.remove();
			}
		}
	});
	refreshCode();
}

function messageFailure(){
	$("<span>",{
		'class':'error',
		innerHTML:lan['valiCodeError']
	}).appendTo("#authcodeSpan");
	refreshCode();
}

$(function() {
	$("#mainMessageForm").validate( {
		errorElement : "span",
		errorPlacement : function(error, element) {
			error.appendTo(element.parent().find(".tip"));
		},
		success : function(label) {
			label.remove();
		},
		submitHandler : function(form) {
			var $form=$(form);
			$form.ajaxSubmit( {
				type : "get",
				dataType : "script",
				data : {
					domain : document.domain,
					userDomain : document.domain
				}
			});
			
		}
	});
	$("#valiCode").bind("keyup", function() {
		if ($("#authcodeSpan").empty())
			;
	})
})