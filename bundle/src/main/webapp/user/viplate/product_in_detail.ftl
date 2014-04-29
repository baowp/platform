<#assign product=action.root.product>
<link type="text/css" href="css/site/product.css" rel="stylesheet" />
<script type="text/javascript" src="js/site/vip.product.js"></script>
<div class="bodyCont mainTextColor">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b titleName wangpuTopTitle">${product.name!""}</span>&nbsp;
    </div>
     <div class="summary">
        <div class="gallery">
    		<div class="booth pic s300">
				<a>
			 		<img src="${(product.mainPic.picUrl(4))!""}" id="booth">
				</a>
				<div id="zoomIcon" class="zoom-icon none"></div> 
				<div class="zoomer hidden"></div>
			</div>
			<ul class="thumb clearfix" id="UlThumb">
				<li class="selected">
					<div class="pic s40">
						<a href="#"><img src="${(product.mainPic.picUrl(6))!""}"></a>
					</div>
				</li>
				<#if product.attachList??>
				<#list product.attachList as item>
				<li>
					<div class="pic s40">
						<a href="#"><img src="${item.picUrl(6)!""}"></a>
					</div>
				</li>
				</#list>
				</#if>
			</ul>
    	</div>
    	<div class="zooming hidden">
    		<div>
    			<img src="">
    		</div>
    	</div>
    	
  		<ul id="detailCtn">
			<li class="row4 row7">
	           <p>${lan['product.address']} <span class="wordwarp">${user.getEnterprise().address!''}</span></p>
											
				<p>${lan['product.unpublishTime']}<span><#if product.unpublishTime??>${product.unpublishTime?string(lan['product.format'])}</#if></span></p>
	        </li>      
	        <li class="nopaybt glitzColor">
				<div class="nopaywarp">
					<ul class="nopaybox">
						<li class="nopaytip"></li>
						<li class="nopayww">
							<a target="_blank" class="nopaycontact" href="contact.html">${lan['product.contact']}</a>
						</li>
					</ul>
				</div>
			</li>
      	</ul>
   </div>
   
   <table cellspacing="0" cellpadding="0" border="0" width="100%" id="table4_new">
   		<tbody>
   			<tr>
   				<td width="60%" valign="top" rowspan="2">
   					<table cellspacing="0" cellpadding="0" border="0" align="center" width="97%">
   						<tbody>
   							<tr>
   								<td width="93" class="wangpuOrangebaron">${lan['product.detail']}</td>
   								<td width="546" class="bfInfo"></td>
   								<td width="120"></td>
   							</tr>
   						</tbody>
   					</table>
   					<table cellspacing="0" cellpadding="0" border="0" align="center" width="97%" id="table11">
    					<tbody><tr>
    						<td height="3" bgcolor="#ff7300"></td>
    					</tr>
    				</tbody></table>
    				<br/>
    				<center>
    					<table cellspacing="1" cellpadding="1" border="0" width="97%" style="color: rgb(0, 0, 0);" class="wangpuAtttilist">
							<tbody>
								<tr>
									<td width="15%" class="title">${lan['product.prodtype']}</td>
									<td width="35%">${product.prodtype!'' }</td>
									<td width="15%" class="title">${lan['price']}</td>
									<td width="35%">${product.price!'' }</td>
								</tr>
								<tr>
									<td width="15%" class="title">${lan['unit']}</td>
									<td width="35%">${product.unit!'' }</td>
									<td width="15%" class="title">&nbsp;</td>
									<td width="35%">&nbsp;</td>
								</tr>
							</tbody>
						</table>
    				</center>
    				<div class="productDes">
						${product.proddesc!'' }
					</div>
   				</td>
   			</tr>
   		
   		</tbody>
   </table>
</div>