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
<div style="padding-bottom: 20px;">
	<div>
		QQ位置：
		<s:radio list="#{'static':'固定','fixed':'浮动' }" name="qqPosition"/>
	</div>
	<h4>方法一：输入您的QQ号码并确认（用户与您临时会话需加您为好友）</h4>
	<div>
		<s:textfield name="gaimqqField" label="QQ号码" theme="xhtml"/>
		<s:submit value="确定" onclick="gaim.qqFieldSubmit()"/>
		<a href="#" onclick="gaim.removeqq()" class="topicLink">删除QQ会话</a>
	</div>
</div>
<div>
	<h4>方法二：由于QQ升级后提高了临时会话使用的安全性，若不希望加为好友即可会话，
		须到腾讯相应网站(<a href="http://wp.qq.com" target="_blank" class="topicLink">QQ在线状态</a>)提取您的临时会话代码，
		并有多种风格可供选择。请复制代码至以下文本域</h4>
	<div>
		<s:textarea name="gaimqqArea" cols="60" rows="7"/>
		<s:submit value="确定" onclick="gaim.qqAreaSubmit()"/>
	</div>
</div>
</body>
</html>