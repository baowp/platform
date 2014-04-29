<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加企业联系人</title>
</head>
<body>
<div>${errors.message[0]}</div>
	<center>
		<h2>修改企业联系人</h2><hr/>
		<s:form namespace="/user/contact" action="contactupdate">
		<s:hidden name="id" value="%{enterpcontactId}"></s:hidden>
		<table>
		<tr><td><s:textfield name="name" label="姓名"  maxlength="20" theme="xhtml"/></td><td><span class="errorSpan">${errors.name[0]}</span></td></tr>
		<tr><td><s:select list="#{'00':'男','01':'女'}"  label="性别" listKey="key" name="sex" listValue="value" theme="xhtml"/></td></tr>
		<tr><td><s:textfield name="position" label="职务" theme="xhtml"/></td><td><span class="errorSpan">${errors.position[0]}</span></td></tr>
		<tr><td><s:textfield name="phone"  maxlength="20" label="电话" theme="xhtml" /></td><td><span class="errorSpan">${errors.phone[0]}</span></td></tr>
		<tr><td><s:textfield name="fax"  maxlength="20" label="传真" theme="xhtml" /></td></tr>
		<tr><td><s:textfield name="cellphone" label="手机" theme="xhtml" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"  maxlength="12"/></td></tr>
		<tr><td><s:textfield name="url" label="公司网址" theme="xhtml"/></td></tr>
		<tr><td><s:textfield name="email" label="电子邮箱" theme="xhtml"/></td></tr>
		<tr><td><input type="image" src="/images/add.jpg" alt="提交"  border="0" title="提交"/></td><td><img src="/images/fanhui.jpg"  border="0" alt="返回" title="返回" onclick="javascript:history.go(-1);"/></td></tr>
		</table>
		</s:form>
	</center>
</body>
</html>