${action.pieceAdsProducts()}
<#assign products=action.root.adsProducts>
<div id="main_ads_products" class="bodyCont moveChild mainTextColor">
	<link rel="stylesheet" href="css/site/main_ads.css"/>
	<script type="text/javascript" src="js/jquery/jcarousel.min.js"></script>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_ADS_PRODUCTS.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_ADS_PRODUCTS.name()])}
		</span>
	</div>
	<div class="bodyContContent rightConteWidth">
		<ul id="carousel" class="glitzPic">
					<#list products.items as item>
						<li>
							<a href="product-detail-${item.productId2}.html" target="_blank">
								<img border="0" src="${(item.mainPic.picUrl(5))!''}" alt="${item.name!''}" title="${item.name!''}" />
							</a>
						</li>
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