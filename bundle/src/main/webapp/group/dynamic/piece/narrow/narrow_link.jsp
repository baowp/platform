<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_link'].name()}" />
<c:if test="${empty narrowLink}">
	${command.pieceLink() }
</c:if>
<div id="side_link" class="bodyCont moveChild mainTextColor"
	data-piece="${sign }">
	<link rel="stylesheet" href="/group/dynamic/css/sidelink.css"></link>
	<div class="clr"></div>

	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]}
		</span>
	</div>
	<div class="bodyContContent">
		<ul>
			<c:forEach items="${narrowLink}" var="c">
				<li class="friendLinkLi"><a class="topicLink draft_no_link"
					target="_blank" href="${c.url}">${c.name}</a>
				</li>
			</c:forEach>

		</ul>
	</div>
</div>