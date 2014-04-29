<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/user/style/uce.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="u">
<div id="to">
<div class="top_left"><img src="/user/images/logo.jpg" width="163"
	height="47" /></div>
<div class="top_right"><span class="font2"><a
	href="http://51archetype.com">返回首页</a></span><br />
<span class="font2">如遇问题请拨打 </span><span class="font01">0579-87171989</span></div>
</div>
<div id="cont">
<div class="h_nav">用户注册</div>

<div id="c_content">
<div class="c_top">
<div class="c_01"><img src="/user/images/Head1239.gif" width="96"
	height="96" /></div>
<div class="c_02">
<h3 class="h3">恭喜你，注册成功!</h3>
<h4 class="h4">您登录的用户名为<s:property value="name" />
，您可使用此用户名登陆abbcc网。</h4>
<h4 class="h4"><strong>友情提示：</strong>我们已将验证信发送到你的电子邮箱，<a
	href="mailto:<s:property value="email"/>"><s:property
	value="email" /></a> 收信，确认后即可验证成功！</h4>
</div>
</div>
<div class="c_center"></div>
<div class="c_top">
<div class="c_01"><img src="/user/images/icon_mail.gif" width="53"
	height="26" /></div>
<div class="c_02">
<h3 class="h3"><strong>验证邮箱：</strong></h3>
<h4 class="h4"><a href="<s:property value="mailPath(email)"/>"><img src="/user/images/yanzheng.jpg"
	width="158" height="38" border="0" /></a></h4>
</div>
</div>
</div>
</div>
<div id="bottom"></div>
<div id="footer">
<div class="footer_center">
<p><a href="http://www.ykit.net/index.html">永康市东方五金电子商务有限公司版权所有2000-2009</a>
| <a href="http://www.ykit.net/index.html">著作权与商标声明</a> | <a
	href="mailto:crm@ykit.net">E-mail:crm@ykit.net</a> | <a
	href="http://net.china.com.cn/index.htm">互联网违法和不良信息举报 |<br />
<br />
</a><a href="http://www.ykit.net/index.html" target="_blank">站点导航: 首页</a>&nbsp;<a
	href="http://www.ykit.net/website1.html" target="_blank">产品方案</a>&nbsp;<a
	href=" http://www.ykit.net/news.html" target="_blank">资讯中心</a>&nbsp;<a
	href="http://www.ykit.net/help.html" target="_blank">客户服务</a> <a
	href="http://www.ykit.net/seojs.html">建站常识&nbsp;|</a> <a
	href="http://www.ykit.net/values.html" target="_blank">企业文化</a>&nbsp;|
<a href="http://www.ykit.net/about1.html" target="_blank">关于我们</a>&nbsp;|
<a href="http://blog.sina.com.cn/dfwjw" target="_blank">企业博客&nbsp;|</a>
<a
	href="http://www.miibeian.gov.cn/state/outPortal/loginPortal.action;jsessionid=w2RQM7nCpyGHrJz9Szm16mW3psLbyYzZKlYfS2TjJbgnNzGnXFXQ!177479300">经营许可证编号：浙B2-20050229
</a><a title="站长统计"
	href="http://www.cnzz.com/stat/website.php?web_id=1132593"
	target="_blank"><img border="0" hspace="0"
	src="http://icon.cnzz.com/pic.gif" /></a></p>
</div>
</div>
</div>
</body>
</html>
