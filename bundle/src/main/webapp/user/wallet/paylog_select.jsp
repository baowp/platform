<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<title>abbcc</title>
</head>
<body style="padding: 10px;">
<div id="body">
<table class="listTable">
<thead>
	<tr><th>付费银行</th><th>付费金额</th><th>单据号</th><th>开始时间</th>
		<th>截至时间</th><th>操作</th></tr></thead>
		<tbody>
	<s:iterator value="pageList.items">
		<tr>
			<td><s:property value="bank"/></td>
			<td><s:property value="amount"/></td>
			<td><s:property value="documentId"/></td>
			<td><s:date name="startTime" format="yyyy-MM-dd"/></td>
			<td><s:date name="endTime" format="yyyy-MM-dd"/></td>
			<td><a href="javascript:void(0)" onclick="choosePaylog('${paylogId}','${documentId}')">选择</a></td>
		</tr>
	</s:iterator>
	</tbody>
	<tfoot><tr><th colspan="6"><s:include value="/common/page.jsp"/></th></tr></tfoot>
</table>
</div>
</body>
</html>