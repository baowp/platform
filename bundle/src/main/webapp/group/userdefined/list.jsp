<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义模块设置</title>
<link rel="stylesheet" type="text/css" href="/user/abbcc/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.min.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery/colorize.js"></script>
</head>
<body>
	<div class="fv">
		<input type="button"
			onclick="window.location.href='/rest/group/userdefined/editPage'"
			value="添加自定义模块" />&nbsp;
	</div>
	<table class="listTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty resultList }">
				<tr class="s">
					<td align="center" colspan="3">没有相关数据!</td>
				</tr>
			</c:if>
			<c:forEach var="item" items="${resultList}" varStatus="st">
				<tr align="center">
					<td>${st.count}</td>
					<td>${item[1] }</td>
					<td>
						<a href="editPage?id=${item[0]}">修改</a>|<a href="delete?id=${item[0]}" onclick="javascript:return del()">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="3"></th>
			</tr>
		</tfoot>
	</table>
</body>
</html>
