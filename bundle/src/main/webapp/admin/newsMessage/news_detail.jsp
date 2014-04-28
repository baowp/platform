<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新闻详细</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#goback").click(function(){
				window.history.back();
			});
		});
</script>
</head>
<body>
<s:form namespace="/admin" action="searchSupply" method="post"
	id="form1" name="form1">
	<div id="nav" style="position: relative">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">新闻详细</li>
		<li><s:property value="title" /></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<tbody>
		<thead>
			<tr>
				<th class="header">新闻栏目</th>
				<th class="header">添加时间</th>
				<th class="header">添加企业</th>
				<th class="header">添加用户</th>
			</tr>
		</thead>
			<tr>
				<td><s:property value="newsCategory().name" /></td>
				<td><s:property value="addTimeString()" /></td>
				<td><s:property value="enterprise().name" /></td>
				<td><s:property value="addUser().username" /></td>
			</tr>
			<tr>
				<td colspan="4">内容</td>
			</tr>
			<tr>
				<td colspan="4" style="word-wrap:break-word;width:100%;overflow:auto"><s:property escape="false" value="content" /></td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="4" class="tb_search">
				<button type="button" id="goback" class="return">返回</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
