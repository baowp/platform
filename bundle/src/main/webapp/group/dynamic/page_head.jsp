<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div id='${spel["T(com.abbcc.util.constant.group.GroupPosition).headList"]}' class="headList hunkList">
		<c:forEach items="${headList}" var="laymod">
			<c:set var="laymod" value="${laymod }" scope="request"/>
			<jsp:include page="${laymod.module.msignJsp }"/>
		</c:forEach>
	</div>