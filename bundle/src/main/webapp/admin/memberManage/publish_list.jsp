<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>统计</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<link rel="stylesheet" href="<s:url value="/css/jquery/style.css"/>" type="text/css" id="" media="print, projection, screen" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery/jquery-latest.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" id="js">
	$(document).ready(function() { 
    $("table").tablesorter({widthFixed: true, widgets: ['zebra']}); 
});
</script>
</head>
<body>

	<s:form namespace="/admin" action="infoPublishStat" method="post" id="form2">
<s:hidden name="pageOrder"/>
</s:form>
<s:form namespace="/admin" action="infoPublishStat" method="post" id="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">发布统计查询</li>
		<li><s:textfield name="entName" id="entName" label="企业名" theme="xhtml"
			 autocomplete="off" />
		<div id="suggestUserName"
			style="display: none; border: 1px solid #3366CC; width: 300px; position: absolute;"></div>
		<input type="submit" value="查找" id="search"/></li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table cellspacing="1" class="tablesorter">
		<s:hidden name="page" id="page" />
		<thead>
			<tr>
				<th>企业名称</th>
				<th>登陆次数</th>
				<th>产品发布</th>
				<th>供求发布</th>
				<th>新闻发布</th>
				<th>证书发布</th>
				<th>招聘发布</th>
				<th>最后使用时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<tr>
					<td><a href="infoMember?id=<s:property value="enterprise(enterpriseId).userId" />"><s:property value="enterprise(enterpriseId).name" /></a></td>
					<td>${loginfrequency}</td>
					<td>${productfrequency}</td>
					<td>${supplyfrequency}</td>
					<td>${newsfrequency}</td>
					<td>${certfrequency}</td>
					<td>${jobfrequency}</td>
					<td><s:date name="lastAddTime" format="yyyy-MM-dd hh:mm:ss"/></td>
				</tr>
			</s:iterator>
		</tbody>
		<tfoot>
			<tr>
		<th colspan="10" class="tb_search"><s:include value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>
			</tr>
			<tr>
				<th colspan="10" class="tb_search">查询：<s:textfield
					id="searchWord" name="searchWord" />
				<button type="button" id="filter" class="search">提交</button>
				<button type="button" class="refresh"
					onclick="window.location.reload()">刷新</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
