<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].COMPANY>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="company.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_COMPANY.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_COMPANY.name()])}
		</a>
	</span>
</li>