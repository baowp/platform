<div id="${enums["com.abbcc.util.constant.layout.Position"].headList}" class="headList">
	<#if layout.headmoduleList??>
		<#list layout.headmoduleList as module>
			<#include module.module.msign2>
		</#list>
	</#if>
</div>