	${action.pieceSupplies()}
	<#assign supplies=action.root.supplies>
		<div id="supplies" class="bodyCont moveChild glare_type_1">
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_SUPPLIES.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_SUPPLIES.name()])}
				</span>
				<#if layout.belongPage!=enums["com.abbcc.util.constant.layout.BelongPage"].SUPPLY>
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="supply.html">${lan['more']} &gt;&gt;</a>
				</#if>
			</div>
			<div class="bodyContContent rightConteWidth rel">
				<table class="prolist_table mainTextColor   bodyContContentImg-150" style="width: 98%">
					<tbody>
						<tr style="color: rgb(0, 0, 0);" class="title">
							<td width="35% " bgcolor="#e7e7e7">${lan['supplies.info']}</td>
							<td width="20%" bgcolor="#e7e7e7">${lan['supplies.price']}</td>
							<td width="45%" bgcolor="#e7e7e7">${lan['supplies.publishTime']}</td>	
						 </tr>
						 <#list supplies.items as item>
						 	<tr>						 		
    							<td  align="left">
    								<a class="draft_no_link topicLink underline" href="supply-detail-${item.supplyId2}.html"" target="_blank">[${item.type!''}] ${item.title!''}</a>
								</td>
    							<td>${item.price!'' }</td>
    							<td><#if item.startTime??>${item.startTime?string(lan['supplies.format'])}</#if></td>     
						 	</tr>
						 </#list>
					</tbody>
				</table>
			</div>
		</div>