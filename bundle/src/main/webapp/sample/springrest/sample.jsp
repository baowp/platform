<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@	taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>springmvc sample</div>
	<div>${sampleController.result }</div>
	<div>${user.username } ${abcUser.name }</div>
	<div>${command.getStr('name4') } ${name4}</div>
	<div>${model.name}</div>
	<div>
		<c:forEach var="brand" items="${list}">
			<div>${brand.name }</div>
		</c:forEach>
	</div>
	<div>
		<f:form commandName="model" action="index" method="get">
			<input type="hidden" name="id" value="${id }">
			<f:input path="name" />
			<input type="submit">
		</f:form>
	</div>
</body>
</html>