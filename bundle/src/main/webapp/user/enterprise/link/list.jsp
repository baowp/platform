<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接管理</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript">
	function stepSort(obj, type) {
		var $row = $(obj).parents("tr[linkId]");
		$("input[name=sourceCate]").val($row.attr("linkId"));
		$("input[name=sourceSort]").val($row.attr("sort"));
		$("input[name=stepType]").val(type);
		$("#form1").submit();
	}
</script>
</head>
<f:form action="step" id="form1">
		<!-- 用于调顺序-->
		<input type="hidden" name="sourceCate" />
		<input type="hidden" name="sourceSort" />
		<input type="hidden" name="stepType" />
</f:form>
<body>
	<div class="fv">
		<input type="button"
			onclick="window.location.href='/rest/user/enterprise/link/addPage'"
			value="添加友情链接" />&nbsp;
	</div>
	<table class="listTable">
		<thead>
			<tr>
				<th>链接名称</th>
				<th>链接地址</th>
				<th>排序</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty resultList }">
				<tr>
					<td align="center" colspan="4">没有相关数据!</td>
				</tr>
			</c:if>
			<c:set var="listLen" value="${resultList.size()}" scope="page"></c:set>
			<c:forEach var="item" items="${resultList}">
				<tr linkId="${item.linkId }" sort="${item.lorder }">
					<td valign="middle" align="center">${item.name }</td>
					<td valign="middle" align="center">${item.url }</td>
					<td align="center">
						<c:choose>
								<c:when test="${item.linkId eq resultList[0].linkId }">上移 |</c:when>
								<c:otherwise>
									<a href=javascript:void(0) onclick=stepSort(this,2)>上移</a> |
								</c:otherwise>
						</c:choose> 
						<c:choose>
								<c:when test="${item.linkId eq resultList[listLen-1].linkId }">下移</c:when>
								<c:otherwise>
									<a href=javascript:void(0) onclick=stepSort(this,1)>下移</a>
								</c:otherwise>
						</c:choose>
					</td>
					<td valign="middle" align="center">
						<a href="editPage?id=${item.linkId }">修改</a>|<a
						href="delete?id=${item.linkId }"
						onclick="javascript:return del()">删除</a></tr>
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
