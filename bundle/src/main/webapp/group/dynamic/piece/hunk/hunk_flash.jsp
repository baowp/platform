<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).hunk_flash'].name()}"/>
<div id="describe_flash" class="describe_flash moveChild headCont"  data-piece="${sign }">
	<embed id="topic_flash" wmode="transparent"
	 width="952" <c:if test="${not empty layout.jsonSign['topicFlash'] }">src="${layout.jsonSign.topicFlash.src }" height="${layout.jsonSign.topicFlash.height}"</c:if>>
</div>
