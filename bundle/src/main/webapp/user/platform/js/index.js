$(function() {
	$("#work-msg-box").bind({
		mouseover : function() {
			$(".work-msg").attr("class", "work-msg over")
		},
		mouseout : function() {
			$("#work-msg-box").attr("class", "work-msg")
		}
	})
	$("#yui-gen0").bind({
		mouseover : function() {
			$(this).attr("class", "select-box over")
		},
		mouseout : function() {
			$(this).attr("class", "select-box")
		}
	})
	$("#yuievtautoid-0 li").bind({
		mouseover : function() {
			$(this).attr("class", "over")
		},
		mouseout : function() {
			$(this).attr("class", "")
		},
		click : function() {
			$("#searchName").val($(this).attr("uName"));
			$("#searchUrl").val($(this).attr("id"));
			$(".selected-value").html($(this).html())
			$("#yui-gen0").attr("class", "select-box")
		}
	})
	$(".search-btn").click(function(){
		if($(".search-text").val()==''||$(".search-text").val()=='请输入关键字')
			return false;
		else{
			addTab("平台搜索", "/user/"+$("#searchUrl").val()+"?"+$("#searchName").val()+"="+$(".search-text").val());
		}
			//loadingIframe("/user/"+$("#searchUrl").val()+"?"+$("#searchName").val()+"="+$(".search-text").val())
	})
	$("#work-show-btn").click(function() {
		if ($(this).attr("state") == "0") {
			$(this).attr("state", "1");
			$(this).attr("class", "lnk work-show-on");
			$("#work-myinfo").hide(300);
			$(this).html("展开状态栏");
			$(".layout-panel-center").css({
				top:"78px",
				left: "160px",
				width: "844px"
			});
		} else {
			$(this).attr("state", "0");
			$(this).attr("class", "lnk work-show-off");
			$("#work-myinfo").show(300);
			$(this).html("收起状态栏");
			$(".layout-panel-center").css({
				top:"130px",
				left: "160px",
				width: "844px"
			});
		}
	})
	$("#quickbox").bind({
		mouseover : function() {
			$("#quickmain").show()
			$("#iframebox").show()
			$("#quickbox a").attr("class", "q-link q-lnk-cur")
			$("#leftmenu").attr("class", "wpl-zdx")
		},
		mouseout : function() {
			$("#quickmain").mouseover(function() {
				$("#quickmain").show();
				$("#iframebox").show()
			})
			$("#quickmain").mouseout(function() {
				$("#quickmain").hide();
				$("#iframebox").hide()
				$("#quickbox a").attr("class", "q-link q-lnk")
				$("#leftmenu").attr("class", "")
			})

		}
	})
	$(".work-nav-new li a").click(function() {
		$(".work-nav-new li").find(".current").attr("class", "b")
		$(this).attr("class", "b current");

		$("#leftmenu").children(":visible").animate({
			height : 'hide',
			opacity : 'hide'
		}, '10');
		$("#" + $(this).attr("to")).animate({
			height : 'show',
			opacity : 'show'
		}, 'slow');
		;
		$("#"+$(this).attr("to")).find(".lv-2-cur").attr("class","menu-bg lv-2");
		if ($(this).attr("to") == 'home'){
			addTab('工作向导', 'action/main.jsp');
		}

	})
	$(".lv-2-box a").click(function() {
		$(this).parent().find(".lv-2-cur").attr("class", "menu-bg lv-2")
		$(this).attr("class", "menu-bg lv-2-cur")
	})
	$("#more-account").click(function() {
		$("#more-account-box").css({
			"opacity" : 1,
			"left" : "158px",
			"top" : "-1px"
		}).show(300)
	})
	$("#more-account-close").click(function() {
		$("#more-account-box").hide(300)
	})
})