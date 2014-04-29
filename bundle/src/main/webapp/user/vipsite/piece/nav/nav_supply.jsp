<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@SUPPLY?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu" >
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_SUPPLY}"/>
	<span>
		<a href="supply">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_SUPPLY.name()]!=null?
				layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_SUPPLY.name()]:
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_SUPPLY.name()]"/>
		</a>
	</span>
</li>