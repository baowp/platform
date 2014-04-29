<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="<s:url value='/user/css/validation.css'/>">
<link href="/user/account/style/code.css" rel="stylesheet"
	type="text/css" />
</head>

<body>
<s:if test="result!=''&&result!=null">
		<div id="warning" style="DISPLAY: none">${result}</div>
		<script type="text/javascript">
		window.onload   =   new   function()
		{
			parent.makeState($("#warning").html());
		}

		</script>
</s:if>
<div id="mn">
<div class="top">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="16" height="36" background="/user/account/images/left.jpg">&nbsp;</td>
		<td height="36" align="left"
			background="/user/account/images/center.jpg" class="font01">密码保护(${pwdQuestion==null||pwdQuestion==''?'未设置':'已设置'})</td>
		<td width="109" height="36"
			background="/user/account/images/right.jpg">&nbsp;</td>
	</tr>
</table>
</div>
<div class="tan">
<div class="tan_left">
<ul>
	<li>
	<h3>密码保护建议</h3>
	</li>
	<s:if test="%{pwdQuestion!=null}">
		<li><span class="font02">■</span> 请尽量填写您记得的个人资料</li>
		<li><span class="font02">■</span> 您只设置了密保问题，需要设置密保后才能帐号保护</li>
		<li><span class="font02">■</span> 设置问题后可以用于找回密码和设置其他密保手段</li>
		<li><span class="font02">■</span> 填写的资料越全，密保的可能性越安全</li>
	</s:if>
	<s:else>
		<li><span class="font02">■</span> 您的密码保护尚未填写</li>
	</s:else>
</ul>
</div>
<div class="tan_right">
<div class="y">请设置密码保护</div>
<div class="u"><s:form namespace="/user/account/password"
	action="%{pwdQuestion==null||pwdQuestion==''?'savePasswordProtection':'updatePasswordProtection'}"
	method="post">
	<s:hidden name="id" value="%{userId}"></s:hidden>
	<table width="100%" border="0" cellpadding="0">
		<s:if test="%{pwdQuestion!=null&&pwdQuestion!=''}">
			<tr>
				<td width="29%" height="32">原密保问题</td>
				<td width="71%" height="32" align="left"><s:textfield
					name="pwdQuestion" id="textfield" readonly="true" /></td>
			</tr>
			<tr>
				<td height="32">原密码答案</td>
				<td height="32" align="left"><s:textfield name="pwdAnswer"
					id="textfield2" value="" /><span class="errorSpan">${errors.pwdAnswer[0]}</span></td>
			</tr>
		</s:if>
		<tr>
			<td height="32">新密保问题</td>
			<td height="32" align="left"><s:textfield name="newPwdQuestion"
				id="textfield3" /><span class="errorSpan">${errors.pwdQuestion[0]}${errors.newPwdQuestion[0]}</span></td>
		</tr>
		<tr>
			<td height="32">新密码答案</td>
			<td height="32" align="left"><s:textfield name="pwdAnswer1"
				id="textfield4" /><span class="errorSpan">${errors.pwdAnswer[0]}</span></td>
		</tr>
		<tr>
			<td height="32">请再次输入</td>
			<td height="32" align="left"><s:textfield name="pwdAnswer2"
				id="textfield5" /><span class="errorSpan">${errors.pwdAnswer2[0]}</span></td>
		</tr>
		<tr>
			<td height="32">&nbsp;</td>
			<td height="32" align="left"><input type="submit" name="button"
				id="button" onclick="javascript:return confirm('确定提交吗？')" value="提交" />
			<input type="button" value="取消" onclick="javascript:history.go(-1);" /></td>
		</tr>
	</table>
</s:form></div>
</div>
</div>
</div>
</body>
</html>