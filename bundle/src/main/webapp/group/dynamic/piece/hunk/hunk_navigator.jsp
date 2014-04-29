<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty navigator}">
	${command.pieceNavigator() }
</c:if>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).hunk_navigator'].name()}"/>
<div id="top_nav" class="headerMenu moveChild"  data-piece="${sign }">
<link rel="stylesheet" href="/group/dynamic/css/menu.css" />
<script type="text/javascript" src="/js/jquery/superfish/hoverIntent.js"></script>
<script type="text/javascript" src="/js/jquery/superfish/superfish.js"></script>
<script type="text/javascript" src="/group/dynamic/js/menu.js"></script>
	<div class="clr"></div>
	<div>
		<div class="headerMenuBorder headerMenuBg">
			<div class="headerMenuList">
				<ul id='list_nav'>
					<c:forEach items="${navigator }" var="nav">
						<c:set var="nav" value="${nav }" scope="request"/>
						<jsp:include page="nav.jsp"/>
					</c:forEach>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
		<div class="headerMenuBottom">
		</div>
	</div>
</div>