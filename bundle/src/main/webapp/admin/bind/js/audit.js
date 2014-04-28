function approve() {
	if (!confirm("是否确定审核通过"))
		return;
	$.ajax({
		url : 'approve',
		cache : false,
		data : {
			id : bindId
		},
		success : function() {
			location.reload();
		}
	})
}

function deny() {
	var foo = $("<div style='display:none'>");
	foo.load("/admin/bind/reason.jsp", function() {
		foo.appendTo("body");

		foo.dialog({
			title : "不通过原因",
			width : 320,
			modal : true,
			close : function() {
				foo.remove();
			},
			buttons : {
				确定 : function() {
					doDeny();
					foo.dialog('close');
				},
				取消 : function() {
					foo.dialog('close');
				}
			}
		});
	});

}

function doDeny() {
	$.ajax({
		url : 'deny',
		cache : false,
		data : {
			id : bindId,
			denyReason : $("#reason").val()
		},
		success : function() {
			location.reload();
		}
	})
}

function detail() {
	location = "view?id=" + bindId;
}

$(function() {
	$(".operate").click(function() {
		var $row = $(this).parents("tr:first");
		var json = $row.attr("class").match(/{.*}/)[0];
		bindId = $.parseJSON(json).id;
	})
});