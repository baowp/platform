<#assign product=action.root.product>
<link type="text/css" href="css/site/product.simple.css" rel="stylesheet" />
<link type="text/css" href="css/site/product_detail1.css" rel="stylesheet" />
<script type="text/javascript" src="js/site/product.simple.js"></script>
<script type="text/javascript" src="js/site/product_detail1.js"></script>
<script type="text/javascript">
	productId="${product.productId}"
</script>
<div class="bodyCont moveChild mainTextColor">
	<div class="tab">
		<span class="b wangpuTopTitle">${product.name!''}</span><span style="font-size:10px; margin-left:10px;">(${lan['product.hits']}:<font color="red" id="viewsum">${product.viewsum!''}</font>,<a href="javascript:" id="collectByPro">${lan['contacts.collectByPro']}</a>)</span>&nbsp;
	</div>
	<div class="cpzs">
		<div class="cpzs_left">
			<div class="datu">
				<a href="#"><img class="divimg" src="${(product.mainPic.picUrl(7))!''}/>"></a>
        	</div>
		</div>
		<div class="cpzs_right">
			<div class="canshu">
				<div class="xinhao">${product.prodtype!''}</div>
				<div class="jiben"></div>
						</div>
			<ul class="jiaodu">
				<li>
				<div class="divImg glitzBorder glitzColor"><img id="0" src="${(product.mainPic.picUrl(8))!''}"/></div>
				</li>
				<#if product.attachList??>
				<#list product.attachList as item>
					<li>
					<div class="divImg glitzBorder glitzColor"><img id="${item_index + 1}" src="${item.picUrl(8)!''}"/></div>
					</li>
				</#list>
				</#if>
			</ul>
		</div>
	</div>
	
	<div class="xxcs">
		<div class="xxcs_hard">
			<#if product.detailTitle1??><div flag="1" id="open" class="xntd glitzColor">${product.detailTitle1!''}</div></#if>
	     	<#if product.detailTitle2??><div flag="2" id="colse" class="xntd glitzColor">${product.detailTitle2!''}</div></#if>
	    	<#if product.detailTitle3??><div flag="3" id="colse" class="xntd glitzColor">${product.detailTitle3!''}</div></#if>
	     </div>
		<#if product.detail1??>
			<div flag="show1"  class="con_xn con glitzColor ">${product.detail1!''}</div>
		</#if>
		<#if product.detail2??>
			<div flag="show2"  class="con_xn con glitzColor " style="display: none;">${product.detail2!''}</div>
		</#if>
		<#if product.detail3??>
			<div flag="show3"  class="con_cs con glitzColor " style="display: none;">
				<table>
					<tbody>
						<tr>
							<td colspan="2" class="xinhao">型号: ${product.prodtype!'' }</td>
						</tr>
						
					<#if product.jsonDetail3??>
					<#list product.jsonDetail3?keys as key>
						<tr>
							<td>${key}</td>
							<td>${product.jsonDetail3[key]!''}</td>
						</tr>
					</#list>
					</#if>
					</tbody>
				</table>
			</div>
		</#if>
		</div>
<#include "product_detail.bottom.ftl"/>
</div>