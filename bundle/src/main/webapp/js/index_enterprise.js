$(document).ready(function() {
	var cache = {};
	$("#entName").autocomplete( {
		minLength : 1,
		delay : 10,
		source : function(request, response) {
			if (request.term in cache) {
				response(cache[request.term]);
				return;
			}

			$.ajax( {
				url : "/admin/byNameSearchEnterprise",
				dataType : "json",
				data : request,
				success : function(data) {
					cache[request.term] = data;
					response(data);
				}
			});
		}
	});
});
$(function() {
	if ($("#entName").val() == '') {
		$("#entName").val("请输入公司名称");
		inputTipText(); // 直接调用就OK了
	}
})
