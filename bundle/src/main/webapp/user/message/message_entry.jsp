<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">

<title>Insert title here</title>
</head>
<body>
收到留言(<a href="messagesearchRecv"><span class="errorSpan">${recvCount}</span></a>)发出留言(<a href="messagesearchSend"><span class="errorSpan">${sendCount}</span></a>)未读留言(<a href="messagesearchNotRead"><span class="errorSpan">${notReadCount}</span></a>)
</body>
</html>