<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link rel="stylesheet" href="<c:url value="/admin/css/common.css"/>"
	type="text/css" />
</head>
<body>
<c:if test="${!empty command.result }">
	<div id="warning">
		${command.result }
	</div>
</c:if>
<div id="data">
	<f:form commandName="model" action="fill" method="post" id="form1">
		<table width="95%" border="0" align="left" cellpadding="3"
			cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th>补全用户相册</th>
			</tr>
		</thead>
		<tr>
			<td>用户名：<f:input path="username"/><span class="errorSpan"><f:errors path="username"/></span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="确定" />
			</td>
		</tr>
		</table>
	</f:form>
</div>
</body>
</html>