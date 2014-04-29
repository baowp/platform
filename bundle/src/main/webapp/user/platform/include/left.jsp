<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="leftmenu"
	style="background: url(&quot;images/bg-leftmenu.png&quot;) repeat-y scroll 0% 0% transparent; height: 430px;"><!--menu-->
<div id="home">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="首页栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">首页栏目</span></a>
<div class="lv-2-box"><!-- <a hideFocus class="menu-bg lv-2" href="<s:url value='/user/enterprise/company/logo'/>"
	onclick="loadingIframe('')" target="main"><span
	class="menu-bg lv-2-txt">logo管理</span></a> --><a hideFocus
	class="menu-bg lv-2" id="indexMenu" 
	href="javascript:openMenu()" ><span
	class="menu-bg lv-2-txt">首页定制</span></a> <s:action name="menuedit"
	namespace="/user/menu"><s:param name="pageType" value="'index'"></s:param> </s:action>
<div id="indexCustom"><s:iterator value="%{#request.resultList}">
<s:if test="%{menuId=='Menu_000000000000000000000000036'}">
<a hideFocus class="menu-bg lv-2" id="isVip"
		href="${action }" target="_blank"><span class="menu-bg lv-2-txt">${name}</span></a>
</s:if>
<s:elseif test="%{menuId=='Menu_000000000000000000000000038'}">
<a hideFocus class="menu-bg lv-2" id="isVip"
		href="${action }" target="_blank"><span class="menu-bg lv-2-txt">${name}</span></a>
</s:elseif>
<s:elseif test="%{menuId=='Menu_000000000000000000000000035'}">
<a hideFocus class="menu-bg lv-2" id="isVip"
		href="${action }" target="_blank"><span class="menu-bg lv-2-txt">${name}</span></a>
</s:elseif><s:else>
	<a hideFocus class="menu-bg lv-2" onclick="loadingIframe('')"
		href="${action }" target="main"><span class="menu-bg lv-2-txt">${name}</span></a>
</s:else>
</s:iterator>
<s:if test="%{#request.resultList.size==0}"><h3>(还未定制任何栏目<br/>请点击'首页定制')</h3></s:if>
</div>
</div>
</div>
</div>
</div>
<!-- 客户 -->
<div id="customers" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="客户栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">客户栏目</span></a>
<div class="lv-2-box"><a hideFocus class="menu-bg lv-2"
	href="<s:url value="/inquiry/view"/>" target="main"><span
	class="menu-bg lv-2-txt">询盘管理</span></a><a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/message/messagesearchVisitorNotRead"/>" target="main"><span
	class="menu-bg lv-2-txt">网站留言</span></a> <a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/message/messageentry"/>" target="main"><span
	class="menu-bg lv-2-txt">站内留言</span></a> <a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/subMember/subMembershow"/>" target="main"><span
	class="menu-bg lv-2-txt">查看客户</span></a> <a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/subMember/statshow"/>" target="main"><span
	class="menu-bg lv-2-txt">客户统计</span></a>  <a
	hideFocus class="menu-bg lv-2" href="<s:url value="/user/order/list"/>"
	target="main"><span class="menu-bg lv-2-txt">查看订单</span></a> <a
	hideFocus class="menu-bg lv-2" href="<s:url value="/user/order/stat"/>"
	target="main"><span class="menu-bg lv-2-txt">订单统计</span></a></div>
</div>
</div>
</div>

<!-- 公司 -->
<div id="company" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="公司栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">公司栏目</span></a>
<div class="lv-2-box"><a target="main"
	href="/user/enterprise/company/showInformationManage" hidefocus=""
	class="menu-bg lv-2"><span class="menu-bg lv-2-txt">基本信息</span></a> <a
	target="main" href="/user/enterprise/company/showEnterpriseInfo"
	hidefocus="" class="menu-bg lv-2"><span class="menu-bg lv-2-txt">公司简介</span></a>
<a target="main" href="/user/technical/showTechnic.action" hidefocus=""
	class="menu-bg lv-2"><span class="menu-bg lv-2-txt">技术实力</span></a> <a
	target="main" href="/user/enterprise/certificate/showCertificate"
	hidefocus="" class="menu-bg lv-2"><span class="menu-bg lv-2-txt">证书管理</span></a>
<a target="main" href="/user/enterprise/job/recruitManage" hidefocus=""
	class="menu-bg lv-2"><span class="menu-bg lv-2-txt">招聘管理</span></a>  <a
	target="main" href="/user/file_manage/file.html" hidefocus=""
	class="menu-bg lv-2"><span class="menu-bg lv-2-txt">文件管理</span></a> <a
	target="main" href="/user/message/messagesysEntry" hidefocus=""
	class="menu-bg lv-2"><span class="menu-bg lv-2-txt">系统留言</span></a> <a
	target="main" href="/rest/user/enterprise/link/list" hidefocus=""
	class="menu-bg lv-2"><span class="menu-bg lv-2-txt">友情链接</span></a></div>
