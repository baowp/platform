<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link href="../css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<title>修改二级帐号</title>
<!--[if lte IE 6]>
<script src="js/DD_belatedPNG_0.0.8a.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('div, ul, img, li, input , a');
    </script>
<![endif]-->
</head>

<body>
<div id="cpcontainer" class="container"><div class="itemtitle"><h3>修改二级帐号</h3>
</div><s:form action="updateSubAccount" namespace="/user/account/password">
<table class="tb tb2 ">
<tbody><tr><td class="td27" colspan="2">用户名:</td></tr>
<tr class="noborder"><td class="vtop rowform">
<s:textfield name="username" cssClass="txt"/></td><td class="vtop tips2"><span class="errorSpan">${errors.username[0]}</span></td></tr>
<tr><td class="td27" colspan="2">密码:</td></tr>
<tr class="noborder"><td class="vtop rowform">
<s:password name="password1" cssClass="txt" /></td><td class="vtop tips2"></td></tr>
<tr>
  <td class="td27" colspan="2">姓&nbsp;&nbsp;&nbsp;名:</td>
</tr>
<tr class="noborder"><td class="vtop rowform"><s:textfield name="name"  cssClass="txt"/></td>
<td class="vtop tips2"><span class="errorSpan">${errors.name[0]}</span></td></tr>
<tr>
  <td class="td27" colspan="2">Email:</td>
</tr>
<tr class="noborder"><td class="vtop rowform">
<s:textfield name="email"  cssClass="txt"/></td><td class="vtop tips2"><span class="errorSpan">${errors.email[0]}</span></td></tr>
<tr>
      <td class="td27" colspan="2">地&nbsp;&nbsp;&nbsp;址:</td>
</tr>
<tr class="noborder"><td class="vtop rowform"><s:textfield name="address"  cssClass="txt"/></td>
<td class="vtop tips2"></td></tr>
    <td class="td27" colspan="2">Q&nbsp;&nbsp;&nbsp;Q:</td>
</tr>
<tr class="noborder"><td class="vtop rowform"><s:textfield name="qq" cssClass="txt" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
<td class="vtop tips2"></td></tr>
    <td class="td27" colspan="2">电&nbsp;&nbsp;&nbsp;话:</td>
</tr>
<tr class="noborder"><td class="vtop rowform"><s:textfield name="phone" cssClass="txt" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
<td class="vtop tips2"></td></tr>
    <td class="td27" colspan="2">手&nbsp;&nbsp;&nbsp;机:</td>
</tr>
<tr class="noborder"><td class="vtop rowform"><s:textfield name="cellphone" cssClass="txt" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
<td class="vtop tips2"></td></tr>
  <tr><td colspan="15"><div class="fixsel"><input type="image" border="0" src="/images/add.jpg" alt="提交" title="提交"/> 
  &nbsp;
  </div></td></tr></tbody></table>
</s:form>

</div>
</body>
</html>