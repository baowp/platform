<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="w952" id="header">
<div class="work-top-bar">
<div class="top-bar-1"><a class="logo" target="_blank"
	title="返回首页" href="/"></a><span id="memberinfo" class="memberinfo">您好， <a
	title="${sessionScope.abbccuser.name}" href="javascript:void(0)">${sessionScope.abbccuser.name}</a></span><a
	href="javascript:logout()" class="lnk">[退出]</a><a target="_self"
	href="/" class="go-alihelper">返回首页&gt;&gt;</a></div>
<div class="top-bar-2">
<div id="mod-search-box" class="mod-search-box">
<ul class="clr search-box">
	<li class="select-box" id="yui-gen0">
	<p class="selected-value">产品</p>
	<ul id="yuievtautoid-0" class="clr select-types">
		<li id="product/published" uName="name">产品</li>
		<li id="news/newsshow" uName="title">新闻</li>
		<li id="supply/published" uName="title">供求</li>
		<li id="enterprise/certificate/showCertificate" uName="name">证书</li>
		<li id="enterprise/job/recruitManage" uName="title">招聘</li>
	</ul>
	<input type="hidden" id="searchUrl" value="product/published">
	<input type="hidden" id="searchName" value="name">
	</li>
	<li><input type="text"  class="search-text"
		name="keywords"></li>
	<li>
	<button class="search-btn" type="button"><em>搜索</em></button>
	</li>
</ul>
</div>
</div>
<div class="top-bar-3">
<div id="mod-work-msg" class="mod-work-msg">
<ul class="clr mod-work-msg-wrap">
	<li><a href="#" id="wk-notice" class="wk-notice">消息提醒</a></li>
	<li id="work-msg-box" class="work-msg">
	<div class="work-msg-box"><a id="work-msg" class="wk-msg"
		href="javascript:" target="_blank">留言</a></div>
	<ul id="work-msg-list" class="work-msg-list">
		<li><a onclick="openDia(this,true)" href="/user/message/messagevisitor" target="main">网站留言</a></li>
		<li><a onclick="openDia(this,true)"  href="/user/message/messageentry" target="main">站内留言</a></li>
	</ul>
	</li>
</ul>
</div>
</div>
<s:action name="hint" namespace="/user"></s:action>
<div class="top-bar-4">
<div id="mod-work-msg" class="mod-work-msg" sizcache>
<ul class="sUl">
	<s:iterator value="%{#request.hintList}">
		<s:property escape="false" />
	</s:iterator>
</ul>

</div>
</div>
</div>
<div class="work-head-new">
<div class="work-head-m">
<div class="work-head-m-c">
<ul class="work-nav-new">
	<li id="work-nav-newli1"><a to="home" href="javascript:void(0)"
		class="b current">首页</a></li>
	<li><a href="javascript:void(0)" to="customers" class="b">客户</a></li>
	<li><a href="javascript:void(0)" to="company" class="b">公司</a></li>
	<li><a href="javascript:void(0)" to="product" class="b">产品</a></li>
	<li><a href="javascript:void(0)" to="news" class="b">新闻</a></li>
	<li><a href="javascript:void(0)" to="wallet" class="b">钱包</a></li>
	<li><a href="javascript:void(0)" to="tools" class="b">工具</a></li>
	<li><a href="javascript:void(0)" to="webSite" class="b">网站</a></li>
	<s:if test="#session[@com.abbcc.common.CommonConst@SESSIONUSER].grade==@com.abbcc.common.CommonConst@USERGRADETWO">
		<li><a href="javascript:void(0)" to="groupSite" class="b">集团</a></li>
	</s:if>
	<li><a href="javascript:void(0)" to="account" class="b">帐号</a></li>
</ul>
</div>
</div>
<div class="work-head-l">
<ul>
	<li id="corp-name"><a title="${sessionScope.abbccEnterprise.name}" style="width:10px;"
		href="javascript:" id="headEntName" class="lnk">${sessionScope.abbccEnterprise.name}</a>(<a href="javascript:openMap()" id="mapbar"><font color="red">地图</font></a>)</li>
</ul>
</div>
</div>
<s:action name="member" namespace="/user"></s:action>
<div class="work-status">
<div class="work-show-btn"><a href="#" onfocus="this.blur()"
	class="lnk work-show-off" state="0" id="work-show-btn">收起状态栏</a></div>
<div id="work-myinfo" class="work-myinfo"
	style="display: block; height: 52px;">
<div id="work-loading" class="work-loading" style="display: none;"><img
	align="absmiddle" src="images/loading.gif"></div>
<div id="work-myinfo-l" class="work-myinfo-l">
<p class="tplogo normal-member">会员类别:<s:if
	test="#session.abbccuser.grade==00">免费用户[<a href="/user/upgrade/show" onclick="openDia(this,true)" target="_blank">升级</a>]</s:if><s:if
	test="#session.abbccuser.grade==01">
	企业版[<a href="/user/upgrade/show" target="_blank" onclick="openDia(this,true)">升级</a>]</s:if><s:if test="#session.abbccuser.grade==02">
	集团版</s:if></p>
	
