<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${pageList.pageCount>1}">
	<c:set var="lan" value="${spel['T(com.abbcc.common.CommonConst).languagePack']}"/>
<div class="pagination mainTextColor">
	<div class="fl">
		<c:if test="${pageList.currentPage!=1 }">
			<a class="danaiPageUp" href="javascript:prevPage()">${lan['pagination.prev']}</a>
		</c:if>
		${command.pagination(pageList.currentPage,pageList.pageCount) }
		<c:forEach items="${pagination }" var="page">
			<c:choose>
				<c:when test="${page eq pageList.currentPage }">
					<span class="danaiPageCurrent">${page }</span>
				</c:when>
				<c:otherwise>
					<a href="javascript:toPage(${page })" class="danaiPageNum">${page }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageList.currentPage ne pageList.pageCount }">
			<a class="danaiPageDown" href="javascript:nextPage()">${lan['pagination.next']}</a>
		</c:if>
	</div>
	<div class="fl danaiPageDes">
		<span>${pageList.currentPage }/${pageList.pageCount }</span>${lan['pagination.page']}
		<c:if test="${pageList.pageCount>1 }">
			${lan['pagination.to']}<input id="pageNum" type="text" size="3"/>${lan['pagination.page']} 
			<input type="button" onclick="toPage(this)" value="${lan['pagination.agree']}">
		</c:if>	
	</div>
</div>
</c:if>