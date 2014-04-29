<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].CERT>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="cert.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_CERT.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_CERT.name()])}
		</a>
	</span>
</li>