</div>
<div id="work-myinfo-m" class="work-myinfo-m">
<dl class="account-info">
	<dd class="myweb">我的网站<s:if test="#session.abbccuser.grade==00">[<a
			href="/user/upgrade/show" onclick="openDia(this,true)" target="_blank">升级</a>]</s:if><s:else>[<a
			href="/user/upgrade/confirm?grade=${sessionScope.abbccuser.grade} "  onclick="openDia(this,true)" target="_blank">续费</a>]</s:else></dd>
	<dd>免费网站：<span class="orange"><a
		href="http://${sessionScope.abbccuser.domain}/site/${sessionScope.abbccuser.username}"
		target="blank">访问</a></span></dd>
	<dd>旺铺网站：<span class="orange"><s:if
		test="#session.abbccuser.grade==00">
		未开通</s:if><s:else>
		<a
			href="http://${sessionScope.abbccuser.username}.${sessionScope.abbccuser.domain}"
			target="blank">访问</a>
	</s:else></span></dd>
	<dd>绑定域名：<span class="orange"><s:if
		test="#session.abbccuser.grade==00">
		未绑定
			</s:if><s:elseif test="#request.bind!=null"><a href="<s:property value="%{#request.bind.address}" />" target="_blank">访问</a>
	</s:elseif></span></dd>
	<dd class="more-btn "><a href="#" class="lnk more-account"
		id="more-account">更多</a></dd>
</dl>
<div id="more-account-box">
<div class="more-account-box clr" id="account-info-main">
<div id="hideDiv">
<dl>
	<dd class="myweb">我的网站<s:if test="#session.abbccuser.grade==00">[<a
			href="/user/upgrade/show" target="main">升级</a>]</s:if><s:else></s:else></dd>
	<dd>免费网站：<span class="orange"><a
		href="http://${sessionScope.abbccuser.domain}/site/${sessionScope.abbccuser.username}"
		target="blank">访问</a></span></dd>
	<dd>旺铺网站：<span class="orange"><s:if
		test="#session.abbccuser.grade==00">
		未开通</s:if><s:else>
		<a
			href="http://${sessionScope.abbccuser.username}.${sessionScope.abbccuser.domain}"
			target="blank">访问</a>
	</s:else></span></dd>
	<dd>绑定域名：<span class="orange"><s:if
		test="#session.abbccuser.grade==00">
		未绑定
			</s:if><s:elseif test="#request.bind!=null">
		<s:property value="%{#request.bind.address}" />
	</s:elseif></span></dd>
</dl>
<div class="more-btns "><a href="#"
	class="lnk more-account-close" id="more-account-close">隐藏</a></div>
</div>
<div class="account-box" id="account-box">
<div id="alipayInfo"><span>服务时间：
</span><a href="javascript:">
<s:date name="%{#request.payUser.startTime}" format="yyyy-MM-dd" />
--
<s:date name="%{#request.payUser.endTime}" format="yyyy-MM-dd" /></a>
<span>，距离到期时间还有</span><font color="red"><s:property value="%{#request.howdate}" /></font>天
</div>
</div>
</div>
<div class="account-box-b"></div>
</div>
</div>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");

	java.util.Date currentTime = new java.util.Date();//得到当前系统时间
	com.abbcc.util.DateUtil du = new com.abbcc.util.DateUtil();

	String str_date1 = formatter.format(currentTime); //将日期时间格式化
	String str_date2 = du.getWeek(); //得到星期几
%>
<div id="work-myinfo-r" class="work-myinfo-r">

<ul>
<li>咨询、建议：</li>
	<li><a target="blank" href="tencent://message/?uin=83503261&amp;Site=www.tair.com.cn&amp;Menu=yes"><img height="29" border="0" width="68" alt="请留言" src="http://wpa.qq.com/pa?p=1:83503261:6"></a></li>
</ul>


</div>
</div>
</div>
</div>
<script language="javascript">
$(document).ready(function(){
	//播放速度
	var speed = 5000;
	//控制范围，符合jQuery路径即可
	var block = '.sUl li';
	//需要显示的内容条目数
	var eq = 1;
	var h = 0;
	if($(block).length > eq){//如果内容数目大于需要滚动的数目，开始滚动！
		//隐藏除了第一个的其它所有节点
		$(block).slice(eq).css('display','none');
		//播放开始
		h = setInterval('scrollContent("'+block+'",'+eq+',2)',speed);
		$('#siteBulletin').mouseout(function(){
			h = setInterval('scrollContent("'+block+'",'+eq+',2)',speed);
		});
		$('#siteBulletin').mouseover(function(){
			clearInterval(h);
		});
	}
	
});
function scrollContent(block,eq,type){
		//获取第节点
	    var firstNode = $(block);
	    //动画效果
	    switch(type){
	    	case 1:
	    		animation_out = {height:'hide'};
	    		animation_in = {height:'show'};
	    		break;
	    	case 2:
	    		animation_out = {opacity:'hide'};
	    		animation_in = {opacity:'show'};
	    		break;
	    	case 3:
	    		animation_out = {height:'hide',opacity:'hide'};
	    		animation_in = {height:'show',opacity:'show'};
	    		break;
	    	default:'';
	    }
	    //开始动画
	    firstNode.eq(0).animate(animation_out,1000,function(){//隐藏
	    	//克隆.追加到最后.隐藏
	        $(this).clone().appendTo($(this).parent()).css('display','none');
	        //显示第二个节点内容
	    	firstNode.eq(eq).removeAttr('style').animate(animation_in,1000);
	    	//删除第一个节点内容
	        $(this).remove();
	    });
}
</script>