<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].RECRUIT>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="recruit.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_RECRUIT.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_RECRUIT.name()])}
		</a>
	</span>
</li>