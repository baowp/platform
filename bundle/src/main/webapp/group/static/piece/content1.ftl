<#assign sign=enums["com.abbcc.util.constant.group.GroupPosition"].content1>
<div id='${sign }' class="${sign } filter filterDom sort${vs} moveList" style="width: ${(listWidth.one)!''}px;">
	<#if content1??>	
		<#list content1 as laymod>
			<#assign laymod=laymod>
			<#include laymod.module.msignFtl>
		</#list>
	</#if>
</div>