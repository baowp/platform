<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="head_user_defined3" class="headerMenu moveChild head_user_defined headCont">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@HEAD_USER_DEFINED3}"/>
	<div class="clr"></div>
	<s:if test="maintainable">
		<div class="rel fl zindex900">
			<div class="edit_top">
				<div class="editdiv">
					<a class="editCtrl" href="#">
						<img border="0" src="/user/vipsite/tool/images/edit/edit.gif">
					</a>
				</div>
			</div>
		</div>
	</s:if>
	<div class="headContContent">
		<s:property value="layout.jsonBanner[@com.abbcc.util.constant.layout.Piece@HEAD_USER_DEFINED3.name()]" escape="false"/>
	</div>
</div>