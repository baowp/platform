<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>付费管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMember?searchWord="+$("#searchWord").val();
			});
		});
</script>
</head>
<body>
<s:form namespace="/admin" action="memberLoginStat" method="post" id="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">登陆资料查询</li>
		<li><s:textfield name="entName" id="entName" label="企业名" theme="xhtml"
			 autocomplete="off" />
		<div id="suggestUserName"
			style="display: none; border: 1px solid #3366CC; width: 300px; position: absolute;"></div>
		<input type="submit" value="查找" id="search"/></li>
	</ul>
	</div>
	</s:form>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table><tr align="center"><td>用户后台总共登陆过<font color=red>${sumStat }</font>次，今天登陆过的有<font color=red></font>次</td></tr></table>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<s:hidden name="page" id="page" />
		<thead>
			<tr>
				<th class="header" name="enterprise">企业名称</th>
				<th class="header" name="state">登陆次数</th>
				<th class="header" name="type">最后登陆时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<tr>
					<td><a href="infoMember?id=<s:property value="enterprise(enterpriseId).userId" />"><s:property value="enterprise(enterpriseId).name" /></a></td>
					<td>${frequency}</td>
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

</body>
</html>
