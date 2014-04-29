<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<c:forEach items="${content}" var="laymod" varStatus="vs">
		<c:set var="vs" value="${vs }" scope="request"/>
		<jsp:include page="${laymod.module.msignJsp }"/>
	</c:forEach>
</div>