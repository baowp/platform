<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_supplies>
${command.pieceSupplies() }
<#assign supplies=command.take("supplies")>
<div id="supplies" class="bodyCont moveChild glare_type_1">
		<div class="clr"></div>
		<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
				${moduleMap[sign]!'' } 
			</span>
			<#if belongPage != 'supply'>
				<a class="fr fs12px nb titleLinkColor draft_no_link" href="supply.html">${lan['more']} &gt;&gt;</a>
			</#if>
		</div>
		<div class="bodyContContent rightConteWidth rel" data-meta='{"currentPage":${supplies.currentPage!''},"pageCount":${supplies.pageCount!''},"url":"${sign }"}'>
			<table class="prolist_table mainTextColor   bodyContContentImg-150" style="width: 98%">
				<tbody>
					<tr style="color: rgb(0, 0, 0);" class="title">							
						<td width="35% " bgcolor="#e7e7e7">${lan['supplies.info']}</td>
						<td width="20%" bgcolor="#e7e7e7">${lan['supplies.price']}</td>
						<td width="45%" bgcolor="#e7e7e7">${lan['supplies.publishTime']}</td>	
					 </tr>
					 <#list supplies.items as supply>
					 	<tr>						 		
   							<td align="left">
   								<a class="draft_no_link topicLink underline" href="supply-detail-${supply.supplyId2}.html" target="_blank">[${supply.type.toString()!'' }] ${supply.title!''}</a>
							</td>
   							<td>${supply.price!'' }</td>
   							<td><#if supply.startTime??>${supply.startTime?string(lan['supplies.format'])}</#if></td> 
					 	</tr>
					 </#list>
				</tbody>
			</table>
			<div class="wangpuBorderBottom3 mt10px mb5px mainTextColor">
				<#if belongPage == 'supply'>
					<#assign pageList=supplies>
					<#include "../../pagination.ftl">
				</#if>
			</div>
		</div>
	</div>