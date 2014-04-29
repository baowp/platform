<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航菜单维护</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/user/abbcc/css/common.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/jquery-ui.min.css"/>">
<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="/group/navigator/js/operate.js"></script>
</head>

<body>
	<f:form action="step" id="form1">
		<!-- 用于调顺序-->
		<input type="hidden" name="sourceCate" />
		<input type="hidden" name="sourceSort" />
		<input type="hidden" name="stepType" />
		<input type="hidden" name="navigatorId"
			value="${resultList[0].parentId} " />
	</f:form>
	<f:form action="save" commandName="model">
		<div class="fv">
			<label>当前目录：</label> <a href="/rest/group/navigator/list">一级菜单</a>&gt;
			<c:forEach var="route" items="${position}">
				<c:choose>
					<c:when test="${route.key eq '0'}"></c:when>
					<c:otherwise>
						<a href="/rest/group/navigator/list?navigatorId=${route.key}">${route.value}
						</a>&gt;
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<label>页面路径:</label>
		<f:input path="page" title="请输入页面路径，注意不能重复"></f:input>
		<f:errors path="page" style="color:red"></f:errors>
		<label>导航菜单名称:</label>
		<f:input path="name" title="请输入导航菜单名称"></f:input>
		<f:errors path="name" style="color:red"></f:errors>
		<f:hidden path="grade" value="${resultList[0].grade} " />
		<f:hidden path="parentId" value="${parentId}" />
		<input type="submit" value="添加菜单" onclick="return check()" />
	</f:form>
	<table class="listTable">
		<thead>
			<tr>
				<th>页面路径</th>
				<th>导航菜单名称</th>
				<th>排序</th>
				<th>子菜单管理</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty resultList }">
				<tr class="s">
					<td align="center" colspan="4">没有相关数据!</td>
				</tr>
			</c:if>
			<tr id="insert" style="display: none" class="i">
				<td valign="middle" align="center"></td>
				<td valign="middle" align="center"></td>
				<td valign="middle" align="center"></td>
			</tr>
			<c:set var="listLen" value="${resultList.size()}" scope="page"></c:set>
			<c:forEach var="item" items="${resultList}">
				<tr id="navigatorId" navigatorId="${item.navigatorId }"
					sort="${item.sort }">
					<td valign="middle" align="center">${item.page }</td>
					<td valign="middle" align="center"><input type="text"
						name="name" value="${item.name }" /><a href="javascript:void(0)"
						id="update" onclick="javascript:edit(this)">修改</a>
					</td>
					<td align="center"><c:choose>
							<c:when test="${item.navigatorId eq resultList[0].navigatorId }">上移 |</c:when>
							<c:otherwise>
								<a href=javascript:void(0) onclick=stepSort(this,2)>上移</a> |
								</c:otherwise>
						</c:choose> <c:choose>
							<c:when
								test="${item.navigatorId eq resultList[listLen-1].navigatorId }">下移</c:when>
							<c:otherwise>
								<a href=javascript:void(0) onclick=stepSort(this,1)>下移</a>
							</c:otherwise>
						</c:choose></td>
					<td valign="middle" align="center">
					<c:choose>
						<c:when test="${item.grade eq 3}" ></c:when>
						<c:otherwise>
						<a
						href="/rest/group/navigator/list?navigatorId=${item.navigatorId}&grade=${item.grade+1}">下属分类(${item.childSize})</a>
						</c:otherwise>
					</c:choose>
					
					</td>
					<td valign="middle" align="center"><c:if
							test="${item.display eq 0}">
							<a href="javascript:void(0)" id="isDisplay" value="1"
								onclick="javascript:setDisplay(this)">已隐藏</a>
						</c:if> <c:if test="${item.display eq 1}">
							<a href="javascript:void(0)" id="isDisplay" value="0"
								onclick="javascript:setDisplay(this)">已显示</a>
						</c:if> | <c:choose>
							<c:when test="${command.preDelete(item.page)}">
								<label style="color: grey">删除</label>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)"
									onclick="javascript:deleteNav(this)">删 除 </a>
							</c:otherwise>
						</c:choose>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="5"></th>
			</tr>
		</tfoot>
	</table>
</body>
</html>
