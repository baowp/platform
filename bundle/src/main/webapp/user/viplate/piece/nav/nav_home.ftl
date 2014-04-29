<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].HOME>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="index.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_HOME.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_HOME.name()])}
		</a>
	</span>
</li>