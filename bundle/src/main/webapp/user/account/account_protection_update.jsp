<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.abbcc.common.CommonConst"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=CommonConst.SITEINFO.sitename%>-设置密码保护</title>
<link href="<s:url value="/user/css/css.css"/>" rel="stylesheet" type="text/css" />
<link href="<s:url value="/user/css/div.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<!--[if lte IE 6]>
<script src="js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
</head>

<body>
<div id="body">

	<s:form namespace="/user/account/password" action="updatePasswordProtection" method="post">
	<table>
		<tr>
			<td>您当前的密保问题是:</td>
			<td><s:textfield readonly="true" cssClass="txt" name="pwdQuestion" value="%{#parameters['pwdQuestion']==null?pwdQuestion:#parameters['pwdQuestion']}"/></td>
			
		</tr>
		<tr>
			<td>请输入原来的密码保护答案:</td>
			<td><s:textfield name="pwdAnswer" cssClass="txt"/></td>
			<td><span class="errorSpan">${errors.pwdAnswer[0]}</span></td>
		</tr>
		<tr>
			<td>请输入新的密保问题是:</td>
			<td><s:textfield name="newPwdQuestion" cssClass="txt"/></td>
			<td><span class="errorSpan">${errors.newPwdQuestion[0]}</span></td>
		</tr>
		<tr>
			<td>请输入新的密码保护答案:</td>
			<td><s:textfield name="pwdAnswer1" cssClass="txt"/></td>

		</tr>
		<tr>
			<td>请再输入一次:</td>
			<td><s:textfield name="pwdAnswer2" cssClass="txt"/></td>
			<td><span class="errorSpan">${errors.pwdAnswer2[0]}</span></td>
		</tr>
		<tr>
			<td><s:submit value="提交"
				onclick="javascript:return confirm('确定提交吗？')" /></td>
			<td><input type="button" value="取消" onclick="javascript:history.go(-1);"/></td>
		</tr>
	</table>
	</s:form>  
</div>
</body>
</html>