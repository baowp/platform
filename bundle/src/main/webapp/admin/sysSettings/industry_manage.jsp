<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>行业管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">行业管理</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<div id="list">
 <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
	<thead>
		<tr>
			<th class="left_title_1" name="name" width="20%">行业名称</th>
			<th class="left_title_1" name="operation" width="20%">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="province" value="pageList.items" status="u">
			<tr>
				<td width="20%"><s:property value="name" /></td>
				<td width="20%"><a
					href="javascript:if(confirm('确定要删除？'))window.location='deleteDistrict?id=<s:property value="syscodeId"/>'">删除</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
	<tfoot>
		<tr>
			<th colspan="2" class="left_title_1"><s:include
				value="../../common/page.jsp">
				<s:param name="pageList" value="pageList" />
			</s:include></th>
		</tr>
	</tfoot>
</table>
</div>
<div id="add"><s:form namespace="/admin" action="addIndustry"
	method="post">
 <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
		<thead>
			<tr>
				<th class="left_title_1" name="Industry">行业名称</th>
				<s:hidden name="type" value="02"></s:hidden>
				<s:hidden name="state" value="01"></s:hidden>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><s:textfield name="name" /><span class="errorSpan">${errors.name[0]}</span>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="3" class="left_title_1"><s:submit value="添加" /></th>
			</tr>
		</tfoot>
	</table>
</s:form></div>
</div>
</div>
</body>
</html>
