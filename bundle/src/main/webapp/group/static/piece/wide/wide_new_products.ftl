<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_new_products>
${command.pieceNewProducts() }
<#assign newProducts=command.take("newProducts")>

<div id="newProducts" class="bodyCont moveChild glare_type_1" data-piece="${sign }">
<div class="clr"></div>
<div class="bodyContTitle"><span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!'' } </span>
	 <#if belongPage != 'product'>
		<a class="fr fs12px nb titleLinkColor draft_no_link" href="product">${lan['more']}&gt;&gt;</a>
	 </#if>
	</div>
<div class="bodyContContent rightConteWidth rel">
<div class="glitzBody">
<ul>
	<#list newProducts.items as c>
		<li class="glitzItem <#if c.loginView='1'>isLoged</#if>">
		<div class="glitzPic glitzBorder glitzColor"><a
			href="product-detail-${c.productId2}.html" target="_blank"> <img
			border="0" src="${c.mainPic.picUrl(5)!'' }" /> </a>
		<div class="imgBorder"></div>
		</div>
		<div class="txt"><a class="topicLink draft_no_link"
			target="_blank" href="product-detail-${c.productId2}.html">${c.name!''}</a></div>
		<span class="price"></span></li>
	</#list>
</ul>
</div>
</div>
</div>