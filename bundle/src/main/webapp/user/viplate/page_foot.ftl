<#if ((setting.footer)!"display")!="none">		
	<div class="mod-footer glitzColor mainTextColor" >
		<p>&nbsp;</p>
		<p>
			<#if layout.navmoduleList?? && layout.navmoduleList?size gt 0>
				<#list layout.navmoduleList as module>
					<a href="${enums["com.abbcc.util.constant.layout.Piece"][module.module.moduleId].action}.html">
						${extract(layout.jsonContent['title'][module.module.moduleId],
							moduleMap[module.module.moduleId])}
					</a> <#if module_has_next>|</#if>
				</#list>
			</#if>
		</p>
		<p>
			${user.getEnterprise().name!"" }
			${lan['address']}${user.address!"" }
		</p>
		<p>
			<a href="http://www.miibeian.gov.cn/" target="_blank">${user.getEnterprise().icp!''}</a>
			&nbsp;&nbsp;
			${lan['support']}
			<span class='dongfang'></span><a target="_blank" href="http://51archetype.com" class="draft_no_link">${lan['domain.home'] }</a>
			<span class="analysis_tool">${layout.jsonFooter['cnzz']!''}</span> 
			<span class="analysis_tool">${layout.jsonFooter['51la']!''}</span>
		</p>
	</div>
</#if>
