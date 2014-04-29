<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:if test="#request['supplies']==null">
		<s:action var="suppreq" name="*/pieceSupplies" namespace="/vip" >
			<s:param name="enterpriseId" value="enterpriseId"/>
		</s:action>
	</s:if>
		<div id="supplies" class="bodyCont moveChild glare_type_1">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_SUPPLIES}"/>
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_SUPPLIES.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_SUPPLIES.name()]"/>
				</span>
				<s:if test="layout.belongPage!=@com.abbcc.util.constant.layout.BelongPage@SUPPLY">
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="supply">${lan['more']} &gt;&gt;</a>
				</s:if>
			</div>
			<div class="bodyContContent rightConteWidth rel">
				<table class="prolist_table mainTextColor   bodyContContentImg-150" style="width: 98%">
					<tbody>
						<tr style="color: rgb(0, 0, 0);" class="title">							
							<td width="35% " bgcolor="#e7e7e7">${lan['supplies.info']}</td>
							<td width="20%" bgcolor="#e7e7e7">${lan['supplies.price']}</td>
							<td width="45%" bgcolor="#e7e7e7">${lan['supplies.publishTime']}</td>	
						 </tr>
						 <s:iterator value="#request.supplies.items">
						 	<tr>						 		
    							<td align="left">
    								<a class="draft_no_link topicLink underline" href="supply_detail?supplyId=${supplyId}"" target="_blank">[<s:property value="type"/>] ${title}</a>
								</td>
    							<td>${price }</td>
    							<td><s:date name="startTime" format="%{lan['supplies.format']}"/></td>     
						 	</tr>
						 </s:iterator>
					</tbody>
				</table>
			</div>
		</div>