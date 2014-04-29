<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌管理列表</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
</head>
<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="fv">
<input type="button" onclick="window.location.href='add'" value="添加品牌"/></div>
<table class="listTable">
	<thead><tr><th>品牌名称</th><th>行业</th><th>操作</th></tr></thead>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="3">没有相关数据!</td></tr>
</s:if>
	<s:iterator value="pageList.items" status="st">
		<tr>
			<td>${name}</td>
			<s:action name="syscodePath" namespace="/user/product/syscode" var="sys">
					<s:param name="syscodeId" value="industry"/>
			</s:action>
			<td><s:property value="#sys.syspath"/></td>
			<td align="center"><a href="edit?id=${brandId}">修改</a>|
				<a href="remove?id=${brandId}">删除</a>
			</td>
		</tr>
	</s:iterator>
	<tfoot>
		<tr><th colspan="9"><s:include value="/common/page.jsp"/></th></tr>
	</tfoot>
</table>
</body>
</html>