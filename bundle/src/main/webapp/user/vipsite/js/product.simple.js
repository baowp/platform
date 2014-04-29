$(function() {
	$(".thumb li").mouseover(function() {
		$(".thumb li").removeClass("selected");
		var $node = $(this);
		$node.addClass("selected");
		var src = $node.find("img").attr("src");
		$("#booth").attr("src", src.replace(/_\d/, "_3"));
	});
});

$(function() {
	$.ajax({
		url : "http://51archetype.com/product/addNumber",
		data : {
			id : productId
		},
		dataType : "jsonp",
		success : function(msg) {
			$("#viewsum").html(msg)
		}
	});

	$("#product_close").click(function() {
		close();
	})
})
$(function() {
	$("#collectByPro").click(function() {
		if ($.cookie('userId') == '')
			alert('请先登陆!')
		$.ajax({
			url : "h51archetype.comcc.net/favour/saveCollectByPro",
			data : {
				userId : $.cookie('userId'),
				productId : productId
			},
			dataType : "jsonp",
			success : function(result) {
				if (result == '0') {
					alert('请先登陆!');
					$(".loginLink").click();
				} else if (result == '5')
					alert('二级会员无此权限')
				else if (result == '2')
					alert('您已经收藏过该产品!')
				else {
					alert('收藏成功!')
				}
			}
		});
	})
})