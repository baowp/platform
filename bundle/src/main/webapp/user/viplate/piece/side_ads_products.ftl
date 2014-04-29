${action.pieceSideAdsProducts()}
<#assign products=action.root.sideAdsProducts>
<script type="text/javascript" src="/js/site/side_ads.js"></script>
<link rel="stylesheet" type="text/css" href="css/site/side_ads.css" />
<div id="side_ads_products" class="bodyCont moveChild mainTextColor">
	<div class="clr"></div>
		<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].SIDE_ADS_PRODUCTS.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].SIDE_ADS_PRODUCTS.name()])}
		</span>
		 </div>
    <div class="bodyContContent gabcont">
  		<div class="glitzBody" id="gallery">
  		<div id="slides">
  		<#list products.items as item>
			<div class="slide" >
			<a href="product-detail-${item.productId2}.html" target="_blank">
			<img border="0" src="${(item.mainPic.picUrl(5))!''}" alt="${item.name!''}" />
		</a>
			</div>
       </#list>
     </div>
	 <div id="menu">
		<ul>
		<#list products.items as item>
				<li class="menuItem">
        			<a href="">
        			<img src="${(item.mainPic.picUrl(6))!''}"  alt="${item.name!''}" /></a>
        			</li>
				</#list>
		</ul>	
	 </div>
         </div>
     </div>
</div>
