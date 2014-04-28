<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<script language="JavaScript">
		if (window != top)
			top.location.href = location.href;
		</script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<title>Abbcc系统后台管理登陆</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">管理登陆</li>
</ul>
</div>
<div id="sub_info">&nbsp;&nbsp; <img src="images/hi.gif" />
&nbsp; <span id="show_text">欢迎使用Abbcc系统后台管理!</span>
<div id="man_zone">
<s:if test="result!=''&&result!=null">
<div id="warning">
	${result}
</div>
</s:if>
<s:form namespace="/admin/dfwj" action="login"
	method="post">
	<s:token />
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<tr>
			<td width="18%" class="left_title_1"><span class="left-title">用户名</span>
			</td>
			<td width="82%"><s:textfield name="username" /><span class="errorSpan">${errors.username[0]}</span>
			</td>
		</tr>
		<tr>
			<td class="left_title_2">密码</td>
			<td><s:password name="password" /><span class="errorSpan">${errors.password[0]}</span>
			</td>
		</tr>
		<tr>
			<td class="left_title_1">验证码</td>
			<td><s:textfield name="vericode" /><span class="errorSpan">${errors.vericode[0]}</span>
			&nbsp; <img class="validate-num" id="verifyPic" name="verifyPic"
				valign="bottom" src="<s:url value="/veriImg"/>" /> <a
				onclick="refreshCode();" href="#">看不清换一张？</a></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left"><input
				type="submit" id="adminLogin_save" name="save" value="登录" /></td>
		</tr>
	</table>
</s:form></div>
</div>

</body>
</html>

