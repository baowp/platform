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
<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<title>修改网站</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改网站</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/admin" action="updateUserSite" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="usersiteId"></s:hidden>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">用户名</span>
			</td>
			<td><s:textfield name="username" /><span
				class="errorSpan">${errors.username[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">域名</span>
			</td>
			<td><s:textfield name="domain" /><span
				class="errorSpan">${errors.domain[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">所属服务器</span>
			</td>
			<td><s:select list="%{#session.webservers}"
				name="serverId" listKey="serverId" listValue="name" value="serverId"/><span
				class="errorSpan">${errors.serverId[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">网站部署路径</span>
			</td>
			<td><s:textfield name="folder" /><span
				class="errorSpan">${errors.folder[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left">
			<button type="submit" class="submit"/>提交</button>
			<button onclick="window.location='listUserSite'" class="return">返回</button>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
