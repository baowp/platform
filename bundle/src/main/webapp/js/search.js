function formSubmit(obj) {
	$("#form1").attr(
			"action",
			$("#form1").attr("action").replace($("#form1").attr("action"),
					"byNameSearch?pageType=" + obj));
	$("#pageTypeHidden").val(obj);
	checkValue();
	$("#form1").submit();

};
function checkType(obj) {
	var $form = $("#form1");
	$form.attr("action", $form.attr("action").replace($form.attr("action"),
			"byNameSearch?pageType=" + obj));
	$("#pageTypeHidden").val(obj);
	checkValue();
	$form.submit();
}
function checkValue(){
	if ($("#entName").val() == ''
		|| $("#entName").val() == '请输入供求名称'
		|| $("#entName").val() == '请输入产品名称'
		|| $("#entName").val() == '请输入资讯名称'
		|| $("#entName").val() == '请输入公司名称') {
	$("#entName").val("");
}
}
function logout() {
	if (confirm("您确定要退出后台管理吗"))
		return true;
	else
		return false;
}
function setHomepage() {
	var href = "http://" + location.host;
	if (document.all) {
		document.body.style.behavior = 'url(#default#homepage)';
		document.body.setHomePage(href);

	} else if (window.sidebar) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
				var prefs = Components.classes['@mozilla.org/preferences-service;1']
						.getService(Components.interfaces.nsIPrefBranch);
				prefs.setCharPref('browser.startup.homepage', href);
			} catch (e) {
				alert("\u8be5\u64cd\u4f5c\u88ab\u6d4f\u89c8\u5668\u62d2\u7edd\uff0c\u5982\u679c\u60f3\u542f\u7528\u8be5\u529f\u80fd\uff0c\u8bf7\u5728\u5730\u5740\u680f\u5185\u8f93\u5165 about:config,\u7136\u540e\u5c06\u9879 signed.applets.codebase_principal_support \u503c\u8be5\u4e3atrue");
			}
		}
	}
}
function addPage() {
	var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd'
			: 'CTRL';
	if (document.all) {
		window.external.addFavorite('http://51archetype.com/', 'ABBCC平台');
	} else if (window.sidebar) {
		window.sidebar.addPanel('ABBCC', 'h51archetype.comcc.net/', "");
	} else {
		alert('您可以尝试通过快捷键' + ctrl + ' + D 加入到收藏夹~');
	}
}
function inputTipText() {
	$("#entName") // 所有样式名中含有grayTips的input
	.each(function() {
		var oldVal = $(this).val(); // 默认的提示性文本
		$(this).css( {
			"color" : "#DCDCDC"
		}) // 灰色
		.focus(function() {
			if ($(this).val() != oldVal) {
				$(this).css( {
					"color" : "#000000"
				})
			} else {
				$(this).val("").css( {
					"color" : "#DCDCDC"
				})
			}
		}).blur(function() {
			if ($(this).val() == "") {
				$(this).val(oldVal).css( {
					"color" : "#DCDCDC"
				})
			}
		}).keydown(function() {
			$(this).css( {
				"color" : "#000000"
			})
		})

	})
}
function openInquiry(obj){
	var url = '/inquiry/inquiryPage?productId='+$(obj).attr('pId');
	art.dialog.open(url,{
		id : 'xjj',
		skin: 'aero',
		title: '询价(按esc可关闭)',
		left:100,
		top:0,
		fixed:false,
		width:800,
		height:500
	});
}
function openMap(obj){
	art.dialog({
		id : 'testIframe',
		skin: 'aero',
		title: '公司地图(按esc可关闭)',
		left:150,
		top:40,
		fixed:true,
		width:600,
		height:480,
	    content: '<iframe width="600" height="450" scrolling="no" border="0" frameborder="0" id="mapIframe" src="http://searchbox.mapbar.com/publish/template/template1010/index.jsp?CID=ggggfj&tid=tid1010&nid='+$(this).attr("mapaddress")+'&width=600&height=450&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0"></iframe>'
	});
}