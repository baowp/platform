<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户升级类型选择</title>
</head>
<body>
<center>
<h1>请选择您要升级的类型</h1>
<table><tr><td><a href="<s:url value='/user/upgrade/apply'/>" target="_blank">升级为普通付费会员</a><br/>(自主建站)</td><td></td><td><a href="<s:url value='/user/upgrade/custom'/>">升级成为高级付费会员</a><br/>(定制网站)</td></tr>
</table>
</center>
</body>
</html>