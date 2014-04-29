<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_contact'].name()}"/>
<div id="contact_side" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign] }
		</span>
	</div>
	<div class="bodyContContent mainTextColor">
		<ul>
		<li class="contactLi">
			<span id="contactName">
				<a class="topicLink draft_no_link" >${model.name }</a>				
			</span>
			<c:if test="${maintainable }">
				<span class="gaim" id="gaim-qq">
					<a href="#" id="gaimqqLink">
						
					</a>
				</span>
				<span class="gaim">
					<!-- 可点击 <a href="#" onclick="gaim.openMsn()" id="gaimMsnLink"> -->
					<a href="#" id="gaimMsnLink">

					</a>
				</span>
			</c:if>
			<c:if test="${!maintainable }">
				<span class="gaim" id="gaim-qq"></span>
				<span class="gaim"></span>
			</c:if>
		</li>
		<c:if test="${!empty model.phone }">
			<li class="contactLi"><span>${lan['contact.phone']}${model.phone }</span></li>
		</c:if>
		<c:if test="${!empty model.fax }">
			<li class="contactLi"><span>${lan['contact.fax']}${model.fax }</span></li>
		</c:if>
		<c:if test="${!empty model.cellphone }">
			<li class="contactLi"><span>${lan['contact.cellphone']}${model.cellphone }</span></li>
		</c:if>
		<c:if test="${!empty model.url }">
			<li class="contactLi">
			<span>${lan['contact.entUrl']}<a href="http://${model.url2 }" title="${model.url2 }" target="_blank">${model.url2 }</a></span></li>
		</c:if>
		<c:if test="${!empty model.email }">
			<li class="contactLi">
			<span>${lan['contact.email']}<a href="mailto:${model.email}" title="${model.email }">${model.email }</a></span></li>
		</c:if>
		<c:if test="${!empty model.address }">
			<li class="contactLi"><span>${lan['address']}${model.address }</span></li>
		</c:if>
		</ul>
	</div>
	<div class="clr"></div>
</div>