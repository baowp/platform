<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="<s:url value="/user/vipsite/css/product.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/user/js/site/vip.product.js"/>"></script>
<div class="bodyCont mainTextColor">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b titleName wangpuTopTitle">${product.name} - ${product.prodtype }</span>&nbsp;
    </div>
    <div class="summary">
        <div class="gallery">
    		<div class="booth pic s300">
				<a>
			 		<img src="<s:property value="product.mainPic.picUrl(4)"/>" id="booth">
				</a>
				<div id="zoomIcon" class="zoom-icon none"></div> 
				<div class="zoomer hidden"></div>
			</div>
			<ul class="thumb clearfix" id="UlThumb">
				<li class="selected">
					<div class="pic s40">
						<a href="#"><img src="<s:property value="product.mainPic.picUrl(6)"/>"></a>
					</div>
				</li>
				<s:iterator value="product.attachList">
				<li>
					<div class="pic s40">
						<a href="#"><img src="<s:property value="picUrl(6)"/>"></a>
					</div>
				</li>
				</s:iterator>
			</ul>
    	</div>
    	<div class="zooming hidden">
    		<div>
    			<img src="">
    		</div>
    	</div>
    	
  		<ul id="detailCtn">
			<li class="row4 row7">
	           <p>${lan['product.address']} <span class="wordwarp">${enterprise.address}</span></p>
											
				<p>${lan['product.unpublishTime']}<span><s:date name="product.unpublishTime" format="%{lan['product.format']}"/></span></p>
	        </li>      
	        <li class="nopaybt glitzColor">
				<div class="nopaywarp">
					<ul class="nopaybox">
						<li class="nopaytip"></li>
						<li class="nopayww">
							<a target="_blank" class="nopaycontact" href="<s:url value="/contact"/>">${lan['product.contact']}</a>
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
									<td width="35%">${product.prodtype }</td>
									<td width="15%" class="title">${lan['price']}</td>
									<td width="35%">${product.price }</td>
								</tr>
								<tr>
									<td width="15%" class="title">${lan['unit']}</td>
									<td width="35%">${product.unit }</td>
									<td width="15%" class="title">&nbsp;</td>
									<td width="35%">&nbsp;</td>
								</tr>
							</tbody>
						</table>
    				</center>
    				<div class="productDes">
						${product.proddesc }
					</div>
   				</td>
   			</tr>
   		
   		</tbody>
   </table>
</div>