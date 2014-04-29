<li id="menu-${nav.page!''}" class="<#if belongPage==nav.page>headerMenuLiCheck headerMenuLiCheckBg<#else>headerMenuLi</#if> moveMenu" 
	data-submenu='${statics["net.sf.json.JSONSerializer"].toJSON(nav.subJson!"")}'>
	<span>
		<a href="${nav.page!''}.html">
			${nav.name!'' }
		</a>
	</span>
</li>
