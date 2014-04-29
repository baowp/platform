<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action namespace="/vip" name="*/pieceTechnic">
	<s:param name="enterpriseId" value="enterpriseId"/>
</s:action>
		<div id="technic" class="bodyCont moveChild">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_TECHNIC}"/>
			<div class="clr"></div>
			<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_TECHNIC.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_TECHNIC.name()]"/>
				</span>
			
			</div>
			<div class="bodyContContent rel rightConteWidth">
			<div class="bodyContContentRightCont fl tal mainTextColor break">
			  <span>${requestScope.technic.content }</span>
			</div>
			<div class="clr"></div>
			</div>
		</div>