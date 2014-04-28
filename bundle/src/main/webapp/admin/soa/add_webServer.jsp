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
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<title>添加服务器</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">添加服务器</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/admin" action="addWebServer" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="state" value="01"></s:hidden>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">服务器名称</span>
			</td>
			<td><s:textfield name="name" /><span
				class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">服务器ip</span>
			</td>
			<td><s:textfield name="ip" /><span
				class="errorSpan">${errors.ip[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">ftp用户名</span>
			</td>
			<td><s:textfield name="ftpUsername" /><span
				class="errorSpan">${errors.ftpUsername[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">ftp密码</span>
			</td>
			<td><s:textfield name="ftpPassword" /><span
				class="errorSpan">${errors.ftpPassword[0]}</span></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">webservice</span>
			</td>
			<td><s:textfield name="webservice" /><span
				class="errorSpan">${errors.webservice[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left">
			<button type="submit" class="submit"/>提交</button>
			<button onclick="window.location='listWebServer'" class="return">返回</button>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
