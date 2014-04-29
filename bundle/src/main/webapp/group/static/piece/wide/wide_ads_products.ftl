<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_ads_products>
<#if !command.take("adsProducts")??>
	${command.pieceAdsProducts() }
</#if>
<#assign adsProducts=command.take("adsProducts")>
<div id="main_ads_products" class="bodyCont moveChild mainTextColor">
<link rel="stylesheet" type="text/css" href="css/main_ads.css" />
<script type="text/javascript" src="js/site/jcarousel.min.js"></script>
	<div class="clr"></div>	
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			 ${moduleMap[sign]!'' }
		</span>
	</div>
	<div class="bodyContContent rightConteWidth">
		<ul id="carousel" class="glitzPic">
		<#list adsProducts.items as ads>
			<li><a href="product_detail?productId=${ads.productId!''}" target="_blank"> <img border="0"
				src="${ads.mainPic.picUrl(5)!''}" alt="${ads.name!''}" title="${ads.name!''}"/> </a></li>
		</#list>
		</ul>
	</div>
	<script type="text/javascript" src="js/site/main_ads.js"></script>
	<!--[if lte IE 6]>
	<script src="js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
    <script type="text/javascript">
        DD_belatedPNG.fix('.jcarousel-prev,.jcarousel-next');
    </script>
	<![endif]-->
</div>