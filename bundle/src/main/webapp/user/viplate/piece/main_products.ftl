	${action.pieceProducts()}
	<#assign products=action.root.products>
		<div id="products" class="bodyCont moveChild glare_type_1">
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_PRODUCTS.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_PRODUCTS.name()])}
				</span>
				<#if layout.belongPage!=enums["com.abbcc.util.constant.layout.BelongPage"].PRODUCT>
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="product.html">${lan['more']} &gt;&gt;</a>
				</#if>
			</div>
			<div class="bodyContContent rightConteWidth rel">
				<input type=hidden id="meta" class="{currentPage:${products.currentPage},pageCount:${products.pageCount},parentValue:'${action['categoryId']!''}'}"/>
				<div class="glitzBody">
					<ul>
					<#list products.items as item>
						<li class="glitzItem <#if item.loginView='1'>isLoged</#if>">
							<div class="glitzPic glitzBorder glitzColor">
								<a href="product-detail-${item.productId2}.html" target="_blank">
									<img border="0" src="${(item.mainPic.picUrl(5))!''}" />
								</a>
								<div class="imgBorder"></div>
							</div>
							<div class="txt">
							<#if action.bitAnd(setting.pshow,1)>
								<div>
									<a class="topicLink draft_no_link" target="_blank" href="product-detail-${item.productId2}.html">${item.name !''}</a>
								</div>
								
							</#if>
							<#if action.bitAnd(setting.pshow,2)>
								<div>
									<a class="topicLink draft_no_link" target="_blank" href="product-detail-${item.productId2}.html">${item.prodtype!''}</a>
								</div>
							</#if>
							</div>
							<span class="price"></span>
						</li>
					</#list>
					</ul>
				</div>
				<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].PRODUCT>
				<#assign pageList=products>
				<#include "../pagination.ftl">
				</#if>
			</div>
		</div>