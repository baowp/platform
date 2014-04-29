<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.ui-widget-content a {
	color: blue;
}
</style>
<title>abbcc</title>
</head>
<body>
<div>
	<h4>设置MSN临时会话，请按以下步骤添加代码</h4>
	<ol>
		<li>进入<a href="http://settings.messenger.live.com/applications/Default.aspx" 
			target="_blank">Windows Live</a>的网络设置页面，用自己的ID号登陆；</li>
		<li>进入“网络设置”，钩选“允许网络上的人查看您是否联机并给您发送消息”，保存;</li>
		<li>进入“创建HTML”，选择“状态图标”，并复制您的HTML代码至以下文本域;</li>
	</ol>
	<div>
		<s:textarea name="gaimMsnArea" cols="60" rows="7"/>
		<s:submit value="确定" onclick="gaim.msnAreaSubmit()"/>
	</div>
	<a href="#" onclick="gaim.removeMsn()">删除MSN会话</a>
</div>
</body>
</html>