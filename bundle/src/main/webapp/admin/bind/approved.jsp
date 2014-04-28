<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link type="text/css" href="/admin/css/common.css" rel="stylesheet">
<script src="/js/jquery.js" type="text/javascript"></script>
<link type="text/css" href="/css/jquery-ui.css" rel="stylesheet">
<script src="/js/jquery-ui.min.js" type="text/javascript"></script>
<script src="/js/common.js" type="text/javascript"></script>
<script src="/admin/bind/js/audit.js" type="text/javascript"></script>
<script src="/admin/bind/js/approved.js" type="text/javascript"></script>
</head>
<body>
<s:form action="approved" method="get">
	<div id="nav">
		<ul>
			<li class="bg_image_onclick" id="man_nav_1">审核通过的域名</li>
			<s:include value="search.jsp"/>
		</ul>
	</div>
	<div id="sub_info">
		<div id="man_zone">
			<table cellspacing="1" cellpadding="3" border="0" align="center" width="99%" id="tablesorter">
				<thead>
					<tr>
						<th>用户名</th>
						<th>域名/地址</th>
						<th>类型</th>
						<th>ICP备案号</th>
						<th>申请时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="pageList.items">
					<tr class='{"id":"${bindId}"}'>
						<td>${username }</td>
						<td>${address }</td>
						<td><s:property value="type"/></td>
						<td>${icp }</td>
						<td><s:date name="applyTime" format="yyyy-MM-dd hh:mm:ss"/></td>
						<td>
							<a href="javascript:deny()" class="operate">不通过</a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<th class="tb_search" colspan="7">
							<s:include value="/common/page.jsp"/>
						</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>