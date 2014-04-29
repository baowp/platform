<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="sign" value="${spel['T(com.abbcc.util.constant.group.GroupPiece).wide_ads_products'].name()}"/>
<c:if test="${empty adsProducts}">
	${command.pieceAdsProducts() }
</c:if>
<div id="main_ads_products" class="bodyCont moveChild mainTextColor" data-piece="${sign }">
	<link rel="stylesheet" type="text/css"
		href="/group/dynamic/css/main_ads.css" />
	<script type="text/javascript" src="/group/dynamic/js/jcarousel.min.js"></script>
	<div class="clr"></div>	
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			 ${moduleMap[sign] }
		</span>
	</div>
	<div class="bodyContContent rightConteWidth">
		<ul id="carousel" class="glitzPic">
		<c:forEach items="${adsProducts.items}" var="ads">
			<li><a href="product_detail?productId=${ads.productId}" target="_blank"> <img border="0"
				src="${ads.mainPic.picUrl(5)}" alt="${ads.name}" title="${name}"/> </a></li>
		</c:forEach>
		</ul>
	</div>
	<script type="text/javascript" src="/group/dynamic/js/main_ads.js"></script>
	<c:if test="!${maintainable}">
	<!--[if lte IE 6]>
	<script src="/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('.jcarousel-prev,.jcarousel-next');
    </script>
	<![endif]-->
	</c:if>
</div>