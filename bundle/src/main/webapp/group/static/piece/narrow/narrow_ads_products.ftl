<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_ads_products>
<#if !command.take("sideAdsProducts")??>
	${command.pieceSideAdsProducts() }
</#if>
<#assign sideAdsProducts=command.take("sideAdsProducts").items>
<div id="side_ads_products" class="bodyCont moveChild mainTextColor">
<script type="text/javascript" src="js/site/side_ads.js"></script>
<link rel="stylesheet" type="text/css" href="css/side_ads.css" />
<div class="clr"></div>
<div class="bodyContTitle">
	<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!'' } </span></div>
<div class="bodyContContent  gabcont">
<div class="glitzBody" id="gallery">
<div id="slides">
	<#list sideAdsProducts as ads>
		<div class="slide">
		<a href="product_detail?productId=${ads.productId!''}" target="_blank">
			 <img border="0" src="${ads.mainPic.picUrl(5)!''}" alt="${ads.name!''}" />
		</a></div>
	</#list>
	</div>
<div id="menu">
<ul>
	<#list sideAdsProducts as nads>
		<li class="menuItem"><a href=""> <img
		src="${nads.mainPic.picUrl(6)!'' }" alt="${nads.name!''}" /></a></li>
	</#list>
</ul>
</div>
</div>
</div>
</div>