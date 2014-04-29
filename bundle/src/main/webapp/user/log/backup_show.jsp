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
	function check(){
		if($("#filename").val()==''||$("#filename").val()==null){
			alert("请输入备份的文件名!");
			return false;
		}else
			return true;
	}
</script>
</head>

<body>

<h2>日志备份</h2>&nbsp<s:form namespace="/admin" action="addbackup" onsubmit="return check();">
<s:textfield id="filename" name="filename" label="备份名" theme="xhtml"/><span class="errorSpan">${errors.username[0]}</span>
<s:submit value="开始备份"></s:submit>
</s:form>

<table class="listTable">
	<thead>
		<tr>
			<td >备份文件</td>
			<td >时间</td>
			<td >操作</td>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="log" value="pageList.items" status="st">
		<tr>
			<td >${filename}</td>
			<td ><s:date name="uploadTime" format="yyyy-MM-dd hh:mm:ss"/></td>
			<td ><a href="<s:url value="/admin/dataResumebackup"/>?id=${attId}" onclick="return confirm('确定要还原吗?')">还原</a>/<a href="<s:url value="/admin/delbackup"/>?id=${attId}" onclick="return del()">删除</a></td>
		</tr>
		</s:iterator>
	</tbody>
	<tr>


		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>

	</tr>
</table>


</body>
</html>
