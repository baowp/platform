<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>供求详细</title>
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
		<li id="man_nav_1" class="bg_image_onclick">供求详细</li>
		<li></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<tbody>
			<tr>
				<td class="tdblue">供求名称</td>
				<td><s:property value="title" /></td>
				<td class="tdblue">供求分类</td>
				<td><s:property value="supplyType" /></td>
			</tr>
			<tr>
				<td class="tdblue">供求企业</td>
				<td><s:property value="enterprise().name" /></td>
				<td class="tdblue">添加用户</td>
				<td><s:property value="addUser().username" /></td>
			</tr>
			<tr>
				<td class="tdblue">品牌</td>
				<td><s:property value="brand().name" /></td>
				<td class="tdblue">关键字</td>
				<td><s:property value="skey" /></td>
			</tr>
			<tr>
				<td class="tdblue">开始时间</td>
				<td><s:property value="startTimeString()" /></td>
				<td class="tdblue">结束时间</td>
				<td><s:property value="endTimeString()" /></td>
			</tr>
			<tr>
				<td class="tdblue">价格</td>
				<td><s:property value="price" />/<s:property value="unit" /></td>
				<td class="tdblue">联系方式</td>
				<td><s:property value="contact" /></td>
			</tr>
			<tr>
				<td colspan="4">描述</td>
			</tr>
			<tr>
				<td colspan="4"><s:property escape="false" value="wdesc" /></td>
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
