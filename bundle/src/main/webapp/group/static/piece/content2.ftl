<#assign sign=enums["com.abbcc.util.constant.group.GroupPosition"].content2>
<div id='${sign }' class="${sign } filter filterDom sort${vs} moveList" style="width: ${(listWidth.two)!''}px;">
	<#if content2??>
		<#list content2 as laymod>
			<#assign laymod=laymod>
			<#include laymod.module.msignFtl>
		</#list>
	</#if>
</div>
