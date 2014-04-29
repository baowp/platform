<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>管理页面</title>
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>

<script language="JavaScript">
function logout(){
	if (confirm("您确定要退出后台管理吗"))
	top.location = "userLogout";
}
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<base target="main">
<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0"
	cellspacing="0" class="admin_topbg">
	<tr>
		<td width="40%" height="64"><img src="images/logo.gif"
			width="262" height="64"></td>
		<td width="60%" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="75%" height="38" class="admin_txt">欢迎:${sessionScope.abbccuser.name
				}【<a href="right" target="main"><font color="red">首页</font></a>】【<a
					href="<s:url value='/user/menu/menuedit'/>"  target="main"><font color="red">个性菜单</font></a>】【<a
					href="<s:url value='/user/cellbind/view'/>"  target="main"><font color="red">手机绑定</font></a>】
				【<font color="red"  target="main">用户积分</font>】【<a
					href="<s:url value='/user/upgrade/show'/>"  target="main"><font color="red">升级会员</font></a>】
				【<a href="<s:url value='/user/help/show'/>"  target="main"><font color="red">帮助中心</font></a>】【<a
					href="#" onmouseover="show(this,'show')" target="main"><font
					color="red" target="main">导航</font></a>】<br />
				<div id="show" align="left"
					style="BORDER-RIGHT: black 1px solid PADDING-RIGHT: 20px; BORDER-TOP: black 1px solid; PADDING-LEFT: 20px; Z-INDEX: 100; VISIBILITY: hidden; PADDING-BOTTOM: 20px; BORDER-LEFT: black 1px solid; WIDTH: 600px;PADDING-TOP: 20px; BORDER-BOTTOM: black 1px solid; POSITION: absolute; background-color: #F0FFFF"
					onmouseout="hide('show')" onmouseover="show(this,'show')">
				<div style="float: left; background-color: #F0F8FF;">
				<ul>
					<li><a href="javascript:void(0)"><font color="red">公司管理</font></a></li>
					<s:iterator var="user" value="%{#session.customList}" status="st">
						<li><a href="<s:url value='%{action}'/>" target="main">${name}</a></li>
					</s:iterator>
					<li><a href="javascript:void(0)"><font color="red">会员管理</font></a></li>
					<li><a href="<s:url value="/user/subMember/subMembershow"/>"
						target="main">查看会员</a></li>
					<li><a href="<s:url value="/user/subMember/statshow"/>"
						target="main">会员统计</a></li>
				</ul>
				</div>
				<div style="float: left; background-color: #F0F8FF;">
				<ul>
					<li><a href="javascript:void(0)"><font color="red">产品管理</font></a></li>
					<li><a href="<s:url value="/user/supply/add"/>" target="main">发布供求信息</a></li>
					<li><a href="<s:url value="/user/supply/auditing"/>"
						target="main">供求信息管理</a></li>
					<li><a href="<s:url value="/user/product/add"/>" target="main">发布新产品</a></li>
					<li><a href="<s:url value="/user/product/auditing"/>"
						target="main">产品管理</a></li>
					<li><a href="<s:url value="/user/product/brand/list"/>"
						target="main">品牌管理</a></li>
					<li><a href="<s:url value="/user/product/category/show"/>"
						target="main">分类管理</a></li>
					<li><a href="javascript:void(0)"><font color="red">工具箱</font></a></li>
					<li><a href="http://www.865171.cn" target="main">网站IP屏蔽</a></li>
					<li><a href="http://www.865171.cn" target="main">搜索优化SEO</a></li>
					<li><a href="<s:url value="/user/favour/list"/>" target="main">收藏管理</a></li>
					<li><a href="<s:url value="/user/recycle/list"/>"
						target="main">回收站</a></li>
				</ul>
				</div>
				<div style="float: left; background-color: #F0F8FF;">

				<ul>
					<li><a href="javascript:void(0)"><font color="red">新闻管理</font></a></li>
					<li><a href="<s:url value="/user/news/newsshow"/>"
						target="main">新闻管理</a></li>
					<li><a href="<s:url value="/user/news/newslayout"/>"
						target="main">新闻布局</a></li>
					<li><a href="<s:url value="/user/news/categorysshow"/>"
						target="main">分类管理</a></li>
					<li><a href="javascript:void(0)"><font color="red">订单管理</font></a></li>
					<li><a href="<s:url value="/user/order/list"/>" target="main">查看订单</a></li>
					<li><a href="<s:url value="/user/order/stat"/>" target="main">订单统计</a></li>
					<li><a href="javascript:void(0)"><font color="red">帐号管理</font></a></li>
					<li><a
						href="<s:url value="/user/account/password/showUpdatePassword"/>"
						target="main">密码修改</a></li>
					<li><a
						href="<s:url value="/user/account/password/showPasswordProtection"/>"
						target="main">设置密码保护</a></li>
					<li><a
						href="<s:url value="/user/account/password/showSubAccount"/>"
						target="main">二级账号管理</a></li>
				</ul>
				</div>
				<div style="float: left; background-color: #F0F8FF;">

				<ul>
					<li><a href="javascript:void(0)"><font color="red">商业信息</font></a></li>
					<li><a href="<s:url value="/user/message/messageentry"/>"
						target="main">站内留言</a></li>
					<li><a href="<s:url value="/user/message/messagesysEntry"/>"
						target="main">系统留言</a></li>
					<li><a href="<s:url value="/user/message/messagevisitor"/>"
						target="main">访问者留言</a></li>
					<li><a href="http://www.865171.cn" target="main">我的访问者</a></li>
					<!--
					<li><a href="<s:url value="/user/contact/show"/>"
						target="main">我的联系人</a></li>
					-->
					<li><a href="javascript:void(0)"><font color="red">我的钱包</font></a></li>
					<li><a href="<s:url value="/user/wallet/paylog/records"/>"
						id="isVip" target="main" class="isVip1">付费记录</a></li>
					<li><a href="<s:url value="/user/wallet/paylog/applying"/>"
						id="isVip" target="main" class="isVip1">续费服务</a></li>
					<li><a href="<s:url value="/user/wallet/paylog/cert"/>"
						id="isVip" target="main" class="isVip1">付费凭证</a></li>
					<li><a href="<s:url value="/user/wallet/paylog/balance"/>"
						id="isVip" target="main" class="isVip1">余额管理</a></li>
				</ul>
				</div>
				<div style="float: left; background-color: #F0F8FF;">
				<s:if test="#session.memberState!=03">
				<ul>
				
					<li><a href="javascript:void(0)"><font color="red">管理旺铺</font></a></li>
					<li><a
						href="<s:url value="/vip/"/><s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>"
						target="_blank" id="isVip" class="isVip5">我的旺铺</a></li>
					<li><a
						href="<s:url value="/maintain/"/><s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>"
						target="_blank" id="isVip" class="isVip5">网站外观设计</a></li>
					<li><a href="http://www.865171.cn" target="main" id="isVip"
						class="isVip5">网站浏览分析</a></li>
					<li><a href="<s:url value="/user/album/albumshow"/>"
						target="main" id="isVip" class="isVip5">公司相册</a></li>


				</ul>
</s:if>
				</div>



				</div>
				</td>
				<td width="25%"><a href="#" target="_self" onClick="logout();"><img
					src="images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
			</tr>
			<tr>
				<td height="19" colspan="3">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
