<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<title>发送会员升级邀请</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">升级邀请</li>
	<li>发送会员升级邀请</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/admin" action="sendUpgradeInvite" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="id"/>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">接收人</span>
			</td>
			<td><s:textfield name="email"/>（会员名：<s:property value="username" />）
			</td>
		</tr>
		<tr>
			<td class="left_title_1"><span class="left-title">邮件内容</span>
			</td>
			<td><s:textarea name="inviteContent" cssClass="ckeditor">
			</s:textarea> <span class="errorSpan">${errors.inviteContent[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left"><input
				type="submit" name="save" id="submit" value="提交" />
			<button onclick="window.location='viewSubmembers'">返回</button>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
