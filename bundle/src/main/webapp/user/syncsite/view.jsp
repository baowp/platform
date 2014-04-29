<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同步网站</title>
</head>
<body>
<s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<s:if test="usersiteId!=null">
	<s:url id="url" action="syncSite">
		<s:param name="id">
			<s:property value="usersiteId" />
		</s:param>
	</s:url>
	<s:a href="%{url}">同步</s:a>
</s:if>
</body>
</html>