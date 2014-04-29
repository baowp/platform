<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>东方五金工作平台</title>
    <link href="css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<LINK rel=stylesheet type=text/css href="css/div.css" id="skin">
<LINK rel=stylesheet type=text/css href="css/left.css" id="skin">
<LINK rel=stylesheet type=text/css href="css/footer.css" id="skin">
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
 <link rel="stylesheet" href="css/ie1.css"/>
<link type="text/css" rel="stylesheet" href="css/ie2.css"/>
<![endif]-->
<link type="text/css" rel="stylesheet" href="css/ContextMenu.css"/>
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.easyui.pack.js"></script>
<script type="text/javascript" src="js/window.js"></script>
<script type="text/javascript" src="js/loading.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jquery.contextMenu.js"></script>
<script type="text/javascript" src="/user/enterprise/js/enterprise.js"></script>
<script type="text/javascript">
$(function(){
	$("body").contextMenu({
		menuId : 'contextMenu',
		onContextMenuItemSelected : function(menuItemId, $triggerElement) {
			if(menuItemId=='index'){
				location.href="/";
			}else if(menuItemId=='about'){
				alert("东方五金技术服务中心")
			}
		},
		onContextMenuShow : function($triggerElement) {
			//alert('trigger1'+$triggerElement.attr('id'))
		},
		showShadow : false
	});
})
</script>
</head>
<body style="height:100%">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
<ul id="contextMenu" class="contextMenu">
				<li id="index" class="delete">
					<a href="/" target="_blank">首页</a>
				</li>
				<li id="about" class="edit">
					<a href="javascript:alert('东方五金技术部')">关于我们</a>
				</li>
</ul>
<s:include value="include/header.jsp"></s:include>
<s:include value="include/center.jsp"></s:include>
<s:include value="include/footer.jsp"/>
<s:if test="#session.abbccuser.grade==00">
	<style>
A.isVip1 {
	color: #808080;
}

A.isVip2 {
	color: #808080;
}
</style>
	<script type="text/javascript">
	jQuery('.isVip').attr('href', "<s:url value='/user/upgrade/show'/>");
	jQuery('.isVip5').attr('href', "<s:url value='/user/upgrade/show'/>");
	</script>
</s:if>
<s:else>
	<script type="text/javascript">
	jQuery("#upgradeMember").hide();
</script>
</s:else>
<s:if test="#session.memberState==03">
	<style>
A.isVip2 {
	color: #808080;
}
</style>
	<script type="text/javascript">
	jQuery('.isVip5').attr('href', "<s:url value='/user/upgrade/show'/>");
	</script>
</s:if>
<s:else>
	<script type="text/javascript">
	jQuery("#upgradeMember").hide();
</script>
</s:else>
<script language="JavaScript">
function logout(){
	if (confirm("您确定要退出后台管理吗"))
	top.location = "/user/userLogout";
}
jQuery(function(){
	jQuery(".tabs-inner").find("span").each(function(i){
		if($(this).html()==''){
			$(this).parent().parent().remove()
		}
	})
})
</script>
<script type="text/javascript" src="/js/unIframe.js"></script>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>