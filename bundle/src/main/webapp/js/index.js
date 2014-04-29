$(function() {
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
				url : "/admin/byNameSearchProduct",
				dataType : "json",
				data : request,
				success : function(data) {
					cache[request.term] = data;
					response(data);
				}
			});
		}
	});
	// $( "#entName" ).focus()

	// 首页动画效果
	$("ul.nav li a").each(function(index) {
		$(this).hover(function() {
			onBg(index + 1, 0);
		}, function() {
			onBg(index + 1, 1);
		});
	});
});
// -------首页动画效果-----
function navScrollBg(i, index, flag) {
	var a = [ 0, 52, 104, 156, 208 ];
	var spans = $("#info_" + index).find("span");
	if (flag == 0) {
		spans.css("background-position", (0 - a[i]) + "px" + " "
				+ (0 - ((index - 1) * 37)) + "px");
	} else {
		a.sort(function(b, c) {
			return c - b;
		});
		spans.css("background-position", (0 - a[i]) + "px" + " "
				+ (0 - ((index - 1) * 37)) + "px");
	}
}
function onBg(index, flag) {
	for ( var i = 0; i < 5; i++) {
		setTimeout("navScrollBg(" + i + "," + index + "," + flag + ")", 60 * i);
	}
}
// ---------------------------------------------------------
$(function() {
	checkValue();
	if ($("#entName").val() == '') {
		$("#entName").val("请输入产品名称");
		inputTipText(); // 直接调用就OK了
	}
})
