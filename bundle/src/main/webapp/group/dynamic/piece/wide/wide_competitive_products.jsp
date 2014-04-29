<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_competitive_products'].name()}" />
<c:if test="${empty competitiveProducts}">
	${command.pieceCompetitiveProducts() }
</c:if>

<div id="competitiveProducts" class="bodyCont moveChild glare_type_1"
	data-piece="${sign }">

<div class="clr"></div>
<div class="bodyContTitle"><span
	class="fl b titleLinkColor titleName"> ${moduleMap[sign] } </span> <c:if
	test="${ belongPage ne 'product'}">
	<a class="fr fs12px nb titleLinkColor draft_no_link" href="product">${lan['more']}
	&gt;&gt;</a>
</c:if></div>
<div class="bodyContContent rightConteWidth rel">
<div class="glitzBody">
<ul>
	<c:forEach items="${competitiveProducts.items }" var="c">

		<li class="glitzItem ${loginView eq 1?'isLoged':''}">
		<div class="glitzPic glitzBorder glitzColor"><a
			href="product_detail?productId=${c.productId}" target="_blank"> <img
			border="0" src="${c.mainPic.picUrl(5) }" /> </a>
		<div class="imgBorder"></div>
		</div>
		<div class="txt"><a class="topicLink draft_no_link"
			target="_blank" href="product_detail?productId=${c.productId}">${c.name
		}</a></div>
		<span class="price"></span></li>
	</c:forEach>
</ul>
</div>
</div>
</div>