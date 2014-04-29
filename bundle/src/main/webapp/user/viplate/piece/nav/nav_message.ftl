<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].MESSAGE>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="message.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_MESSAGE.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_MESSAGE.name()])}
		</a>
	</span>
</li>