</div>
</div>
</div>

<!-- 产品 -->
<div id="product" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="产品栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">产品栏目</span></a>
<div class="lv-2-box">
<a hideFocus  aType="add" href="<s:url value="/user/product/category/show"/>"
	class="menu-bg lv-2" target="main"><span class="menu-bg lv-2-txt">分类管理</span></a>
<a hideFocus href="<s:url value="/user/product/brand/list"/>"
	class="menu-bg lv-2" target="main"><span class="menu-bg lv-2-txt">品牌管理</span></a>
<a hideFocus 
	href="<s:url value="/user/supply/add"/>" class="menu-bg lv-2"
	target="main"><span class="menu-bg lv-2-txt">发布供求</span></a> <a
	hideFocus href="<s:url value="/user/supply/published"/>"
	class="menu-bg lv-2" target="main"><span class="menu-bg lv-2-txt">供求管理</span></a>
<a hideFocus href="<s:url value="/user/product/add"/>"
	class="menu-bg lv-2" target="main"><span class="menu-bg lv-2-txt">发布产品</span></a>
<a hideFocus  href="<s:url value="/user/product/published"/>"
	class="menu-bg lv-2" target="main"><span class="menu-bg lv-2-txt">产品管理</span></a>


</div>
</div>
</div>
</div>

<!-- 新闻 -->
<div id="news" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="新闻栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">新闻栏目</span></a>
<div class="lv-2-box"><a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/news/newsshow"/>" target="main"><span
	class="menu-bg lv-2-txt">新闻管理</span></a> <a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/news/newslayout"/>" target="main"><span
	class="menu-bg lv-2-txt">新闻布局</span></a> <a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/news/categorysshow"/>" target="main"><span
	class="menu-bg lv-2-txt">分类管理</span></a></div>
</div>
</div>
</div>

<!-- 钱包 -->
<div id="wallet" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="钱包栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">钱包栏目</span></a>
<div class="lv-2-box"><a hideFocus class="menu-bg lv-2 isVip1"
	href="<s:url value="/user/wallet/paylog/records"/>" onclick="openDia(this,true)" id="isVip"
	target="main"><span class="menu-bg lv-2-txt">付费记录</span></a> <a
	hideFocus class="menu-bg lv-2 isVip1"
	href="<s:url value="/user/wallet/paylog/applying"/>"  onclick="openDia(this,true)" id="isVip"
	target="main"><span class="menu-bg lv-2-txt">续费服务</span></a> <a
	hideFocus class="menu-bg lv-2 isVip1"
	href="<s:url value="/user/wallet/paylog/cert"/>" onclick="openDia(this,true)" id="isVip"
	target="main"><span class="menu-bg lv-2-txt">付费凭证</span></a> <a
	hideFocus class="menu-bg lv-2 isVip1"
	href="<s:url value="/user/wallet/paylog/balance"/>" onclick="openDia(this,true)" id="isVip"
	target="main"><span class="menu-bg lv-2-txt">余额管理</span></a></div>
</div>
</div>
</div>

<!-- 网站 -->
<div id="webSite" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="网站栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">网站栏目</span></a>
<div class="lv-2-box"><a hideFocus class="menu-bg lv-2 isVip5"
	href="http://<s:property value='#session[@com.abbcc.common.CommonConst@SESSIONUSER].username'/>.vip.51archetype.com"
	target="_blank" id="isVip"><span class="menu-bg lv-2-txt">网站在线预览</span></a>
<a hideFocus class="menu-bg lv-2 isVip5"
	href="http://<s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].username"/>.mai51archetype.comcc.net"
	target="_blank" id="isVip"><span class="menu-bg lv-2-txt">网站外观设计</span></a>
<a hideFocus class="menu-bg lv-2 isVip5"
	href="<s:url value="/user/static.jsp"/>" onclick="openDia(this,true)" target="main" id="isVip"><span
	class="menu-bg lv-2-txt">生成企业网站</span></a> <a hideFocus
	class="menu-bg lv-2 isVip5"
	href="http://<s:property value="#session[@com.abbcc.common.CommonConst@SESSIONUSER].51archetype.com/>.51archetype.com"
	target="_blank" id="isVip"><span class="menu-bg lv-2-txt">我的企业网站</span></a>

