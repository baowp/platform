<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="main_user_defined6" class="bodyCont moveChild mainTextColor user_defined">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_USER_DEFINED6}"/>
	<div class="clr"></div>
	<s:if test="maintainable">
		<div class="rel fl zindex900">
			<div class="edit_right">
				<div class="editdiv">
					<a class="editCtrl" href="#">
						<img border="0" src="/user/vipsite/tool/images/edit/edit.gif">
					</a>
				</div>
			</div>
		</div>
	</s:if>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_USER_DEFINED6.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_USER_DEFINED6.name()]"/>
		</span>
	</div>
	<div class="bodyContContent">
		<s:property value="layout.jsonBanner[@com.abbcc.util.constant.layout.Piece@MAIN_USER_DEFINED6.name()]" escape="false"/>
	</div>
</div>