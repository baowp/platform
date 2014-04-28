<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加帮助</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/admin/help/help.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>

<style>
.suggest_link_over {
	background: #D5DBF4;
	padding-left: 2px;
}

.suggest_link {
	background: #fff;
	padding-left: 2px;
}
</style>
</head>
<body>
<s:form namespace="/admin" action="delHelpCenter">
<div id="nav" style="position: relative">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">帮助中心</li>
	<li><a href="#" onclick="showAdd(this)">点击添加帮助</a>
	</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<s:hidden name="page" id="page" />
	<s:hidden name="entId" id="entId" />
	<thead>
		<tr>
			<th class="header" name="title">常见问题</th>
			<th class="header" name="adduser">添加用户</th>
			<th class="header" name="addtime">添加时间</th>
			<th class="header">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="prod" value="pageList.items">
			<tr>
				<td><a href="detailHelpCenter?id=<s:property value="newsId" />"
					target="_blank"><s:property value="title" /></a></td>
				<td><s:property value="addAdmin().username" /></td>
				<td><s:property value="addTimeString()" /></td>
				<td><s:checkbox fieldValue="%{newsId}" name="newsIds"/></td>
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
		<tr>
			<th colspan="7" class="tb_search">
			<s:checkbox id="check" name="check" onclick="check_all(this,'newsIds')"/>全选</td>
			<button type="button" id="delete" class="delete" onclick="delall('newsIds')">删除</button>
			</th>
		</tr>
	</tfoot>
</table>
</div>
</div>
</s:form>
<s:form namespace="/admin" action="addHelpCenter" id="dialog"
	cssStyle="display:none;" method="get">
	<s:hidden name="id" id="payuserId" value="" />
	<table>
		<tr>
			<td><s:textfield name="title" label="帮助主题*" theme="xhtml" /><span
				class="errorSpan">${errors.title[0]}</span></td>
		</tr>
		<tr>
			<td><s:textarea name="content" cssClass="ckeditor" label="内容" theme="xhtml"/><span class="errorSpan">${errors.content[0]}</span></td>
		</tr>

		<tr>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
</s:form>
</body>
</html>
