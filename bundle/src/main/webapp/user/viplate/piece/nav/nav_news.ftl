<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].NEWS>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="news.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_NEWS.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_NEWS.name()])}
		</a>
	</span>
</li>