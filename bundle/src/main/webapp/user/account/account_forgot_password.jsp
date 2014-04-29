<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<s:include value="/user/include/header.jsp"></s:include>
	<s:form namespace="/user/account/password" action="updatePasswordForId" method="post">
		<table>
			<s:hidden name="userId" value="%{#request.userList.userId}"></s:hidden>
			<tr><td>尊敬的用户，你的用户名是:</td><td><s:property value="#request.userList.username" /></td><td></td></tr>
			<tr><td>请输入您的新密码:</td><td><s:password name="password1"/></td><td><span class="errorSpan">${errors.password1[0]}</span></td></tr>
			<tr><td>请再输入一次:</td><td><s:password name="password2"/></td><td><span class="errorSpan">${errors.password2[0]}</span></td></tr>
			<tr><td><s:submit value="确认修改" onclick="javascript:return confirm('确定修改吗？')"/></td><td></td><td></td></tr>
		</table>
	</s:form>
<s:include value="/user/include/footer.jsp"></s:include>
</body>
</html>