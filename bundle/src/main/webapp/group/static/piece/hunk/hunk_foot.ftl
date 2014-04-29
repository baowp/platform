<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].hunk_foot>
<#if !command.take("navigator")??>
	${command.pieceNavigator() }
</#if>
<#assign navigator=command.take("navigator")>
<div class="mod-footer bodyCont glitzColor mainTextColor moveChild">
	<div class="footerCont">
		<p>&nbsp;</p>
		<p>
			<#list navigator as nav>
				<a class="topicLink" href="${nav.page!''}.html">${nav.name!'' }</a>
				<#if nav_has_next>|</#if>
			</#list>
		</p>
		<p>${enterprise.name!'' } ${lan['address']!''}${address!'' }</p>
		<p>
			<a href="http://www.miibeian.gov.cn/" target="_blank">${enterprise.icp!'' }</a>
				&nbsp;&nbsp;
				${lan['support']!''}
				<a target="_blank" href="http://51archetype.com" class="draft_no_link topicLink">${lan['domain.home'] }</a>
				<#if !analysisList??>
					${command.analysis(enterprise.enterpriseId) }
				</#if>
				<#if analysisList??>
					<#list analysisList as c>
						<span class="analysis_tool">${c!'' }</span>
					</#list>
				</#if>
		</p>
	</div>
</div>
