$(function() {
	getMoodsByEnt()
	$("#collect-btn").click(function() {
		if ($.cookie('userId') == '')
			alert('请先登陆!')
		$(this).attr("disabled", true);
		$.ajax({
			url : "http://51archetype.com/favour/saveCollectByEnt",
			data : {
				userId : $.cookie('userId'),
				enterpriseId : $(this).attr("eId")
			},
			dataType : "jsonp",
			success : function(result) {
				if (result == '0') {
					alert('请先登陆!');
					$(".loginLink").click();
				} else if (result == '5')
					alert('二级会员无此权限')
				else if (result == '2')
					alert('您已经收藏过该公司!')
				else {
					alert('收藏成功!')
					getMoodsByEnt();
				}
			}
		});
	})
})
getMoodsByEnt = function() {

	$.ajax({
		url : "h51archetype.comcc.net/favour/moodsByEnt",
		data : {
			enterpriseId : enterpriseId
		},
		dataType : "jsonp",
		success : function(result) {
			$(".moods").html(result)

		}
	});

}
