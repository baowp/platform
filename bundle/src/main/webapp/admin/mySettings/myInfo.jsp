<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<title>修改个人信息</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改个人信息</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<form id="adminInfo" action="adminInfoAction" method="post">
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" class="table_style">
	<tr>
		<td width="18%" class="left_title_1"> <span
			class="left-title">姓名</span></td>
		<td width="82%"><input type="text" name="name" id="name"
			value="${sessionScope.abbccadmin.name}" size="20" /><span class="errorSpan">${errors.name[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span
			class="left-title">email</span></td>
		<td width="82%"><input type="text" name="email" id="email"
			value="${sessionScope.abbccadmin.email}" size="20" /><span class="errorSpan">${errors.email[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">手机</span>
		</td>
		<td width="82%"><input type="text" name="cellphone"
			id="cellphone" value="${sessionScope.abbccadmin.cellphone}" size="20" /><span class="errorSpan">${errors.cellphone[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">性别</span>
		</td>
		<td width="82%"><select name="gender" id="gender">
			<option value="0">男</option>
			<option value="1">女</option>
		</select></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">个人说明</span>
		</td>
		<td width="82%"><textarea name="adesc" id="adesc">${sessionScope.abbccadmin.adesc}</textarea></td>
	</tr>
	<tr>
		<td class="left_title_2"></td>
		<td class="left_title_2" style="text-align: left"><input
			type="submit" name="save" value="提交" /></td>
	</tr>
</table>
</form>
</div>
</div>
</body>
</html>
