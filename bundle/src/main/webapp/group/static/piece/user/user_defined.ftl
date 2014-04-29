<#assign sign=laymod.module.moduleId!''>
<#if !command.take(sign)??>
	${command.pieceUserDefined(sign,null) }
</#if>
<#assign ud=command.take(sign)>

<div id="${sign }" class="bodyCont moveChild mainTextColor user_defined">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">${moduleMap[sign]!'' }</span>
	</div>
	<div class="bodyContContent">${ud.content!'' }</div>
</div>