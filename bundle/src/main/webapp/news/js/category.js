

$(function() {
	$(".jform").each(function() {
		$(this).validate( {
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
					beforeSend : function() {
					},
					success : function(data) {
						if (data == "success")
							info("保存成功")
					}
				});
			}
		});
	})
});

function remove() {
	var row = $(currentEventNode).parents("ol:first");
	var id = row.find(":hidden[name$=Id]:first").val();
	$.ajax( {
		url : "remove",
		data : {
			id : id
		},
		success : function() {
			row.hide(300, function() {
				row.remove()
			});
		}
	})
}
