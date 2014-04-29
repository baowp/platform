<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.abbcc.common.CommonConst"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<s:url value='/user/css/validation.css'/>">

<title><%=CommonConst.SITEINFO.sitename%>-设置密码保护</title>
<link href="<s:url value="/user/css/css.css"/>" rel="stylesheet" type="text/css" />
<link href="<s:url value="/user/css/div.css"/>" rel="stylesheet" type="text/css" />
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="body">
<s:if test="%{#request.userRequest.pwdQuestion==null}">
  <div id="top_b">
    <div class="font05">&nbsp;</div>
    <div class="font_h" id="top_footer"> &nbsp;您当前还没有任何密码保护，为了更好的保证您帐号的安全，请使用密码保护</div>
  </div>
  <s:form namespace="/user/account/password"
		action="savePasswordProtection"
		method="post">
  <div id="gh">
    <table width="50%" height="110" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="21%" align="center" class="font_h">请输入保护问题:</td>
        <td width="29%" align="left"><s:textfield name="pwdQuestion" cssClass="txt"/></td>
        <td width="50%" align="left"><span class="errorSpan">${errors.pwdQuestion[0]}</span></td>
      </tr>
      <tr>
        <td align="center" class="font_h">请输入保护答案:</td>
        <td align="left"><s:textfield name="pwdAnswer" cssClass="txt"/></td>
        <td align="left"><span class="errorSpan">${errors.pwdAnswer[0]}</span></td>
      </tr>
      <tr>
        <td align="center" class="font_h">请再次输入一次:</td>
        <td align="left"><s:textfield name="pwdAnswer2" cssClass="txt"/></td>
        <td align="left"><span class="errorSpan">${errors.pwdAnswer2[0]}</span></td>
      </tr>
      <tr>
        <td align="center" class="font_h">&nbsp;</td>
        <td align="left"><s:submit value="提交"
				onclick="javascript:return confirm('确定提交吗？')" /></td>
        <td align="left">&nbsp;</td>
      </tr>
    </table>
  </div>
  </s:form>
  </s:if>
  <s:else>
	<table>
		<tr>
			<td>您已经设定了密码保护问题</td>
			<td><a href="javascript:" onclick="window.location.href='/user/account/account_protection_update.jsp?pwdQuestion=<s:property value='#request.userRequest.pwdQuestion' />'">修改</a></td>
		</tr>
		<tr>
			<td>设置问题后可以用于找回密码和设置其他密保手段。</td>
		</tr>
	</table>


</s:else>
</div>
</body>
</html>
