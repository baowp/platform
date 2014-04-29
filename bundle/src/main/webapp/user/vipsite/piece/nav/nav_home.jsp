<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@HOME?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu" >
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_HOME}"/>
	<span>
		<a href="index">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_HOME.name()]!=null?
				layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_HOME.name()]:
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_HOME.name()]"/>
		</a>
	</span>
</li>