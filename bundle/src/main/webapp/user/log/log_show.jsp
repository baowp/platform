<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript">
	function clearLog(){
		if (confirm('你确定清除吗?')) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>

<body>
<s:if test="result!=''&&result!=null">
		<div id="warning" style="DISPLAY: none">${result}</div>
		<script type="text/javascript">
		window.onload   =   new   function()
		{
			parent.makeState($("#warning").html());
		}

		</script>
</s:if>
<h2>日志查看</h2><font color="red">(注:只保存一个月以内的日志)</font>

<table class="listTable">
	<thead>
		<tr>
			<th >详细</th>
			<th >时间</th>
			<th >IP</th>
			<th>访问地址</th>
		</tr>
	</thead>
	<tbody>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="3">没有相关数据!</td></tr>
</s:if>
		<s:iterator var="log" value="pageList.items" status="st">
		<tr>
			<td ><s:property value="userName()"/>对${name}(${ldesc})进行了<FONT color="red"><s:property value="logType()"/></FONT>操作</td>
			<td  valign="middle" align="center"><s:date name="addTime" format="yyyy-MM-dd hh:mm:ss"/></td>
			<td  valign="middle" align="center">${ip}</td>
			<td  valign="middle">${location}</td>
		</tr>
		</s:iterator>
	</tbody>
	<tfoot>
	<tr>
		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>
	</tr>
	</tfoot>
</table>


</body>
</html>
