<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站服务器管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#add").click(function() {
				window.location="<s:url value="/admin/soa/add_webServer.jsp"/>";
			});
		});
		function opSite(op,id){
			var url="";
			var msg = "";
			if(op=="01"){
				msg="确定要启用服务器？";
				url="<s:url value="/admin/openWebServer?id="/>"+id;
			}
			if(op=="02"){
				msg="确定要停用服务器？";
				url="<s:url value="/admin/stopWebServer?id="/>"+id;
			}
			if(op=="03"){
				msg="确定要删除服务器？";
				url="<s:url value="/admin/deleteWebServer?id="/>"+id;
			}
			if(confirm(msg))
				window.location=url;
		}
</script>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">网站服务器</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
<div id="warning">
	${result}
</div>
</s:if>
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<thead>
		<tr>
			<th class="header" name="name">服务器名称</th>
			<th class="header" name="ip">服务器ip</th>
			<th class="header" name="webservice">webservice</th>
			<th class="header" name="ftpusername">ftp用户名</th>
			<th class="header" name="ftppassword">ftp密码</th>
			<th class="header" name="state">状态</th>
			<th class="header">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="webServer" value="resultList">
			<tr>
				<td><s:property value="name" /></td>
				<td><s:property value="ip" /></td>
				<td><s:property value="webservice" /></td>
				<td><s:property value="ftpUsername" /></td>
				<td><s:property value="ftpPassword" /></td>
				<td>
				<s:if test="state=='01'">
				<image src="<s:url value="/images/active.png"/>" title="启用" alt="启用"/>
				</s:if>
				<s:if test="state=='02'">
				<image src="<s:url value="/images/inactive.png"/>" title="停用" alt="停用"/>
				</s:if>
				<s:if test="state=='03'">
				<image src="<s:url value="/images/cancel.png"/>" title="删除" alt="删除"/>
				</s:if>
				</td>
				<td><s:url id="url" action= "viewWebServer">
						<s:param name="id">
							<s:property value="serverId" />
						</s:param>
					</s:url> <s:a href="%{url}">修改</s:a>
					<s:if test="state=='01'">
					<image src="<s:url value="/images/inactive.png"/>" title="停用" alt="停用" onclick="javascript:opSite('02','<s:property value="serverId" />')"/>
					<image src="<s:url value="/images/cancel.png"/>" title="删除" alt="删除"  onclick="javascript:opSite('03','<s:property value="serverId" />')"/>
					</s:if>	
					<s:if test="state=='02'">
					<image src="<s:url value="/images/active.png"/>" title="启用" alt="启用" onclick="javascript:opSite('01','<s:property value="serverId" />')"/>
					<image src="<s:url value="/images/cancel.png"/>" title="删除" alt="删除"  onclick="javascript:opSite('03','<s:property value="serverId" />')"/>
					</s:if>	
					<s:if test="state=='03'">
					<image src="<s:url value="/images/active.png"/>" title="启用" alt="启用"  onclick="javascript:opSite('01','<s:property value="serverId" />')"/>
					</s:if>	
					</td>
			</tr>
		</s:iterator>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="7" class="tb_search">
			<button type="button" id="add" class="add">添加服务器</button>
			</th>
		</tr>
	</tfoot>
</table>
</div>
</div>
</body>
</html>
