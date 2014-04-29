<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seo优化设置</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery/colorize.js"/>"></script>
</head>
<body>
	<div class="fv">
		<label>当前目录：</label> <a href="list">一级菜单</a>&gt;
		<c:forEach var="route" items="${navigatorRoute}">
			<a href="list?navigatorId=${route.key}">${route.value}</a>&gt;
		</c:forEach>
	</div>
	<table class="listTable">
		<thead>
			<tr>
				<th>页面路径</th>
				<th>导航菜单名称</th>
				<th>子菜单</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty navigatorList }">
				<tr class="s">
					<td align="center" colspan="4">没有相关数据!</td>
				</tr>
			</c:if>
			<c:forEach var="item" items="${navigatorList }">
				<tr>
					<td valign="middle" align="center">${item.page }</td>
					<td valign="middle" align="center">${item.name }</td>
					<td valign="middle" align="center">
						<c:if test="${item.childSize > 0}">
							<a href="list?navigatorId=${item.navigatorId}">下属菜单(${item.childSize })</a>
						</c:if>
					</td>
					<td valign="middle" align="center"><a
						href="editPage?page=${item.page}&name=${item.name}&navigatorId=${item.navigatorId}">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="4"></th>
			</tr>
		</tfoot>
	</table>
</body>
</html>
