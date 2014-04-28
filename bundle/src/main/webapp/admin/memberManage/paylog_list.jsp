<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>付费管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMember?searchWord="+$("#searchWord").val();
			});
		});
</script>
</head>
<body>
<s:form namespace="/admin" action="viewMember" method="post" id="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">付费记录</li>
		<li>用户“<s:property value="user.username" />”的付费记录</li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th class="header" name="payDate">付款日期</th>
				<th class="header" name="startEnd">付费起止</th>
				<th class="header" name="type">付款类型</th>
				<th class="header" name="bank">银行</th>
				<th class="header" name="bankAccount">付款账号</th>
				<th class="header" name="amount">付款金额</th>
				<th class="header" name="documentId">付款单号</th>
				<th class="header" name="state">状态</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="paylog" value="payLogs">
				<tr>
					<td><s:property value="payDate()" /></td>
					<td><s:property value="payStart()" />至<s:property value="payEnd()" /></td>
					<td><s:property value="typeName()" /></td>
					<td><s:property value="bankName()" /></td>
					<td><s:property value="bankAccount()" /></td>
					<td><s:property value="amount" /></td>
					<td><s:property value="documentId" /></td>
					<td><s:property value="stateName()" /></td>
					<td>操作</td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="9" class="tb_search">
				<s:url id="url" action= "viewAddPaylog">
						<s:param name="payuserId">
							<s:property value="payuserId" />
						</s:param>
					</s:url> <s:a href="%{url}">添加付费记录</s:a>&nbsp;
					<s:a href="payMember">返回</s:a> 
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
