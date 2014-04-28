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
<script language="Javascript" type="text/javascript"
	src="<s:url value="/js/edit_area/edit_area_full.js"/>"></script>
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
		editAreaLoader.init({
			id: "templateContent"	// id of the textarea to transform	
			,start_highlight: true	// if start with highlight
			,allow_resize: "both"
			,allow_toggle: false
			,word_wrap: true
			,language: "en"
			,syntax: "html"	

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
</s:if> <s:form namespace="/admin" action="updateUserTemplate" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="siteId" />
		<s:hidden name="id" />
		<s:hidden name="searchKey" />
		<tr>
			<td class="left_title_1">模板名称</td>
			<td><s:textfield name="name" /></td>
			<td class="left_title_1">页面url</td>
			<td><s:textfield name="pageName" /></td>
			<td class="left_title_1">用户可见</td>
			<td><s:select list="#{'0':'否','1':'是'}" name="userdisplay"
				value="userdisplay">分页类型：</s:select></td>
			<td><s:select list="#{'00':'列表','01':'详细'}" name="contentType"
				value="contentType">模板类型：</s:select></td>

		</tr>
		<tr>
			<td width="70px" class="left_title_1" colspan="7">模板内容</td>
		</tr>
		<tr>
			<td colspan="7"><s:textarea name="content" id="templateContent" style="height: 300px; width: 100%;"/><span
				class="errorSpan">${errors.content[0]}</span></td>
		</tr>

		<tr>
			<td class="left_title_2" style="text-align: left" colspan="7">
<s:submit value="提交"/>
		
			</td>
		</tr>
	</table>
</s:form></div>
</div>
<script type="text/javascript">

</script>
</body>
</html>
