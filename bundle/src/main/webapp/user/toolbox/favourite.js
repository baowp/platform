var f_str = '';
var title = "产品详细";
$(function() {
	$(".listTable tr td.names").live(
			"mousemove",
			function(event) {
				if (pageType == 'ent')
					title = "公司详细";
				$("#" + $(this).attr("pDiv")).dialog(
						{
							modal : false,
							width : 480,
							title : title,
							position : [ $(this).position().left + 50,
									$(this).position().top + 35 ], // 设置坐标
							autoOpen : true
						})

			})
	$(".listTable tr td.names").live("mouseout", function() {
		$("#" + $(this).attr("pDiv")).dialog('close');
	})
	$("#contrast").click(
			function() {
				var obj = $("input[name='ch']");
				var n = $("input[name='ch']:checked").length;
				if (n <=1) {
					alert('请选择2个以上的对比信息!')
					return;
				}
				if (n > 10) {
					alert('对比信息不能超过10条!')
					return;
				}
				var img = new Array(n);
				var proName = new Array(n);
				var proHref = new Array(n);
				var proType = new Array(n);
				var price = new Array(n);
				var entName = new Array(n);
				var businessType = new Array(n);
				var staffSum = new Array(n);
				var registeredCapital = new Array(n);
				var regTime = new Array(n);

				$("input[name='ch']:checked").each(
						function(i) {
							var $p = $(this).parent().parent();
							img[i] = $p.find(".names #img").val();
							proName[i] = $p.find(".names #proName").val();
							proHref[i] = $p.find(".names #proHref").val();
							proType[i] = $p.find(".names #proType").val();
							price[i] = $p.find(".names #price").val();
							entName[i] = $p.find(".names #entName").val();
							businessType[i] = $p.find(".names #businessType")
									.val();
							staffSum[i] = $p.find(".names #staffSum").val();
							registeredCapital[i] = $p.find(
									".names #registeredCapital").val();
							regTime[i] = $p.find(".names #regTime").val();
						})
				window.open( "/user/toolbox/info.jsp?img=" + img
						+ "&proName=" + proName
						+ "&proHref=" +proHref
						+ "&proType=" + proType
						+ "&price=" + price
						+ "&entName=" + entName 
						+ "&businessType="+ businessType 
						+ "&staffSum="+ staffSum
						+ "&registeredCapital="+ registeredCapital
						+ "&regTime=" + regTime);
			})
})