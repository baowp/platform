<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_technic>
${command.pieceTechnic() }
<#assign technic=command.take("technic")>
<div id="technic" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
	<span class="fl b titleLinkColor titleName" >
		${moduleMap[sign]!'' }
	</span>
	</div>
	<div class="bodyContContent rel rightConteWidth">
	<div class="bodyContContentRightCont fl tal mainTextColor break">
	  <span>${technic.content!'' }</span>
	</div>
	<div class="clr"></div>
	</div>
</div>