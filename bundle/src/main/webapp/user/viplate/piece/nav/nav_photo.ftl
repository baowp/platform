<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].PHOTO>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="photo.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_PHOTO.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_PHOTO.name()])}
		</a>
	</span>
</li>