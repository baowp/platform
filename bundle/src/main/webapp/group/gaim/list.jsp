<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>即时通讯设置</title>
<link rel="stylesheet" type="text/css" href="/user/abbcc/css/common.css">
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.min.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/jquery/colorize.js"></script>
<script type="text/javascript">
	function setDisplay(obj) {
		var id = $(obj).parents("tr").data("id");
		var value = $(obj).data("value");
		var url = "/rest/group/gaim/display";
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
	function stepSort(obj, type) {
		var $row = $(obj).parents("tr:first");
		$("input[name=sourceCate]").val($row.data("id"));
		$("input[name=sourceSort]").val($row.data("sort"));
		$("input[name=stepType]").val(type);
		$("#form1").submit();
	}
</script>
</head>
<body>
	<div class="fv">
		<input type="button"
			onclick="window.location.href='/rest/group/gaim/addPage'"
			value="添加通信信息" />&nbsp;
	</div>
	<f:form action="step" id="form1">
			<!-- 用于调顺序-->
			<input type="hidden" name="sourceCate" />
			<input type="hidden" name="sourceSort" />
			<input type="hidden" name="stepType" />
	</f:form>
	<table class="listTable">
		<thead>
			<tr>
				<th>名称</th>
				<th>账号</th>
				<th>类型</th>
				<th>排序</th>
				<th>显示</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty resultList }">
				<tr class="s">
					<td align="center" colspan="4">没有相关数据!</td>
				</tr>
			</c:if>
			<c:set var="listLen" value="${resultList.size()}" scope="page"></c:set>
			<c:forEach var="item" items="${resultList}">
				<tr align="center" data-id="${item.gaimId }" data-sort="${item.sort }">
					<td>${item.name }</td>
					<td>${item.account }</td>
					<td>${item.type}</td>
					<td><c:choose>
							<c:when test="${item.gaimId eq resultList[0].gaimId }">上移 |</c:when>
							<c:otherwise>
								<a href=javascript:void(0) onclick=stepSort(this,2)>上移</a> |
							</c:otherwise>
						</c:choose>
						 <c:choose>
							<c:when
								test="${item.gaimId eq resultList[listLen-1].gaimId }">下移</c:when>
							<c:otherwise>
								<a href=javascript:void(0) onclick=stepSort(this,1)>下移</a>
							</c:otherwise>
						</c:choose></td>
					<td><c:if test="${item.display eq 0}">
							<a href="javascript:void(0)" data-value="1" onclick="javascript:setDisplay(this)">隐藏</a>
						</c:if> <c:if test="${item.display eq 1}">
							<a href="javascript:void(0)" data-value="0" onclick="javascript:setDisplay(this)">显示</a>
						</c:if> 
					</td>
					<td>
						<a href="editPage?id=${item.gaimId }">修改</a>|<a href="delete?id=${item.gaimId }" onclick="javascript:return del()">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="6"></th>
			</tr>
		</tfoot>
	</table>
</body>
</html>
