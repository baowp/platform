<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站模板管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
</head>
<body>
	<div id="sub_info">
	<div id="man_zone">
<s:if test="result!=''&&result!=null">
		<div id="warning" style="DISPLAY: none">${result}</div>
		<script type="text/javascript">
		window.onload   =   new   function()
		{
			parent.makeState($("#warning").html());
		}

		</script>
</s:if>
		<table width="99%" border="0" align="center" cellpadding="3"
			cellspacing="1" id="tablesorter">
			<thead>
				<tr>
					<th class="header" name="username">用户名</th>
					<th class="header" name="domain">域名</th>
					<th class="header" name="name">页面名称</th>
					<th class="header" name="pagename">页面url</th>
					<th class="header" name="addTime">上传时间</th>
					<th class="header" name="userdisplay">用户可见</th>
					<th class="header" name="op">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator var="template" value="pageList.items">
					<tr>
						<td><s:property value="soaUser().username" /></td>
						<td><s:property value="soaUser().domain" /></td>
						<td><s:property value="name" /></td>
						<td><s:property value="pageName" /></td>
						<td><s:property value="addTimeString()" /></td>
						<td><s:if test="userdisplay==1">是</s:if>
							<s:if test="userdisplay==0">否</s:if>
						</td>
						<td><s:url id="url" action="viewTemplate">
							<s:param name="id">
								<s:property value="templateId" />
							</s:param>
							<s:param name="siteId">
								<s:property value="usersiteId" />
							</s:param>
						</s:url> <s:a href="%{url}">修改</s:a></td>
					</tr>
				</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="7" class="tb_search"><s:include
						value="../../common/page.jsp">
						<s:param name="pageList" value="pageList" />
					</s:include></th>
				</tr>
			</tfoot>
		</table>
	</div>
	</div>
</body>
</html>
