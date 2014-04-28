<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link type="text/css" href="/admin/css/common.css" rel="stylesheet">
</head>
<body>
<div id="nav">
	<ul>
		<li class="bg_image_onclick" id="man_nav_1">域名详细</li>
	</ul>
</div>
<div id="sub_info">
	<div id="man_zone">
		<table cellspacing="1" cellpadding="3" border="0" align="center" width="99%" id="tablesorter">
			<tbody>
				<tr>
					<td>用户名</td><td>${username }</td>
				</tr>
				<tr>
					<td>域名/地址</td><td>${address }</td>
				</tr>
				<tr>
					<td>类型</td><td><s:property value="type"/></td>
				</tr>
				<tr>
					<td>ICP备案号</td><td>${icp }</td>
				</tr>
				<tr>
					<td>申请时间</td><td><s:date name="applyTime" format="yyyy-MM-dd hh:mm:ss"/></td>
				</tr>
				<tr>
					<td>不通过原因</td><td>${denyReason }</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<th class="tb_search" colspan="7">
					</th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
</body>
</html>