<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_recruit'].name()}"/>
${command.pieceRecruit() }
<div id="recruit" class="bodyCont moveChild" data-piece="${sign }">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign] }
		</span>
		<c:if test="${belongPage ne 'recruit' }">
			<a class="fr fs12px nb titleLinkColor draft_no_link" href="recruit">${lan['more']} &gt;&gt;</a>
		</c:if>
	</div>
	<div class="bodyContContent rel rightConteWidth" data-meta='{"currentPage":${recruit.currentPage },"pageCount":${recruit.pageCount },"url":"${sign }"}'>
		<div class="tal mainTextColor break recruit">
			<c:forEach items="${recruit.items }" var="c">
				<dl class="glitzColor recruitDl">
					<dt class="title">${c.title }</dt>
					<dt>${lan['recruit.duty']}${c.duty } ${lan['recruit.sum']}${c.sum }</dt>
					<dt>${lan['recruit.addTime']}
					<fmt:formatDate value="${c.addTime }" pattern="yyyy-MM-dd hh:mm:ss"/>
					<dt class="fl">${lan['recruit.requirement']}</dt>
					<dd>${c.content }</dd>
				</dl>
			</c:forEach>
		</div>
			<c:set var="pageList" value="${recruit }" scope="request"/>
					<jsp:include page="../../pagination.jsp"></jsp:include>
		<div class="clr"></div>
	</div>
</div>