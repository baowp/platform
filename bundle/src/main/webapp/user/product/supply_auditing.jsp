<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核中的供求信息</title>
<s:include value="supply_style.jsp"/>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="listTab">
	<dl><dt></dt><a href="published">已发布上网</a><dt></dt></dl>
	<dl class="focus"><dt></dt>审核中<dt></dt></dl>
	<dl><dt></dt><a href="unapprove">审核未通过</a><dt></dt></dl>
	<dl><dt></dt><a href="expired">已过期</a><dt></dt></dl>
</div>
<table class="listTable">
<thead><tr><th width="">标题</th><th width="">发布时间</th><th>结束时间</th></tr></thead>
<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="3">没有相关数据!</td></tr>
</s:if>
<s:iterator value="pageList.items">
	<tr>
		<td>[<s:property value="type" />]<s:property value="title"/></td>
		<td  align="center"><s:date name="startTime" format="yyyy-MM-dd hh:mm:ss"/></td>
		<td  align="center"><s:date name="endTime" format="yyyy-MM-dd hh:mm:ss"/></td>
	</tr>
</s:iterator>
	<tfoot>
		<tr><th colspan="9"><s:include value="/common/page.jsp"/></th></tr>
	</tfoot>
</table>
</body>
</html>
