<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].TECHNIC>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="technic.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_TECHNIC.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_TECHNIC.name()])}
		</a>
	</span>
</li>