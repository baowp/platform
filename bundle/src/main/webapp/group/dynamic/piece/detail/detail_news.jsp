<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).detail_news'].name()}"/>
<c:if test="${empty news}">
	${command.pieceNewsDetail(itemId) }
</c:if>
<div class="bodyCont moveChild mainTextColor detail" data-piece="${sign }">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b wangpuTopTitle">${news.title}</span>&nbsp;
    </div>
    <div style="margin: 8px auto; min-height: 320px; width: 700px;">
		<div class="newsInfo">
			<span>${lan['news.origin']}${news.origin}</span>
			<span>${lan['news.author']}${news.author}</span>
			<span>${lan['news.addTime']}<fmt:formatDate value="${news.addTime }" pattern="${lan['news.format']}"/></span>
		</div>
		<div class="newsContent">${news.content }</div>
    </div>   
</div>