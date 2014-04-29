		<div id="companyIntro" class="bodyCont moveChild">
			<div class="clr"></div>
			<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_INTRO.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_INTRO.name()])}
				</span>
				<#if layout.belongPage!=enums["com.abbcc.util.constant.layout.BelongPage"].COMPANY>
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="company.html">${lan['more']} &gt;&gt;</a>
				</#if>
			</div>
			<div class="bodyContContent rel rightConteWidth">
			<div class="bodyContContentRightCont fl tal mainTextColor break">
			  <span>${user.getEnterprise().edesc!"" }</span>
			</div>
			<div class="clr"></div>
			</div>
		</div>