<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@CONTACT?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_CONTACT}" />
	<span>
		<a href="contact">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_CONTACT.name()]!=null?
				layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_CONTACT.name()]:
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_CONTACT.name()]"/>
		</a>
	</span>
</li>