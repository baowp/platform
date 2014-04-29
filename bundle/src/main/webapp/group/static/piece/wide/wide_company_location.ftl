<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_company_location>
${command.pieceCompanyLocation() }
<div id="main_company_location" class="bodyCont moveChild mainTextColor">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			${moduleMap[sign]!''}
		</span>
	</div>
	<div class="bodyContContent">
		<div class="mapbarBox">
			<iframe width="100%" height="400" scrolling="no" border="0"
				frameborder="0" id="mapIframe"
				src="http://searchbox.mapbar.com/publish/template/template1010/index.jsp?width=700&height=400&CID=ggggfj&tid=tid1010&nid=${enterprise.mapaddress!'' }&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0"></iframe>
		</div>
	</div>
</div>