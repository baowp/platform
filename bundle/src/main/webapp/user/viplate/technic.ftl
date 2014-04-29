<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${action.metaTitle!'' }</title>
<meta name="description" content="${action.metaDescription!'' }">
<meta name="keywords" content="${action.metaKeywords!'' }">
<#include "page_style.ftl">
</head>
<body class="outBg">
<center>
<div class="inBg">	
	<#if layout.layoutId??>
		<#include "page_top.ftl">
		<#include "page_head.ftl">
		<#assign defaultMain="technic_default_main.ftl">
		<#include "page_content.ftl">
		<#include "page_below.ftl"/>
		<#include "page_foot.ftl">
	<#else>
		<#include "page_top.ftl"/>
		<#include "default_page_head.ftl"/>
		<#include "technic_default_content.ftl"/>
		<#include "page_below.ftl"/>
		<#include "page_foot.ftl"/>
	</#if>
</div>
</center>
</body>
</html>
