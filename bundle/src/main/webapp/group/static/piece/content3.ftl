<#assign sign=enums["com.abbcc.util.constant.group.GroupPosition"].content3>
<div id='${sign }' class="${sign } filter filterDom sort${vs} moveList" style="width: ${(listWidth.three)!''}px;">
	<#if content3??>
		<#list content3 as laymod>
			<#assign laymod=laymod>
			<#include laymod.module.msignFtl>
		</#list>
	</#if>
</div>