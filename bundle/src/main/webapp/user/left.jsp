<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理页面</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script src="<s:url value="/user/js/prototype.lite.js"/>" type="text/javascript"></script>
<script src="<s:url value="/user/js/moo.fx.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	function goto(src){
		window.top.frames['main'].location = src;
	}
</script>
<script src="<s:url value="/user/js/moo.fx.pack.js"/>" type="text/javascript"></script>
<style>
body {
	font: 12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}

#container {
	width: 182px;
}

H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 27px;
	line-height: 20px;
}

H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 27px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(<s:url value="/user/images/menu_bg.gif"/>);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}

.content {
	width: 182px;
	height: 26px;
}

.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}

.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}

.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px, 0px, 0px, 0px);
}

.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(<s:url value="/user/images/menu_bg1.gif"/>);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(<s:url value="/user/images/menu_bg1.gif"/>);
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}

.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background-image: url(<s:url value="/user/images/menu_bg1.gif"/>);
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}

.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background-image: url(<s:url value="/user/images/menu_bg2.gif"/>);
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0"
	bgcolor="#EEF2FB">
	<tr>
		<td width="182" valign="top">
		<div id="container"><s:if test="#session.abbccuser.type==00">
			<h1 class="type"><a href="javascript:void(0)">公司管理(<font
				color="red">未建</font>)</a></h1>
		</s:if> <s:else>
			<h1 class="type"><a href="javascript:void(0)">公司管理</a></h1>

		</s:else>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
		<s:iterator var="user" value="%{#request.customList}" status="st">
        	<li><a href="<s:url value='%{action}'/>" target="main">${name}</a></li>
        </s:iterator>
		</ul>
		</div>
		<h1 class="type"><a href="javascript:void(0)">产品管理</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/supply/add"/>" target="main">发布供求信息</a></li>
			<li><a href="<s:url value="/user/supply/auditing"/>" target="main">供求信息管理</a></li>
			<li><a href="<s:url value="/user/product/add"/>" target="main">发布新产品</a></li>
			<li><a href="<s:url value="/user/product/auditing"/>" target="main">产品管理</a></li>
			<li><a href="<s:url value="/user/product/brand/list"/>" target="main">品牌管理</a></li>
			<li><a href="<s:url value="/user/product/category/show"/>" target="main">分类管理</a></li>
		</ul>
		</div>

		<h1 class="type"><a href="javascript:void(0)">新闻管理</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/news/newsshow"/>" target="main">新闻管理</a></li>
			<li><a href="<s:url value="/user/news/newslayout"/>" target="main">新闻布局</a></li>
			<li><a href="<s:url value="/user/news/categorysshow"/>" target="main">分类管理</a></li>
		</ul>
		</div>
		<h1 class="type"><a href="javascript:void(0)">订单管理</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/order/list"/>" target="main">查看订单</a></li>
			<li><a href="<s:url value="/user/order/stat"/>" target="main">订单统计</a></li>
		</ul>
		</div>
		<h1 class="type"><a href="javascript:void(0)">客户管理</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/subMember/subMembershow"/>" target="main">查看客户</a></li>
			<li><a href="<s:url value="/user/subMember/statshow"/>" target="main">客户统计</a></li>
		</ul>
		</div>

		<h1 class="type"><a href="javascript:void(0)">商业信息</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/message/messageentry"/>" target="main">站内留言</a></li>
			<li><a href="<s:url value="/user/message/messagesysEntry"/>" target="main">系统留言</a></li>
			<li><a href="<s:url value="/user/message/messagevisitor"/>" target="main">访问者留言</a></li>
			<li><a href="<s:url value="/user/viewlog/viewmain"/>" target="main">我的访问者</a></li>
		</ul>
		</div>

		<h1 class="type"><a href="javascript:void(0)" class="isVip1">我的钱包</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/wallet/paylog/records"/>" id="isVip" target="main" class="isVip1">付费记录</a></li>
			<li><a href="<s:url value="/user/wallet/paylog/applying"/>" id="isVip" target="main" class="isVip1">续费服务</a></li>
			<li><a href="<s:url value="/user/wallet/paylog/cert"/>" id="isVip" target="main" class="isVip1">付费凭证</a></li>
			<li><a href="<s:url value="/user/wallet/paylog/balance"/>" id="isVip" target="main" class="isVip1">余额管理</a></li>
		</ul>
		</div>
		<h1 class="type"><a href="javascript:void(0)" class="isVip2">管理旺铺</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/vip/"/><s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>" target="_blank" id="isVip" class="isVip5">我的旺铺</a></li>
			<li><a href="<s:url value="/maintain/"/><s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>" target="_blank" id="isVip" class="isVip5">网站外观设计</a></li>
			<li><a href="http://www.865171.cn" target="main" id="isVip" class="isVip5">网站浏览分析</a></li>
			<li><a href="<s:url value="/user/album/albumshow"/>" target="main" id="isVip" class="isVip5">公司相册</a></li>
			<li><a href="<s:url value="/syncsite/viewSite"/>" target="main" id="isVip" class="isVip5">信息同步</a></li>
		</ul>
		</div>


		<h1 class="type"><a href="javascript:void(0)">帐号管理</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="<s:url value="/user/account/password/showUpdatePassword"/>"
				target="main">密码修改</a></li>
			<li><a href="<s:url value="/user/account/password/showPasswordProtection"/>"
				target="main">设置密码保护</a></li>
			<li><a href="<s:url value="/user/account/password/showSubAccount"/>"
				target="main">二级账号管理</a></li>

		</ul>
		</div>

		<h1 class="type"><a href="javascript:void(0)">工具箱</a></h1>
		<div class="content">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td><img src="<s:url value="/user/images/menu_topline.gif"/>" width="182" height="5" /></td>
			</tr>
		</table>
		<ul class="MM">
			<li><a href="http://www.865171.cn" target="main">网站IP屏蔽</a></li>
			<li><a href="http://www.865171.cn" target="main">搜索优化SEO</a></li>
			<li><a href="<s:url value="/user/favour/list"/>" target="main">收藏管理</a></li>
			<li><a href="<s:url value="/user/recycle/list"/>" target="main">回收站</a></li>
		</ul>
		</div>

		<script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
		</td>
	</tr>
</table>
<s:if test="#session.abbccuser.grade==00">
	<style>
	A.isVip1 {
	color:#808080;
	}
	A.isVip2 {
	color:#808080;
	}
	</style>
	<script type="text/javascript">
	jQuery('.isVip').attr('href', "<s:url value='/user/upgrade/show'/>");
	jQuery('.isVip5').attr('href', "<s:url value='/user/upgrade/show'/>");
	</script>
</s:if>
<s:if test="#session.memberState==03">
	<style>
	A.isVip2 {
	color:#808080;
	}
	</style>
	<script type="text/javascript">
	jQuery('.isVip5').attr('href', "<s:url value='/user/upgrade/show'/>");
	</script>
</s:if>
</body>
</html>
