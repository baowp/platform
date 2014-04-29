<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPosition).content2']}"/>
<div id='${sign }' class="${sign } filter filterDom sort${vs.count} moveList" style="width: ${listWidth.two}px;" data-piece="${sign }">
	<c:forEach items="${content2}" var="laymod">
		<c:set var="laymod" value="${laymod }" scope="request"/>
		<jsp:include page="${laymod.module.msignJsp }"/>
	</c:forEach>
</div>
