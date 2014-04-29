<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="/user/vipsite/css/product.simple.css" rel="stylesheet" />
<script type="text/javascript" src="/user/vipsite/js/product.simple.js"></script>
<div class="bodyCont mainTextColor">
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b titleName wangpuTopTitle">${product.name}</span>&nbsp;
    </div>
    <div class="summary">
        <div class="gallery">
    		<div class="booth pic s600">
				<a>
			 		<img src="<s:property value="product.mainPic.picUrl(3)"/>" id="booth">
				</a>
			</div>
			<ul class="thumb clearfix" id="UlThumb">
				<li class="selected">
					<div class="pic s160 glitzPic glitzBorder glitzColor">
						<a href="#"><img src="<s:property value="product.mainPic.picUrl(5)"/>"></a>
					</div>
				</li>
				<s:iterator value="product.attachList">
				<li>
					<div class="pic s160 glitzPic glitzBorder glitzColor">
						<a href="#"><img src="<s:property value="picUrl(5)"/>"></a>
					</div>
				</li>
				</s:iterator>
			</ul>
    	</div>
   </div>
   
   <div class="productType">
   		产品型号：${product.prodtype }
   </div>
   <div class="productDetailTitle">
   		详细信息：
   </div>
    <div class="productDes">
		${product.proddesc }
	</div>
</div>