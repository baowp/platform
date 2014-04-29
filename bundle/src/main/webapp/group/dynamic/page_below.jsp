<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id='${spel["T(com.abbcc.util.constant.group.GroupPosition).belowList"]}' class="belowList hunkList">
	<c:forEach items="${belowList }" var="laymod">
		<c:set var="laymod" value="${laymod }" scope="request"/>
		<c:import url="${laymod.module.msignJsp }"/>
	</c:forEach>
</div>