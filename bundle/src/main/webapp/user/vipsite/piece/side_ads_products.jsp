<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/user/vipsite/js/side_ads.js"></script>
<link rel="stylesheet" type="text/css" href="/user/vipsite/css/side_ads.css" />
<s:if test="#request['sideAdsProducts']==null">
		<s:action name="*/pieceSideAdsProducts" namespace="/vip" >
			<s:param name="enterpriseId" value="enterpriseId"/>
		</s:action>
</s:if>
<div id="side_ads_products" class="bodyCont moveChild mainTextColor">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@SIDE_ADS_PRODUCTS}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@SIDE_ADS_PRODUCTS.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@SIDE_ADS_PRODUCTS.name()]"/>
		</span>
	</div>
<div class="bodyContContent  gabcont">
	<div class="glitzBody" id="gallery">
	<div id="slides">
		<s:iterator value="#request.sideAdsProducts.items">
			<div class="slide" >
			<a href="product_detail?productId=${productId}" target="_blank"> <img border="0"
				src="<s:property value="mainPic.picUrl(5)"/>" alt="${name}" /> </a>
			</div>
       </s:iterator>
     </div>
	 <div id="menu">
		<ul>
				<s:iterator value="#request.sideAdsProducts.items">
				<li class="menuItem">
        			<a href="">
        			<img src="<s:property value="mainPic.picUrl(6)"/>"  alt="${name}" /></a>
        			</li>
				</s:iterator>
		</ul>	
	 </div>
	 </div>
</div>
</div>