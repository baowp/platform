<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_link>
<#if !command.take("narrowLink")??>
	${command.pieceLink() }
</#if>
<#assign narrowLink=command.take("narrowLink")>
<div id="side_link" class="bodyCont moveChild mainTextColor">
	<link rel="stylesheet" href="css/sidelink.css"></link>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!''}
		</span>
	</div>
	<div class="bodyContContent">
		<ul>
			<#list narrowLink as c>
				<li class="friendLinkLi"><a class="topicLink draft_no_link" target="_blank" href="${c.url!''}">${c.name!''}</a>
				</li>
			</#list>
		</ul>
	</div>
</div>