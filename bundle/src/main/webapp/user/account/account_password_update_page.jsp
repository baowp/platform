<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.abbcc.common.CommonConst"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link href="<s:url value="/user/css/css.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div id="cpcontainer" class="container">
<div class="itemtitle">
  <h3>修改密码</h3>
</div>
<s:form namespace="/user/account/password"
	action="udatePassword"
	method="post">
<table class="tb tb2 ">
<tbody><tr>
  <td class="td27" colspan="2">请输入旧密码:</td>
</tr>
<tr class="noborder"><td class="vtop rowform">
<s:password name="password" cssClass="txt"/></td><td class="vtop tips2"><span class="errorSpan">${errors.password[0]}</span></td></tr>
<tr>
  <td class="td27" colspan="2">请输入新密码:</td>
</tr>
<tr class="noborder"><td class="vtop rowform">
<s:password name="password1" cssClass="txt"/></td><td class="vtop tips2"><span class="errorSpan">${errors.password1[0]}</span></td></tr>
<tr>
  <td class="td27" colspan="2">再次输入密码:</td>
</tr>
<tr class="noborder"><td class="vtop rowform">
<s:password name="password2" cssClass="txt"/></td><td class="vtop tips2"><span class="errorSpan">${errors.password2[0]}</span></td></tr>
<td class="vtop tips2"></td></tr>
  <tr><td colspan="15"><div class="fixsel"><input type="submit" value="提交" onclick="javascript:return update()" title="按 Enter 键可随时提交您的修改" name="addsubmit" id="submit_addsubmit" class="btn"> 
  &nbsp;
  </div></td></tr></tbody></table>
</s:form>

</div>
</body>
</html>
