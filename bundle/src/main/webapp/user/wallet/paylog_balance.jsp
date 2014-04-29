<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<title>abbcc</title>
</head>
<body style="padding: 10px;">
您目前的账户余额为：<font color="red">${empty requestScope.balance[0] ? 0: requestScope.balance[0]}</font> 元 
</body>
</html>