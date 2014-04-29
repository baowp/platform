$(function() {
	tabClose();
	tabCloseEven();

	if (!$('#tabs').tabs('exists', "平台向导")) {
		$('#tabs').tabs('add', {
			title : "工作向导",
			content : createFrame("action/main.jsp"),
			closable : false,
			width : $('#mainPanle').width() - 10,
			height : $('#mainPanle').height() - 26
		});
	} else {
		$('#tabs').tabs('select', subtitle);
	}
	$("#searchUrl").val("product/published");
	$("#searchName").val("name");

	$(".lv-2-box a").click(
			function() {
				if ($(this).attr("id") == "isVip"
						|| $(this).attr("id") == 'indexMenu') {

				} else {
					var tabTitle = $(this).text();
					var url = $(this).attr("href");
					addTab(tabTitle, url);
					return false;
				}
				
			})
	/**
	 * 搜索的默认文字
	 */
	$(".search-text").val('请输入关键字')
	$(".search-text") // 所有样式名中含有grayTips的input
	.each(function() {
		var oldVal = '请输入关键字'; // 默认的提示性文本
		$(this).css({
			"color" : "#DCDCDC"
		}) // 灰色
		.focus(function() {
			if ($(this).val() != oldVal) {
				$(this).css({
					"color" : "#000000"
				})
			} else {
				$(this).val("").css({
					"color" : "#DCDCDC"
				})
			}
		}).blur(function() {
			if ($(this).val() == "") {
				$(this).val(oldVal).css({
					"color" : "#DCDCDC"
				})
			}
		}).keydown(function() {
			$(this).css({
				"color" : "#000000"
			})
		})

	})

})
function addTab(subtitle, url) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			width : $('#mainPanle').width() - 10,
			height : $('#mainPanle').height() - 26
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$(".tabs-panels-noborder").find(":visible").attr("src",url);
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children("span").text();
		$('#tabs').tabs('close', subtitle);
	})

	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children("span").text();
		$('#mm').data("currtab", subtitle);

		return false;
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		if(t!='工作向导')
			$('#tabs').tabs('close', currtab_title);
	})
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if(t!='工作向导')
				$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != currtab_title && t!='工作向导')
				$('#tabs').tabs('close', t);
		});
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if(t!='工作向导')
				$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if(t!='工作向导')
				$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	})
}
function openDia(obj,n) {
	if(n==true){
		var tabTitle = $(obj).text();
		var url = $(obj).attr("href");
		addTab(tabTitle, url);
	}
}
