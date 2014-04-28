<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#add").click(function() {
				window.location="<s:url value="/admin/soa/add_soaUser.jsp"/>";
			});
		});
</script>
</head>
<body>
<s:form namespace="/admin" action="listUserSite" method="post"
	id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">网站服务器</li>
		<li><s:textfield name="searchKey">用户名：</s:textfield><s:select
			list="%{#session.webservers}" name="searchServerId"
			listKey="serverId" listValue="name" headerKey=""
			headerValue="--请选择服务器--">服务器</s:select> <s:textfield
			name="searchDomain" id="searchKey">域名：</s:textfield><s:submit
			cssClass="search" value="查询" /></li>
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
				<th class="header" name="username">用户名</th>
				<th class="header" name="ip">服务器</th>
				<th class="header" name="domain">域名</th>
				<th class="header" name="folder">部署路径</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="soauser" value="pageList.items">
				<tr>
					<td><s:property value="username" /></td>
					<td><s:property value="webServer().name" /></td>
					<td><s:property value="domain" /></td>
					<td><s:property value="folder" /></td>
					<td><s:url id="url" action="viewUserSite">
						<s:param name="id">
							<s:property value="usersiteId" />
						</s:param>
					</s:url> <s:a href="%{url}">修改</s:a>/<a href="removeUserSite?id=${usersiteId }">删除</a></td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="5" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<tr>
				<th colspan="5" class="tb_search">
				<button type="button" id="add" class="add">添加服务器</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
