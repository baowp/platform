<li class="<#if layout.belongPage==enums["com.abbcc.util.constant.layout.BelongPage"].CONTACT>headerMenuLiCheck<#else>headerMenuLi</#if> moveMenu">
	<span>
		<a href="contact.html">
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].NAV_CONTACT.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].NAV_CONTACT.name()])}
		</a>
	</span>
</li>