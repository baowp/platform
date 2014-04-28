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
	<li id="man_nav_1" class="bg_image_onclick">修改个人信息</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
		<script type="text/javascript">
		window.onload   =   new   function()
		{
			$("#warning").slideToggle(2000);
		}
		</script>
</s:if>
<s:form namespace="/admin"
	action="updateMyAccountInfo" method="post">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<s:hidden name="id" value="%{#session.abbccadmin.adminId}"></s:hidden>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">姓名</span></td>
			<td width="82%"><s:textfield name="name"
				value="%{#session.abbccadmin.name}" /><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">email</span></td>
			<td width="82%"><s:textfield name="email"
				value="%{#session.abbccadmin.email}" /><span class="errorSpan">${errors.email[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">手机</span>
			</td>
			<td width="82%"><s:textfield name="cellphone"
				value="%{#session.abbccadmin.cellphone}" /><span class="errorSpan">${errors.cellphone[0]}</span></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">性别</span>
			</td>
			<td width="82%"><select name="gender" id="gender">
				<option value="00"
					<s:if test="#session.abbccadmin.gender=='00'">selected</s:if>>男</option>
				<option value="01"
					<s:if test="#session.abbccadmin.gender=='01'">selected</s:if>>女</option>
			</select></td>
		</tr>
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">个人说明</span>
			</td>
			<td width="82%"><s:textarea name="adesc" cols="30"
				cssStyle="overflow:visible" value="%{#session.abbccadmin.adesc}"></s:textarea></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left"><s:submit value="修改"/></td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
