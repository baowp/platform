<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].PRODUCT_DETAIL>
	<#if layout.productmoduleList??>
		<#assign mainmoduleList=layout.productmoduleList>
	</#if>
<#else>
	<#if layout.mainmoduleList??>
		<#assign mainmoduleList=layout.mainmoduleList>
	</#if>
</#if>
<div id="content">
	<#if layout.bodyType!=enums["com.abbcc.util.constant.layout.BodyType"].SR>
		<div id="${enums["com.abbcc.util.constant.layout.Position"].content1}" class="sideLeft filter filterDom">
			<#if layout.sidemoduleList??>
				<#list layout.sidemoduleList as module>
					<#include module.module.msign2>
				</#list>
			</#if>
		</div>		
		<div id="${enums["com.abbcc.util.constant.layout.Position"].content2}" class="mainRight filter filterDom">
			<#if mainmoduleList?? >
				<#list mainmoduleList as module>
					<#include module.module.msign2>
				</#list>
			<#else>
				<#include defaultMain>
			</#if>
		</div>
	<#else>
		<div id="${enums["com.abbcc.util.constant.layout.Position"].content2}" class="mainLeft filter filterDom">
			<#if mainmoduleList?? >
				<#list mainmoduleList as module>
					<#include module.module.msign2>
				</#list>
			<#else>
		          <#include defaultMain>
			</#if>
		</div>		
		<div id="${enums["com.abbcc.util.constant.layout.Position"].content1}" class="sideRight filter filterDom">
			<#if layout.sidemoduleList??>
				<#list layout.sidemoduleList as module>
					<#include module.module.msign2>
				</#list>
			</#if>
		</div>
	</#if>
</div>