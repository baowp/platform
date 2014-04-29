<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="/user/abbcc/css/common.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery/colorize.js"></script>
<script type="text/javascript">
function setDisplay(obj) {
	var id = $(obj).parents("tr").data("id");
	var value = $(obj).data("value");
	var url = "/rest/group/analysis/display";
	$.ajax({
		type : "post",
		url : url,
		data : {
			id : id,
			display : value
		},
		success : function() {
			if (value == 0) {
				$(obj).html('隐藏').data("value", "1");
			} else {
				$(obj).html('显示').data("value", "0");
			}
		}
	});
}
</script>
</head>
<body>
	<div class="fv">
		<input type="button"
			onclick="window.location.href='/rest/group/analysis/editPage'"
			value="添加浏览统计" />&nbsp;
	</div>
	<table class="listTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>类型</th>
				<th>显示</th>
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
				<tr align="center" data-id="${item.analysisId }">
					<td>${st.count}</td>
					<td>${item.type.toString() }</td>
					<td>
						<c:if test="${item.display eq 0}">
							<a href="javascript:void(0)" data-value="1" onclick="javascript:setDisplay(this)">隐藏</a>
						</c:if> <c:if test="${item.display eq 1}">
							<a href="javascript:void(0)" data-value="0" onclick="javascript:setDisplay(this)">显示</a>
						</c:if> 
					</td>
					<td>
						<a href="editPage?id=${item.analysisId}">修改</a>|<a href="delete?id=${item.analysisId}" onclick="javascript:return del()">删除</a>
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