<a href="/vipsite/seo/edit" onclick="openDia(this,true)" class="menu-bg lv-2 isVip5" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">搜索优化设置</span></a> <a
	hideFocus class="menu-bg lv-2 isVip5"
	href="<s:url value="/user/bind/apply"/>" onclick="openDia(this,true)" target="main" id="isVip"><span
	class="menu-bg lv-2-txt">旺铺域名绑定</span></a> <a hideFocus
	class="menu-bg lv-2 isVip5"
	href="<s:url value="/user/viewlog/viewLogMain"/>" onclick="openDia(this,true)" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">网站浏览分析</span></a> <a
	hideFocus class="menu-bg lv-2 isVip5" onclick="openDia(this,true)"
	href="<s:url value="/user/analysis/edit"/>" target="main" id="isVip"><span
	class="menu-bg lv-2-txt">第三方统计分析</span></a> <!--<a hideFocus class="menu-bg lv-2 isVip5" href="<s:url value="/user/syncsite/sync.jsp"/>" 
		target="main" id="isVip"><span class="menu-bg lv-2-txt">网站信息同步</span></a>--></div>
</div>
</div>
</div>

<!-- 集团网站 -->
<div id="groupSite" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="网站栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">网站栏目</span></a>
<div class="lv-2-box"><a hideFocus class="menu-bg lv-2 isVip5"
	href="/rest/site/<s:property value='#session[@com.abbcc.common.CommonConst@SESSIONUSER].username'/>/"
	target="_blank" id="isVip"><span class="menu-bg lv-2-txt">网站在线预览</span></a>
<a hideFocus class="menu-bg lv-2 isVip5"
	href="/rest/maintain/<s:property value='#session[@com.abbcc.common.CommonConst@SESSIONUSER].username'/>/"
	target="_blank" id="isVip"><span class="menu-bg lv-2-txt">网站外观设计</span></a>
<a hideFocus class="menu-bg lv-2 isVip5" onclick="openDia(this,true)"
	href="/user/publish.jsp" target="main" id="isVip"><span
	class="menu-bg lv-2-txt">生成集团网站</span></a> <a hideFocus
	class="menu-bg lv-2 isVip5"
	href="http://<s:property value="#session[@com.abbcc.common.CommonConst@SESSION51archetype.comrname"/>.51archetype.com"
	target="_blank" id="isVip"><span class="menu-bg lv-2-txt">我的集团网站</span></a>
	 <a hideFocus class="menu-bg lv-2 isVip5" onclick="openDia(this,true)"
	href="<s:url value="/rest/group/navigator/list"/>" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">导航菜单维护</span></a> 
	<a hideFocus
	class="menu-bg lv-2 isVip5" onclick="openDia(this,true)"
	href="<s:url value="/rest/group/gaim/list"/>" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">即时通讯设置</span></a> 
	<a
	hideFocus class="menu-bg lv-2 isVip5" onclick="openDia(this,true)"
	href="<s:url value="/rest/group/analysis/list"/>" target="main" id="isVip"><span
	class="menu-bg lv-2-txt">统计分析设置</span></a>

<a href="/rest/group/seo/list" onclick="openDia(this,true)" class="menu-bg lv-2 isVip5" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">搜索优化设置</span></a>
	<a hideFocus
	class="menu-bg lv-2 isVip5" onclick="openDia(this,true)"
	href="<s:url value="/rest/group/userdefined/list"/>" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">自定义设置</span></a> 
	 <a	hideFocus class="menu-bg lv-2 isVip5"
	href="<s:url value="/user/bind/apply"/>" target="main" id="isVip"><span
	class="menu-bg lv-2-txt">旺铺域名绑定</span></a> 
	<a hideFocus
	class="menu-bg lv-2 isVip5"
	href="<s:url value="/user/viewlog/viewLogMain"/>" target="main"
	id="isVip"><span class="menu-bg lv-2-txt">网站浏览分析</span></a> 
		</div>
</div>
</div>
</div>

<!-- 工具 -->
<div id="tools" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="工具栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">工具栏目</span></a>
<div class="lv-2-box"><a class="menu-bg lv-2" hideFocus
	href="<s:url value="/user/favour/list"/>" target="main"><span
	class="menu-bg lv-2-txt">收藏管理</span></a></div>
</div>
</div>
</div>

<!-- 账号 -->
<div id="account" style="display: none;">
<div id="workleft">
<div id="menubox"><a class="menu-bg lv-1-opn" title="账号栏目"
	name="ahrefpath" href="javascript:void(0);"><span
	class="menu-bg lv-1-txt">账号栏目</span></a>
<div class="lv-2-box"><a hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/account/password/showUpdatePassword"/>"
	target="main"><span class="menu-bg lv-2-txt">密码修改</span></a> <a
	hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/account/password/showPasswordProtection"/>"
	target="main"><span class="menu-bg lv-2-txt">密码保护</span></a><!--<a
	hideFocus class="menu-bg lv-2"
	href="<s:url value="/user/account/password/showSubAccount"/>"
	target="main"><span class="menu-bg lv-2-txt">二级账号</span></a> --></div>
</div>
</div>
</div>
</div>