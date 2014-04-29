${action.pieceSideLink()}
<#assign sideLink=action.root.sideLink>
<link rel="stylesheet" href="css/site/sidelink.css"></link>
<div id="side_link" class="bodyCont moveChild mainTextColor">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].SIDE_LINK.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].SIDE_LINK.name()])}
		</span>
	</div>
	<div class="bodyContContent">
		<ul>
			<#list sideLink as item>
			<li class="friendLinkLi">
			 <a  class="topicLink draft_no_link" target="_blank" href="${item.url!''}">${item.name!''}</a>
			</li>
			 </#list>
		</ul>
</div>
	</div>
