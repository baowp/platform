<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="/user/vipsite/css/product.simple.css" rel="stylesheet" />
<script type="text/javascript">
productId="${product.productId}"
</script>
<script type="text/javascript" src="/user/vipsite/js/product.simple.js"></script>
<div class="bodyCont moveChild mainTextColor">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@PRODUCT_SIMPLE1}"/>
	<div class="wangpuBodyContTitle glitzColor">
    	<span class="b wangpuTopTitle">${product.name}</span><span style="font-size:10px; margin-left:10px;">(${lan['product.hits']}:<font color="red" id="viewsum">${viewsum}</font>,<a href="javascript:" id="collectByPro">${lan['contacts.collectByPro']}</a>)</span>&nbsp;
    </div>
    <div class="summary">
        <div class="gallery">
			<ul class="thumb thumb-up clearfix" id="UlThumb">
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
    		<div class="booth pic s600">
				<a>
			 		<img src="<s:property value="product.mainPic.picUrl(3)"/>" id="booth">
				</a>
			</div>
    	</div>
   </div>
   
   <div class="productType">
   		${lan['product.prodtype']}：${product.prodtype }
   </div>
   <div class="productDetailTitle">
<!--    		详细信息： -->
   </div>
    <div class="productDes">
		${product.proddesc }
	</div>
	<jsp:include page="product_detail.bottom.jsp"/>
</div>