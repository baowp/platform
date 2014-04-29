<#if action.root.technic??>
<#else>${action.pieceTechnic()}</#if>
<#assign technic=action.root.technic>
		<div id="technic" class="bodyCont moveChild">
			<div class="clr"></div>
			<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_TECHNIC.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_TECHNIC.name()])}
				</span>
			
			</div>
			<div class="bodyContContent rel rightConteWidth">
			<div class="bodyContContentRightCont fl tal mainTextColor break">
			  <span>${(technic.content)!'' }</span>
			</div>
			<div class="clr"></div>
			</div>
		</div>