<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="style/css.css" rel="stylesheet" type="text/css" />
<link href="style/div.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="/css/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery/query.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/search.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/index.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="/css/index.css">
<script type="text/javascript">
window.onload   =   new   function() {
	var url = location.search;
	var path = $.query.get('url');
	if(path!=''){
		window.location.href=path;
	}
}
</script>
<script type="text/JavaScript">
<!--
function search2(){
	$("#form1").submit()
}

//-->
</script>
</head>

<body>
<div id="m">
<div id="header">
<div class="header_left">
<ul>
	<li><a href="javascript:setHomepage()">设置为首页</a></li>
</ul>
</div>
<div class="header_right">
<ul>
	<s:if test="#session.loginuserid!=null">
		<li><a href="user/platform/index.jsp">用户中心</a></li>
		<li><a href="<s:url value="/user/userLogout"/>?pageType='index'"
			onClick="return logout();">注销</a></li>
	</s:if>
	<s:else>
		<li><a href="user/login.jsp">登陆</a></li>
		<li><a href="<s:url value="/user/reg.jsp"/>">注册</a></li>
	</s:else>
	<li><a href="javascript:addPage()">添加收藏</a></li>
</ul>
</div>
</div>
<div id="center">
<div class="logo"><img src="images/logo.jpg" width="224"
	height="64" border="0"></div>
<div class="searc">
<div class="searc_top">
<ul>
	<li><a href="javascript:formSubmit('news')">资讯</a></li>
	<li>产品</li>
	<li><a href="javascript:formSubmit('enterprise')">公司</a></li>
	<li><a href="javascript:formSubmit('supply')">求购</a></li>
	<li>&nbsp</li>
	<li>&nbsp</li>
	<li>&nbsp</li>
	<li>&nbsp</li>
	<li>
	<div class="procurement-top"><a title="风云排行榜" href="/ranking/view">风云排行榜</a></div>
	</li>
</ul>
</div>
<div class="searc_center"><s:form
	action="byNameSearch" namespace="/" id="form1">
	<s:hidden name="entId" id="entId" />
	<table width="100%" border="0" cellpadding="0">
		<tr>
			<td width="81%"><s:textfield name="entName" id="entName" /></td>
			<td width="19%"><a href="#" onclick="search2()"><input
				type="button" id="button1" value="ABBCC搜索" /></a></td>
		</tr>
	</table>
</s:form></div>
<!-- <div class="top">热门搜索</div> -->
<div class="searc_bottom">
<ul id="nav" class="nav">
	<li id="info_1"><a href="http://11diban.com/"><span>&nbsp;</span>地板网</a></li>
	<li id="info_2"><a href="http://11door.com/" target="_blank"><span>&nbsp;</span>上门网</a></li>
	<li id="info_3"><a href="http://easthardware.com/"
		target="_blank"><span>&nbsp;</span>五金网</a></li>
	<li id="info_4"><a href="http://www.zjtcw.cn/" target="_blank"><span>&nbsp;</span>陶瓷网</a></li>
	<li id="info_5"><a href="http://easthardware.com/"
		target="_blank"><span>&nbsp;</span>机电网</a></li>
	<li id="info_6"><a href="#" target="_blank"><span>&nbsp;</span>暂无</a></li>
	<li id="info_7"><a href="#" target="_blank"><span>&nbsp;</span>暂无</a></li>
</ul>
</div>
</div>
</div>
<div id="footer">
<ul>
	<li><span><a href="#" target="_parent">abbcc服务</a></span>|<span><a
		href="#" target="_parent">联系我们</a></span>|<span><a href="#"
		target="_parent">English</a></span>|<span><a href="#" target="_parent">About
	us</a></span></li>
	<li>经营许可证编号：<span><a href="#" target="_parent">浙B2-20100285</a></span><script src="http://s20.cnzz.com/stat.php?id=3045281&web_id=3045281&show=pic1" language="JavaScript"></script></li>
</ul>
</div>
</div>
</body>
<script type="text/javascript" src="/js/unIframe.js"></script>
</html>
