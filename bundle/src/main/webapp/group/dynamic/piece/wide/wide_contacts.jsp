<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_contacts'].name()}"/>
<c:if test="${empty contactList }">
	${command.pieceContactList() }
</c:if>
<div id="contact_column" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign] }
		</span>
	</div>
	<div class="bodyContContent rightConteWidth_ contacts">
		<c:forEach items="${contactList}" var="c">
			<ul class="mainTextColor contactOne glitzColor">
			<li><span>${lan['contacts.name']}${c.name}</span></li>
			<li><span>${lan['contacts.position']}${c.position}</span></li>
			<li><span>${lan['contacts.phone']}${c.phone}</span></li>
			<li><span>${lan['contacts.fax']} ${c.fax}</span></li>
			<li><span>${lan['contacts.cellphone']} ${c.cellphone}</span></li>
			<li><span>${lan['contacts.email']}${c.email}</span></li>
			<li><span>${lan['contacts.entUrl']}<a target="_blank" href="${c.url }"
				class="topicLink draft_no_link" >${c.url }</a>
				</span>
			</li>
			<c:if test="${!empty c.address }">
				<li><span>${lan['contacts.address']}${c.address}</span></li>
			</c:if>
		</ul>
		</c:forEach>
	</div>
</div>
