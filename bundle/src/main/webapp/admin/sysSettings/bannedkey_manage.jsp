<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>关键字管理</title>
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
	<li id="man_nav_1" class="bg_image_onclick">关键字管理</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
<div id="warning">
	${result}
</div>
</s:if>
<s:form namespace="/admin" action="editBannedkey"
	method="post">
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<thead>
		<tr>
			<th class="header" name="name">请输入关键字，多个关键字用空格隔开。关键字将用于产品、新闻等发布的过滤</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>
				<s:hidden name="id" value="%{syscodeId}"></s:hidden>
				<s:hidden name="type"></s:hidden>
				<s:hidden name="state"></s:hidden>
				<s:textarea name="content" wrap="true" cssStyle="width:98%;height:300px;">
				
				</s:textarea>
				</td>
			</tr>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="3">
			<s:submit value="修改"/>
			</th>
		</tr>
	</tfoot>
</table>
</s:form>
</div>
</div>
</body>
</html>
