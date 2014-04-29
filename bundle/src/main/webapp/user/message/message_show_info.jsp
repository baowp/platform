<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/textd.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.mouse.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.draggable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.position.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.resizable.min.js"></script>
<script type="text/javascript" src="/js/jquery/ui/jquery.ui.dialog.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link href="/css/dialog/jq_aero.css" rel="stylesheet" />
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<script type="text/javascript">
	$().ready(function() {

		$('#button1').click(function() {
			$("#content1").val('');
			$("#receive").val($("#author").attr("ah"));
			$("#title1").val("re:" + $("#title").attr("tl"));
			$("#dialog").dialog("open");
			$("#dialog").dialog({
				modal : true,
				width : 380,
				title : "消息回复",
				autoOpen : true,
				buttons : {
					确定 : function() {
						if ($("#title1").val() == '') {
							alert('请输入标题!')
							return false;
						} else {
							$.ajax({
								url : "/user/message/messagerecv",
								dataType : "json",
								async : false, //不进行异步操作
								data : {
									username : $("#receive").val(),
									content : $("#content1").val(),
									title : $("#title1").val()
								},
								success : function(data) {
									alert("留言成功!");
									$("#dialog").dialog("close");
								}
							})
						}

					}
				}
			})
		});
	})
</script>
<title>查看回复消息</title>

</head>

<body>
<div id="cpcontainer" class="container">
<div class="top">
<h3>查看留言</h3>
</div>

<table width="100%" height="363" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td colspan="2" align="left" class="font01">
		<div class="fv"><s:if test="%{type!='05'}">
			<input type="button"
				onclick="javascript:window.location.href='/user/message/messageentry';"
				value="返回" />
		</s:if><s:else>
			<input type="button"
				onclick="javascript:window.location.href='/user/message/messagevisitor';"
				value="返回" />
		</s:else><s:if test="%{#request.pageType!='send'&&type=='03'}">
			<input type="button" id="button1" value="回复" />
		</s:if></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="left" bgcolor="#F5FBFE" class="font01">
		<div class="f">
		<ul>
			<li>发件人：<span id="author" ah=${user.username }>${user.username
			}</span></li>
			<li>时 &nbsp;间：<span><s:date name="addTime"
				format="yyyy-MM-dd hh:mm:ss" /></span></li>
			<li>标 &nbsp;题：<span id="title" tl=${title}>${title}</span></li>
			<li>联系电话：<span id="title"><s:property
				value="#request.message.fromPhone"></s:property></span></li>
			<li>电子邮箱：<span id="title"}><s:property
				value="#request.message.fromEmail"></s:property></span></li>
		</ul>
		</div>
		</td>
	</tr>


	<tr>
		<td width="99%" align="left" valign="top" class="font02_t">
		<div id="gt"><s:textarea dir="ltr" tabindex="0" wrap="soft"
			readonly="true" name="text" id="source"
			cssStyle="-moz-box-sizing: border-box; overflow-y: hidden; overflow-x: auto; padding-bottom: 32px;"
			value="%{content}"></s:textarea></div>
		</td>
		<td width="1%">&nbsp;</td>
	</tr>

</table>
</div>
<s:form namespace="/user/message" action="messagerecv" id="dialog"
	cssStyle="display:none;" method="get" onsubmit="return checkValue()">
	<table>
		<tr>
			<td><s:textfield label="接收人" theme="xhtml" name="username"
				id="receive" readonly="true" /></td>
		</tr>
		<tr>
			<td><s:textfield label="标题" theme="xhtml" id="title1" /></td>
		</tr>
		<tr>
			<td><s:textarea name="content" id="content1" cols="20" value=""
				label="内容" theme="xhtml"></s:textarea></td>
		</tr>
	</table>
</s:form>
</body>
</html>
