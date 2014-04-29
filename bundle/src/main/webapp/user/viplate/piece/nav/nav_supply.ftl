<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].SUPPLY>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="supply.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_SUPPLY.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_SUPPLY.name()])}
		</a>
	</span>
</li>