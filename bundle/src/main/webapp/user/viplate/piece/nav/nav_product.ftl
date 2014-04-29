<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].PRODUCT>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="product.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_PRODUCT.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_PRODUCT.name()])}
		</a>
	</span>
</li>