<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign"
	value="${spel['T(com.abbcc.util.constant.group.GroupPiece).narrow_ads_products'].name()}" />
<c:if test="${empty sideAdsProducts}">
	${command.pieceSideAdsProducts()}
</c:if>

<div id="side_ads_products" class="bodyCont moveChild mainTextColor"
	data-piece="${sign }">
<script type="text/javascript" src="/group/dynamic/js/side_ads.js"></script>
<link rel="stylesheet" type="text/css" href="/group/dynamic/css/side_ads.css" />
<div class="clr"></div>
<div class="bodyContTitle"><span
	class="fl b titleLinkColor titleName"> ${moduleMap[sign] } </span></div>
<div class="bodyContContent  gabcont">
<div class="glitzBody" id="gallery">
<div id="slides"><c:forEach items="${sideAdsProducts.items}" var="ads">
	<div class="slide"><a
		href="product_detail?productId=${ads.productId}" target="_blank"> <img
		border="0" src="${ads.mainPic.picUrl(5)}" alt="${ads.name}" />
	</a></div>
</c:forEach></div>
<div id="menu">
<ul>
	<c:forEach items="${sideAdsProducts.items}" var="nads">
		<li class="menuItem"><a href=""> <img
			src="${nads.mainPic.picUrl(6) }" alt="${nads.name}" /></a></li>
	</c:forEach>
</ul>
</div>
</div>
</div>
</div>