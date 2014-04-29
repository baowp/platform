<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_intro'].name()}"/>
<div id="companyIntro" class="bodyCont moveChild" data-piece="${sign}">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign] }
		</span>
		<c:if test="${ belongPage ne 'company'}">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="company">${lan['more']} &gt;&gt;</a>
		</c:if>
	</div>
	<div class="bodyContContent rel rightConteWidth">
	<div class="bodyContContentRightCont fl tal mainTextColor break">
	  <span>${enterprise.edesc }</span>
	</div>
	<div class="clr"></div>
	</div>
</div>