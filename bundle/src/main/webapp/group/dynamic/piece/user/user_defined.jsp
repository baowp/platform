<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${!empty laymod.module.moduleId?laymod.module.moduleId:sign}" />
<c:if test="${empty requestScope[sign] }">
	${command.pieceUserDefined(sign,null) }
</c:if>
<c:set var="ud" value="${requestScope[sign]}" />
<div id="${sign }" class="bodyCont moveChild mainTextColor user_defined"
	data-piece="${sign }" data-definedId="${ud.userdefinedId }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">${moduleMap[sign] }</span>
	</div>
	<div class="bodyContContent">${ud.content }</div>
</div>