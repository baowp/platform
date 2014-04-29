<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request['adsProducts']==null">
		<s:action name="*/pieceAdsProducts" namespace="/vip" >
			<s:param name="enterpriseId" value="enterpriseId"/>
		</s:action>
</s:if>
<div id="main_ads_products" class="bodyCont moveChild mainTextColor">
	<link rel="stylesheet" href="/user/vipsite/css/main_ads.css"/>
	<script type="text/javascript" src="/js/jquery/jcarousel.min.js"></script>
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_ADS_PRODUCTS}"/>
	<div class="clr"></div>	
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_ADS_PRODUCTS.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_ADS_PRODUCTS.name()]"/>
		</span>
	</div>
	<div class="bodyContContent rightConteWidth">
		<ul id="carousel" class="glitzPic">
			<s:iterator value="#request.adsProducts.items">
			<li><a href="product_detail?productId=${productId}" target="_blank"> <img border="0"
				src="<s:property value="mainPic.picUrl(5)"/>" alt="${name}" title="${name}"/> </a></li>
			</s:iterator>
		</ul>
	</div>
	<script type="text/javascript" src="/user/vipsite/js/main_ads.js"></script>
	<s:if test="!maintainable">
	<!--[if lte IE 6]>
	<script src="/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('.jcarousel-prev,.jcarousel-next');
    </script>
	<![endif]-->
	</s:if>
</div>