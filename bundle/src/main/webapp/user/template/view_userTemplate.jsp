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
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#submit").click(function() {
				$("#submit").disable();
			});
			$("#returnbtn").click(function() {
				window.history.back();
			});
		});
</script>
<title>添加网站</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改模板内容</li>
	<a href="javascript:window.history.back()" class="return">返回</a>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/user" action="updateTemplate" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="siteId" />
		<s:hidden name="id" />
		<s:hidden name="pageType" value="123"/>
		<tr>
			<td class="left_title_1">模板名称</td>
			<td><s:textfield name="name" /></td>
			<td class="left_title_1">页面url</td>
			<td><s:textfield name="pageName" /></td>
			<td class="left_title_1">用户可见</td>
			<td><s:select list="#{'0':'否','1':'是'}" name="userdisplay"
				value="userdisplay">分页类型：</s:select></td>

		</tr>
		<tr>
			<td width="70px" class="left_title_1" colspan="6">模板内容</td>
		</tr>
		<tr>
			<td colspan="6"><s:textarea name="content" cols="100" rows="15" /><span
				class="errorSpan">${errors.content[0]}</span></td>
		</tr>

		<tr>
			<td class="left_title_2" style="text-align: left" colspan="6">

			<button class="submit" id="returnbtn" />
			提交
			</button>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
