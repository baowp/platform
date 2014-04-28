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
<s:form namespace="/admin/priv" action="savepriv">
<s:textfield name="mName" label="模块名称" theme="xhtml"></s:textfield>
<s:textfield name="url" label="action" theme="xhtml"></s:textfield>
<s:select name="mId" value="mId.name()" listKey="name()" list="@com.abbcc.util.constant.AdminPrivType@values()"></s:select>
<s:submit></s:submit>
</s:form>
</body>
</html>