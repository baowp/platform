<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_products>
${command.pieceProducts() }
<#assign products=command.take("products")>

<div id="products" class="bodyCont moveChild glare_type_1" data-piece="${sign }">
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					${moduleMap[sign]!'' }
				</span>
				<#if belongPage != 'product'>
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="product.html">${lan['more']} &gt;&gt;</a>
				</#if>
			</div>																																					
			<div class="bodyContContent rightConteWidth rel" data-meta='{"currentPage":${products.currentPage!''},"pageCount":${products.pageCount!''},"parentValue":"${command.take("categoryId")!'' }","url":"${sign!''}"}'>
				<div class="glitzBody">
					<ul>
					<#list products.items as p>
						<li class="glitzItem <#if p.loginView='1'>isLoged</#if>">
							<div class="glitzPic glitzBorder glitzColor">
								<a href="product-detail-${p.productId2}.html" target="_blank">
									<img border="0" src="${p.mainPic.picUrl(5)!''}" />
								</a>
								<div class="imgBorder"></div>
							</div>
							<div class="txt">
								<a class="topicLink draft_no_link" target="_blank" href="product-detail-${p.productId2}.html">${p.name!'' }</a>
							</div>
							<span class="price"></span>
						</li>
					</#list>
					</ul>
				</div>
				<#if belongPage == 'product'>
					<#assign pageList=products>
					<#include "../../pagination.ftl">
				</#if>
			</div>
</div>