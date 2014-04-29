<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String a = "ddd";
	request.setAttribute("a",a);
	%>
	<table border="1">
		<tr>
			<td>1</td>
			<td>2</td>
			<c:if test="${a=='bc' || a=='cd' }">
			<td>dddd</td>
			</c:if>
		</tr>
	</table>

</body>
</html>