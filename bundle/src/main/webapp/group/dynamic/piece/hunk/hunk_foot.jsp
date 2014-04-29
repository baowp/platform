<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).hunk_foot'].name()}" />
<div class="mod-footer bodyCont glitzColor mainTextColor moveChild"
	data-piece="${sign }">
	<div class="footerCont">
		<p>&nbsp;</p>
		<p>
			<c:if test="${empty navigator}">
				${command.pieceNavigator() }
			</c:if>
			<c:forEach items="${navigator }" var="nav" varStatus="st">
				<a class="topicLink" href="${nav.page}">${nav.name }</a>
				<c:if test="${!st.last }">|</c:if>
			</c:forEach>
		</p>
		<p>${enterprise.name } ${lan['address']}${address }</p>
		<p>
			<a href="http://www.miibeian.gov.cn/" target="_blank">${enterprise.icp
				}</a> &nbsp;&nbsp; ${lan['support']} <a target="_blank"
				href="http://51archetype.com" class="draft_no_link topicLink">${lan['domain.home']
				}</a>
			<c:if test="${empty analysisList }">
				${command.analysis(enterprise.enterpriseId) }
			</c:if>
			<c:forEach items="${analysisList }" var="c">
				<span class="analysis_tool">${c }</span>
			</c:forEach>
		</p>
	</div>
</div>
