<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>


</head>
<body>
<hr>
<center>
<table>
	<tr>
		<td><img src="<s:url value="/user/images/haha.gif"/>" />发送留言成功</td>
	</tr>
	<tr>
		<td><a href="<s:url value="/user/message/message_send.jsp"/>">返回再发一封</a></td>
	</tr>
</table>
</center>
</body>
</html>