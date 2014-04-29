<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_gaim'].name()}" />
<c:if test="${empty narrowGaim}">
	${command.pieceGaim() }
</c:if>
<div id="side_link" class="bodyCont moveChild mainTextColor"
	data-piece="${sign }">
	<link href="/group/dynamic/css/gaim.css" rel="stylesheet" />
	<div class="clr"></div>

	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]}
		</span>
	</div>
	<div class="bodyContContent">
		<ul class="gaim_ul">
			<c:forEach items="${narrowGaim}" var="c">
				<li>
					<div class="gaimName">${c.name }</div>
					<div class="gaimCode">
						<c:choose>
							<c:when test="${empty c.code }">
								<a target="_blank"
									href="http://wpa.qq.com/msgrd?v=3&uin=${c.account }&site=qq&menu=yes"><img
									border="0" src="http://wpa.qq.com/pa?p=2:qqnumber:41"
									alt="${c.account }" title="有事请Q我"> </a>
							</c:when>
							<c:otherwise>
							${c.code }
						</c:otherwise>
						</c:choose>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>