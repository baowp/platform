<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<title>修改管理员</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">修改管理员</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if><s:form namespace="/admin" action="editAdmin" method="post">
	<s:token />

	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
<tr>
		<td width="18%" class="left_title_1"><span class="left-title">所属网站</span>
		</td>
		<td width="82%">
			<s:textfield name="domain"/>
		</td>
	</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">用户名</span>
			<s:hidden name="id" /></td>
			<td width="82%"><s:textfield name="username" /><span
				class="errorSpan">${errors.username[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_1">密码</td>
			<td><s:password name="password" /><span class="errorSpan">${errors.password[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">姓名</span>
			</td>
			<td width="82%"><s:textfield name="name" /><span
				class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">email</span>
			</td>
			<td width="82%"><s:textfield name="email" /><span
				class="errorSpan">${errors.email[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">手机</span>
			</td>
			<td width="82%"><s:textfield name="cellphone" /><span
				class="errorSpan">${errors.cellphone[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">性别</span>
			</td>
			<td width="82%"><select name="gender" id="gender">
				<option value="00" <s:if test="gender=='00'">selected</s:if>>男</option>
				<option value="01" <s:if test="gender=='01'">selected</s:if>>女</option>
			</select></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">状态</span>
			</td>
			<td width="82%"><select name="state" id="state">
				<option value="01" <s:if test="state=='01'">selected</s:if>>正常</option>
				<option value="02" <s:if test="state=='02'">selected</s:if>>封禁</option>
			</select></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">类型</span>
			</td>
			<td width="82%"><select name="type" id="type">
				<option value="01" <s:if test="type=='01'">selected</s:if>>管理员</option>
				<option value="02" <s:if test="type=='02'">selected</s:if>>员工</option>
			</select></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left"><input
				type="submit" name="save" id="submit" value="提交" /></td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
