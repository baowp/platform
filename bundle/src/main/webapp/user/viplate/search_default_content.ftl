<div id="content">
	<div id="content1" class="sideLeft filter filterDom">
		<#include "default_page_side.ftl">		
	</div>
	
	<div id="content2" class="mainRight filter filterDom">
		<#if defaultMain??>
			<#include defaultMain>
		<#else>
			<#include "search_default_main.ftl">	
		</#if>
	</div>
</div>