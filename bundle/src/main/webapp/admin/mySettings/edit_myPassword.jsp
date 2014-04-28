<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<title>修改个人信息</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改密码</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
<div id="warning">
	${result}
</div>
</s:if>
<s:form namespace="/admin" action="updateMyPassword"
	method="post">
	<s:hidden name="id" value="%{#session.abbccadmin.adminId}"></s:hidden>
	<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" class="table_style">
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">老密码</span>
		</td>
		<td width="82%"><s:password name="password"/></td>
	</tr>
	<tr>
		<td class="left_title_2">新密码</td>
		<td><s:password name="password1"/></td>
	</tr>
	<tr>
		<td class="left_title_2">再次输入新密码</td>
		<td><s:password name="password2"/></td>
	</tr>
	<tr>
		<td class="left_title_2"></td>
		<td class="left_title_2" style="text-align: left"><s:submit value="修改"/></td>
	</tr>
</table>
</s:form>
</div>
</div>
</body>
</html>
