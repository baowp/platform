<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		<div id="companyIntro" class="bodyCont moveChild">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_INTRO}"/>
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
						<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_INTRO.name()]||
							#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_INTRO.name()]" escape="false"/>
					</span>
				<s:if test="layout.belongPage!=@com.abbcc.util.constant.layout.BelongPage@COMPANY">
					<a class="fr fs12px nb titleLinkColor draft_no_link" href="company">${lan['more']} &gt;&gt;</a>
				</s:if>
			</div>
			<div class="bodyContContent rel rightConteWidth">
			<div class="bodyContContentRightCont fl tal mainTextColor break">
			  <span>${enterprise.edesc }</span>
			</div>
			<div class="clr"></div>
			</div>
		</div>