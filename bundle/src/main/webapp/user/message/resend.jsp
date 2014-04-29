<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回复信息</title>
</head>
<body>
<s:form namespace="/user/message" action="messagerecv" id="dialog" name="dialog" method="post" onsubmit="return checkValue2()">
	<s:hidden name="recvMessageId" id="messageId" value="%{messageId}" />
	<s:hidden name="pageType" value="recvMessage" />
	<table>
		<tr>
			<td><s:textfield label="接收人" theme="xhtml" name="username"
				id="receive" readonly="true" value="%{#parameters['Torecive']}"/></td>
		</tr>
		<tr>
			<td><s:textfield label="标题" theme="xhtml" name="title"
				id="title1" value="%{#parameters['Totitle']}"/></td>
		</tr>
		<tr>
		<td>
			<s:textarea name="content" id="content1" cols="20" value="" label="内容" theme="xhtml"></s:textarea>
		</td>
		</tr>
	</table>
</s:form>
</body>
</html>