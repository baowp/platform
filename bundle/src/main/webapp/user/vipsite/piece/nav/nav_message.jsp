<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@MESSAGE?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu" >
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_MESSAGE}"/>
	<span>
		<a href="message">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_MESSAGE.name()]!=null?
				layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_MESSAGE.name()]:
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_MESSAGE.name()]"/>
		</a>
	</span>
</li>