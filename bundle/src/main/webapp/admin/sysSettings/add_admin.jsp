<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/admin/css/common.css"
	type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/button.js"></script>
	<script type="text/javascript">
	function checkValue(){
		if($("#domain").val()=='none'){
			alert("请选择系统分类!")
			return false;
		}
	}
	</script>
<title>添加管理员</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">添加管理员</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<form action="<%=request.getContextPath()%>/admin/addAdmin"
	method="post" id="myform" onsubmit="return checkValue()">
	
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" class="table_style">
<tr>
		<td width="18%" class="left_title_1"><span class="left-title">所属网站</span>
		</td>
		<td width="82%">
		<s:select list="#{'cn.51archetype.com':'东51archetype.comcc.ne51archetype.com','51archetype.com':'上门网'}" name="domain"></s:select>
		</td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">用户名</span>
		</td>
		<td width="82%"><input type="text" name="username" id="username"
			value="${username}" size="20" /><span class="errorSpan">${errors.username[0]}</span></td>
	</tr>
	<tr>
		<td class="left_title_1">密码</td>
		<td><input type="password" name="password" id="password"
			size="20" /><span class="errorSpan">${errors.password[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">姓名</span>
		</td>
		<td width="82%"><input type="text" name="name" id="name"
			value="${name}" size="20" /><span class="errorSpan">${errors.name[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">email</span>
		</td>
		<td width="82%"><input type="text" name="email" id="email"
			value="${email}" size="20" /><span class="errorSpan">${errors.email[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">手机</span>
		</td>
		<td width="82%"><input type="text" name="cellphone"
			id="cellphone" value="${cellphone}" size="20" /><span
			class="errorSpan">${errors.cellphone[0]}</span></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">性别</span>
		</td>
		<td width="82%"><select name="gender" id="gender">
			<option value="00">男</option>
			<option value="01">女</option>
		</select></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">状态</span>
		</td>
		<td width="82%"><select name="state" id="state">
			<option value="01">正常</option>
			<option value="02">封禁</option>
		</select></td>
	</tr>
	<tr>
		<td width="18%" class="left_title_1"><span class="left-title">类型</span>
		</td>
		<td width="82%"><select name="type" id="type">
			<option value="01">管理员</option>
			<option value="02">后台工作人员</option>
		</select></td>
	</tr>
	<tr>
		<td class="left_title_2"></td>
		<td class="left_title_2" style="text-align: left"><input
			type="submit" name="save" id="submit" value="提交" /><button onclick="javascript:window.history.go(-1)">返回</button></td>
	</tr>
</table>
</form>
</div>
</div>
</body>
</html>
