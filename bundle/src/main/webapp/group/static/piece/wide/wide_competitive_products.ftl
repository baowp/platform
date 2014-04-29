<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_competitive_products>
<#if !competitiveProducts??>
	${command.pieceCompetitiveProducts() }
</#if>
<#assign competitiveProducts=command.take("competitiveProducts")>

<div id="competitiveProducts" class="bodyCont moveChild glare_type_1">
<div class="clr"></div>
<div class="bodyContTitle">
<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!'' } </span>
 <#if belongPage != 'product'>
	<a class="fr fs12px nb titleLinkColor draft_no_link" href="product">${lan['more']}&gt;&gt;</a>
</#if></div>
<div class="bodyContContent rightConteWidth rel">
<div class="glitzBody">
<ul>
	<#list competitiveProducts.items as c>
		<li class="glitzItem <#if c.loginView='1'>isLoged</#if>">
		<div class="glitzPic glitzBorder glitzColor"><a
			href="product_detail?productId=${c.productId!''}" target="_blank"> <img
			border="0" src="${c.mainPic.picUrl(5)!'' }" /> </a>
		<div class="imgBorder"></div>
		</div>
		<div class="txt"><a class="topicLink draft_no_link"
			target="_blank" href="product_detail?productId=${c.productId!''}">${c.name!''}</a></div>
		<span class="price"></span></li>
	</#list>
</ul>
</div>
</div>
</div>