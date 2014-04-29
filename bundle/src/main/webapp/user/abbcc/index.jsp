<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>用户后台</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="images/admincp.css" id="skin">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript"
	src="<s:url value="/user/abbcc/js/common.js"/>"></script>
<script src="<s:url value="/user/js/prototype.lite.js"/>"
	type="text/javascript"></script>
<script src="<s:url value="/user/js/moo.fx.js"/>" type="text/javascript"></script>
<script src="js/skin.js" type="text/javascript"></script>
<script src="js/load.js" type="text/javascript"></script>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="no-cache">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Cache-Control" content="no-cache">
</HEAD>
<BODY style="MARGIN: 0px" scroll=no>
<DIV id=append_parent></DIV>
<TABLE cellSpacing=0 cellPadding=0 width="100%" height="100%">
	<TBODY>
		<TR>
			<TD height=90 colSpan=2>
			<DIV class=mainhd>
			<DIV><s:if test="%{#session.logoEnt==null}">
				<img id="indexlogo" src="/images/logo.gif" width="160" />
			</s:if><s:else>
				<img id="indexlogo" src="${sessionScope.logoEnt}" alt="还没上传logo"
					width="160">
			</s:else></DIV>
			<DIV class=uinfo>
			<P>您好, <EM><s:if test="%{#session.abbccsubaccount==null}">${sessionScope.abbccuser.name}</s:if><s:else>${sessionScope.abbccsubaccount.name}</s:else></EM>
			[ <A target="_self" onClick="logout();" href="#">退出</A> ]</P>
			<P class=btnlink><A href="<s:url value="/"/>" target=_blank>首页</A></P>
			</DIV>
			<DIV class=navbg></DIV>
			<DIV class=nav>
			<UL id=topmenu>
				<LI><EM><A hideFocus id=header_index
					onclick="toggleMenu('index', 'home');"
					href="<s:url value='/user/right'/>" target="main">首页</A></EM></LI>
				<LI><EM><A hideFocus id=header_tool
					onclick="toggleMenu('tool', 'tools&amp;operation=updatecache');"
					href="javascript:;">客户</A></EM></LI>
				<LI><EM><A hideFocus id=header_global
					onclick="toggleMenu('global', 'settings&amp;operation=basic');"
					href="javascript:;">公司</A></EM></LI>
				<LI><EM><A hideFocus id=header_style
					onclick="toggleMenu('style', 'settings&amp;operation=styles');"
					href="javascript:;">产品</A></EM></LI>
				<LI><EM><A hideFocus id=header_forum
					onclick="toggleMenu('forum', 'forums');" href="javascript:;">新闻</A></EM></LI>
				<LI><EM><A hideFocus id=header_user
					onclick="toggleMenu('user', 'members');" href="javascript:;">钱包</A></EM></LI>
				<LI><EM><A hideFocus id=header_topic
					onclick="toggleMenu('topic', 'moderate&amp;operation=threads');"
					href="javascript:;">网站</A></EM></LI>
				<LI><EM><A hideFocus id=header_extended
					onclick="toggleMenu('extended', 'tasks');" href="javascript:;">工具</A></EM></LI>
				<LI><EM><A hideFocus id=header_count
					onclick="toggleMenu('count', 'count');" href="javascript:;">帐号</A></EM></LI>
				<LI><EM><A hideFocus id=header_adv
					onclick="toggleMenu('adv', 'adv');"
					href="<s:url value='/user/menu/menuedit'/>" target="main">个性菜单</A></EM></LI>



			</UL>
			<DIV class=currentloca>
			<DIV class=navbd></DIV>
			<DIV class=sitemapbtn>

			<DIV id="makestate"
				style="margin: 0px 300px 0px 0px; float: left; display: none;">
			<div class="stateValue"><font id="uState"
				style="font-size: 20px;">修改成功</font></div>
			</DIV>
			<SPAN id=add2custom>皮肤:<a class="styleswitch a1"
				style="CURSOR: pointer" rel="admincp">默认</a>|<a
				class="styleswitch a2" style="CURSOR: pointer" rel=admincp_fh>粉红</a>|<a
				class="styleswitch a2" style="CURSOR: pointer" rel=admincp_g>蓝色</a>|<a
				class="styleswitch a2" style="CURSOR: pointer" rel=admincp_lv>绿色</a></SPAN><A
				id=cpmap onclick="showMap();return false;" href="#"><IMG
				title=后台导航 src="images/btn_map.gif" width=72 height=18></A></DIV>
			</DIV>
			</DIV>
			</DIV>
			</TD>
		</TR>
		<TR>
			<TD class=menutd vAlign=top width=160>
			<DIV id=leftmenu class=menu>
			<UL style="DISPLAY: none" id=menu_global>
				<s:iterator var="user" value="%{#session.customList}" status="st">
					<li><a hideFocus href="<s:url value='%{action}'/>"
						target="main">${name}</a></li>
				</s:iterator>
				<li><a hideFocus href="<s:url value="/user/enterprise/key"/>"
					target="main">SEO优化</a></li>
				<li><a hideFocus href="<s:url value="/user/album/albumshow"/>"
					target="main">公司相册</a></li>
				<li><a hideFocus
					href="<s:url value="/user/message/messagesysEntry"/>" target="main">系统留言</a></li>


			</UL>
			<UL style="DISPLAY: none" id=menu_forum>
				<li><a hideFocus href="<s:url value="/user/news/newsshow"/>"
					target="main">新闻管理</a></li>
				<li><a hideFocus href="<s:url value="/user/news/newslayout"/>"
					target="main">新闻布局</a></li>
				<li><a hideFocus
					href="<s:url value="/user/news/categorysshow"/>" target="main">分类管理</a></li>
			</UL>
			<UL style="DISPLAY: none" id=menu_user>
				<li><a hideFocus
					href="<s:url value="/user/wallet/paylog/records"/>" id="isVip"
					target="main" class="isVip1">付费记录</a></li>
				<li><a hideFocus
					href="<s:url value="/user/wallet/paylog/applying"/>" id="isVip"
					target="main" class="isVip1">续费服务</a></li>
				<li><a hideFocus
					href="<s:url value="/user/wallet/paylog/cert"/>" id="isVip"
					target="main" class="isVip1">付费凭证</a></li>
				<li><a hideFocus
					href="<s:url value="/user/wallet/paylog/balance"/>" id="isVip"
					target="main" class="isVip1">余额管理</a></li>
			</UL>
			<UL style="DISPLAY: none" id=menu_topic>
				<li><a hideFocus
					href="http://<s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>.vip.51archetype.com"
					target="_blank" id="isVip" class="isVip5">网站在线预览</a></li>
				<li><a hideFocus sitedesign
					href="http://<s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>.mai51archetype.comcc.net"
					target="_blank" id="isVip" class="isVip5">网站外观设计</a></li>
				<li><a hideFocus sitestatic href="<s:url value="/user/static.jsp"/>"
					target="main" id="isVip" class="isVip5">生成企业网站</a></li>
				<li><a hideFocus
					href="http://<s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].51archetype.com/>.51archetype.com"
					target="_blank" id="isVip" class="isVip5">我的企业网站</a></li>
				<li>
					<a href="/vipsite/seo/edit" target="main" id="isVip" class="isVip5">搜索优化设置</a>
				</li>
				<li><a hideFocus href="<s:url value="/user/bind/apply"/>"
					target="main" id="isVip" class="isVip5">旺铺域名绑定</a></li>
				<li><a hideFocus
					href="<s:url value="/user/viewlog/viewLogMain"/>" target="main"
					id="isVip" class="isVip5">网站浏览分析</a></li>
				<li><a hideFocus
					href="<s:url value="/user/syncsite/sync.jsp"/>" target="main"
					id="isVip" class="isVip5">网站信息同步</a></li>
			</UL>
			<UL style="DISPLAY: none" id=menu_extended>
				<li><a hideFocus href="<s:url value="/user/favour/list"/>"
					target="main">收藏管理</a></li>
				<!-- <li><a hideFocus href="<s:url value="/user/recycle/list"/>"
					target="main">回收站</a></li> -->
			</UL>
			<UL style="DISPLAY: none" id=menu_style>
				<li><a hideFocus href="<s:url value="/user/supply/add"/>"
					target="main">发布供求</a></li>
				<li><a hideFocus href="<s:url value="/user/supply/published"/>"
					target="main">供求管理</a></li>
				<li><a hideFocus href="<s:url value="/user/product/add"/>"
					target="main">发布产品</a></li>
				<li><a hideFocus
					href="<s:url value="/user/product/published"/>" target="main">产品管理</a></li>
				<li><a hideFocus
					href="<s:url value="/user/product/brand/list"/>" target="main">品牌管理</a></li>
				<li><a hideFocus
					href="<s:url value="/user/product/category/show"/>" target="main">分类管理</a></li>

			</UL>


			<UL style="DISPLAY: none" id=menu_index>
				<LI><a hideFocus
					href="<s:url value='/user/enterprise/company/logo'/>" target="main">logo管理</a></LI>
				<li><a hideFocus href="<s:url value='/user/news/newsaddPage'/>"
					target="main">发布新闻</a></li>
				<LI><a hideFocus href="<s:url value="/user/product/add"/>"
					target="main">发布产品</a></LI>
				<LI><a hideFocus href="<s:url value="/user/supply/add"/>"
					target="main">发布供求</a></LI>
				<LI><a hideFocus href="<s:url value="/user/order/list"/>"
					target="main">查看订单</a></LI>
				<LI><a hideFocus
					href="<s:url value="/user/subMember/subMembershow"/>" target="main">查看客户</a></LI>
				<LI><a hideFocus
					href="<s:url value="/user/account/password/showUpdatePassword"/>"
					target="main">密码修改</a></LI>
				<LI><a hideFocus href="<s:url value='/user/cellbind/view'/>"
					target="main">手机绑定</a></LI>
				<LI id="upgradeMember"><a hideFocus
					href="<s:url value='/user/upgrade/show'/>" target="main">升级会员</a></LI>
				<LI><a hideFocus href="<s:url value='/user/help/show'/>"
					target="main">帮助中心</a></LI>
				<LI><a hideFocus href="<s:url value='/user/log/show'/>"
					target="main">查看日志</a></LI>

			</UL>
			<UL style="DISPLAY: none" id=menu_tool>
				<li><a hideFocus
					href="<s:url value="/user/message/messagevisitor"/>" target="main">网站留言</a></li>
				<li><a hideFocus
					href="<s:url value="/user/message/messageentry"/>" target="main">站内留言</a></li>
				<li><a hideFocus
					href="<s:url value="/user/subMember/subMembershow"/>" target="main">查看客户</a></li>
				<li><a hideFocus
					href="<s:url value="/user/subMember/statshow"/>" target="main">客户统计</a></li>
				<li><a hideFocus
					href="<s:url value='/user/sub_member/sub_member_add.jsp'/>"
					target="main">添加客户</a></li>
				<li><a hideFocus href="<s:url value="/user/order/list"/>"
					target="main">查看订单</a></li>
				<li><a hideFocus href="<s:url value="/user/order/stat"/>"
					target="main">订单统计</a></li>

			</UL>
			<UL style="DISPLAY: none" id=menu_adv>
				<s:iterator var="user" value="%{#session.customList}" status="st">
					<li><a hideFocus href="<s:url value='%{action}'/>"
						target="main">${name}</a></li>
				</s:iterator>
			</UL>
			<UL style="DISPLAY: none" id=menu_count>
				<li><a hideFocus
					href="<s:url value="/user/account/password/showUpdatePassword"/>"
					target="main">密码修改</a></li>
				<li><a hideFocus
					href="<s:url value="/user/account/password/showPasswordProtection"/>"
					target="main">密码保护</a></li>
				<li><a hideFocus
					href="<s:url value="/user/account/password/showSubAccount"/>"
					target="main">二级账号</a></li>
			</UL>
			</DIV>
			</TD>
			<TD class=mask vAlign=top width="100%"><IFRAME id="mainFrame"
				style="OVERFLOW: visible" height="100%"
				src="<s:url value='/user/right'/>" frameBorder=0 width="100%"
				name=main scrolling=yes></IFRAME></TD>
		</TR>
	</TBODY>
</TABLE>
<DIV class=copyright>
<P>Powered by <A href="http://abbcc.cn/" target=_blank>东方五金</A></P>
<P>&copy; 2010-2019, <A href="http://abbcc.cn/" target=_blank>团队
</A></P>
</DIV>
<DIV style="DISPLAY: none" id=cpmap_menu class=custom>
<DIV class=cside>
<center>版权所有，东方五金</center>

</DIV>
<DIV id=cmain class=cmain></DIV>
<DIV class=cfixbd></DIV>
</DIV>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	java.util.Date currentTime = new java.util.Date();//得到当前系统时间
	com.abbcc.util.DateUtil du = new com.abbcc.util.DateUtil();

	String str_date1 = formatter.format(currentTime); //将日期时间格式化
	String str_date2 = du.getWeek(); //得到星期几
%>

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
<script type="text/javascript"
	src="<s:url value="/user/abbcc/js/index.js"/>"></script>
<script language="JavaScript">
function logout(){
	if (confirm("您确定要退出后台管理吗"))
	top.location = "../userLogout";
}
</script>
</BODY>
<script type="text/javascript" src="/js/unIframe.js"></script>
</HTML>
