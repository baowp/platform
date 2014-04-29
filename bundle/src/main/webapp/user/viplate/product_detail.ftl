<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#assign product=action.root.product>
<title>${product.name!""} ${user.getEnterprise().name!'' }</title>
<meta name="keywords" content="${product.pkey!'' }">
<#include "page_style.ftl">
</head>
<body class="outBg">
<center>
<script>
$(function(){
	$.ajax({
		type: "get",
		dataType : "script",
		url: "http://${product.domain}/product/addNumber",
		data:{
			 id:"${product.productId}"
			},
		success: function(data){}
	})
})
</script>
<div class="inBg">	
	<#if layout.layoutId??>
		<#include "page_top.ftl">
		<#include "page_head.ftl">
		<#assign defaultMain="product_detail_default.ftl">
		<#include "page_content.ftl">
		<#include "page_below.ftl"/>
		<#include "page_foot.ftl">
	<#else>
		<#include "page_top.ftl"/>
		<#include "default_page_head.ftl"/>
		<#assign defaultMain="product_detail_default.ftl">
		<#include "product_default_content.ftl"/>
		<#include "page_below.ftl"/>
		<#include "page_foot.ftl"/>
	</#if>
</div>
</center>
</body>
</html>
