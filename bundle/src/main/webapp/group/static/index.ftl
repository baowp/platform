<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${seo.title!'' }</title>
<meta name="description" content="${seo.description!'' }">
<meta name="keywords" content="${seo.keywords!'' }">
<#include "page_style.ftl"/>
</head>
<body>
	<div class="outBg">
		<div class="inBg">
			<#include "page_top.ftl">
			<#include "page_head.ftl"> 
			<#include "page_content.ftl">
			<#include "page_below.ftl">  
		</div>
	</div>
</body>
</html>
