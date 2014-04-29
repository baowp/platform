<#assign product=action.root.product>
<link type="text/css" href="css/site/product.simple.css" rel="stylesheet" />
<script type="text/javascript" src="js/site/product.simple.js"></script>
<script type="text/javascript">
productId="${product.productId}"
</script>
<div class="bodyCont moveChild mainTextColor">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b wangpuTopTitle">${product.name!''}</span><span style="font-size:10px; margin-left:10px;">(${lan['product.hits']}:<font color="red" id="viewsum">${product.viewsum!''}</font>,<a href="javascript:" id="collectByPro">${lan['contacts.collectByPro']}</a>)</span>&nbsp;
    </div>
    <div class="summary">
        <div class="gallery">
    		<div class="booth pic s600">
				<a>
			 		<img src="${(product.mainPic.picUrl(3))!""}" id="booth">
				</a>
			</div>
			<ul class="thumb clearfix" id="UlThumb">
				<li class="selected">
					<div class="pic s160 glitzPic glitzBorder glitzColor">
						<a href="#"><img src="${(product.mainPic.picUrl(5))!""}"></a>
					</div>
				</li>
				<#if product.attachList??>
				<#list product.attachList as item>
				<li>
					<div class="pic s160 glitzPic glitzBorder glitzColor">
						<a href="#"><img src="${item.picUrl(5)!""}"></a>
					</div>
				</li>
				</#list>
				</#if>
			</ul>
    	</div>
   </div>
   
   <div class="productType">
   		${lan['product.prodtype']}：${product.prodtype!'' }
   </div>
   <div class="productDetailTitle">
   		
   </div>
    <div class="productDes">
		${product.proddesc!'' }
	</div>
<#include "product_detail.bottom.ftl"/>
</div>