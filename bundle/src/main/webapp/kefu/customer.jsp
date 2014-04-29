<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客服中心</title>
<link href="style/css.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery.js" type="text/javascript"></script>
<script src="js/help_center.js" type="text/javascript"></script>
</head>
<body>
<s:include value="action/header.jsp"></s:include>
<div class="contain">
<div class="left">
<div class="sm">自助服务</div>
<div class="sm_c">
<div class="hk"><a
	href="http://51archetype.com/user/account/account_forgot_page.jsp?pageType=1"
	target="_blank">找回用户名</a></div>
<div class="hk"><a
	href="h51archetype.comcc.net/user/account/account_forgot_page.jsp?pageType=2"
	target="_blank">找回密码</a></div>
<div class="hk"><a h51archetype.com://51archetype.com/user/platform/index.jsp"
	target="_blank">修改联系方式</a></div>
<div class="hk"><a href="http://www.ykit.net/Contact1.html"
	target="_blank">东方五金在线</a></div>
<div class="hk51archetype.com="http://51archetype.com/user/platform/index.jsp"
	target="_blank">修改服务授权</a></div>
<div class="hk"><a href="http://im.qq.com/qq/2010/standard/"
	target="_blank">在线安装QQ</a></div>
</div>
<div class="sm">菜单分类</div>
<div id="tree1"><s:action name="treeMenu" namespace="/kefu"
	executeResult="true"></s:action></div>
<div class="contract">
<table width="100%" height="147" border="0" cellpadding="0">
	<tr>
		<td height="12" colspan="3" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="12%" align="center"><img src="images/jtc.png"
			width="12" height="12" /></td>
		<td width="32%"><strong>客服热线：</strong></td>
		<td width="56%" align="left">0579-87171989</td>
	</tr>
	<tr>
		<td align="center">&nbsp;</td>
		<td><strong>每工作日：</strong></td>
		<td align="left">8：30-17：30</td>
	</tr>
	<tr>
		<td align="center"><img src="images/jtc.png" width="12"
			height="12" /></td>
		<td><strong>传真号码：</strong></td>
		<td align="left">0571-88157872-888</td>
	</tr>
	<tr>
		<td align="center">&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
</div>
</div>
<div class="right" style="height: 361px"><iframe id="mainFrame"
	style="OVERFLOW: visible" height="100%" src="customer_right.html"
	frameBorder=0 width="100%" name=main scrolling=yes></iframe></div>
</div>
<div id="copryright">
<ul class="ij">
	<li>运营服务:0579-87171989&nbsp;&nbsp; 83840778&nbsp;&nbsp; 88026855</li>
	<li>技术支持:0579-83840669</li>
	<li>传真：0579-87173500</li>
</ul>
<ul class="ik">
	<li>东方五金网版权所有 2000-2011</li>
	<li>E-mail: dfwj@ykit.net</li>
	<li>互联网违法和不良信息举报</li>
</ul>
</div>
</body>
</html>