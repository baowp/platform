<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#assign supply=action.root.supply>
<title>[${supply.type!''}]${supply.title!''} ${user.getEnterprise().name!'' }</title>
<meta name="keywords" content="${supply.skey!'' }">
<#include "page_style.ftl">
</head>
<body class="outBg">
<center>
<div class="inBg">	
	<#if layout.layoutId??>
		<#include "page_top.ftl">
		<#include "page_head.ftl">
		<#assign defaultMain="supply_in_detail.ftl">
		<#include "page_content.ftl">
		<#include "page_below.ftl"/>
		<#include "page_foot.ftl">
	<#else>
		<#include "page_top.ftl"/>
		<#include "default_page_head.ftl"/>
		<#assign defaultMain="supply_in_detail.ftl">
		<#include "supply_default_content.ftl"/>
		<#include "page_below.ftl"/>
		<#include "page_foot.ftl"/>
	</#if>
</div>
</center>
</body>
</html>
