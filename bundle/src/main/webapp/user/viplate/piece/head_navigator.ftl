		<div id="top_nav" class="headerMenu moveChild">
			<div class="clr"></div>
			<div>
				<div class="headerMenuBorder">
					<div class="headerMenuList">
						<ul id='${enums["com.abbcc.util.constant.layout.Position"].list_nav}'>
						<#if layout.navmoduleList?? && layout.navmoduleList?size gt 0>
							<#list layout.navmoduleList as module>
								<#include module.module.msign2>
							</#list>
						</#if>
						</ul>
					</div>
					<div class="clr"></div>
				</div>
				<div class="headerMenuBottom">
				</div>
			</div>
		</div>