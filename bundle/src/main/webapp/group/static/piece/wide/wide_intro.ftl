<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_intro>
<div id="companyIntro" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign]!'' }
		</span>
		<#if belongPage??>
			<#if belongPage != 'company'>
				<a class="fr fs12px nb titleLinkColor draft_no_link" href="company">${lan['more']} &gt;&gt;</a>
			</#if>
		</#if>
	</div>
	<div class="bodyContContent rel rightConteWidth">
	<div class="bodyContContentRightCont fl tal mainTextColor break">
	  <span>${enterprise.edesc!'' }</span>
	</div>
	<div class="clr"></div>
	</div>
</div>