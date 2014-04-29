<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		<div id="top_nav" class="headerMenu moveChild">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@HEAD_NAVIGATOR}"/>
			<div class="clr"></div>
			<div>
				<div class="headerMenuBorder">
					<div class="headerMenuList">
						<ul id='<s:property value='@com.abbcc.util.constant.layout.Position@list_nav'/>'>
							<s:iterator value="layout.navmoduleList">
								<s:include value="%{module.msign}"/>
							</s:iterator>						
						</ul>
					</div>
					<div class="clr"></div>
				</div>
				<div class="headerMenuBottom">
				</div>
			</div>
		</div>