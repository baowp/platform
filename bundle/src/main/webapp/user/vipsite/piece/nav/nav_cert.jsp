<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@CERT?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_CERT}"/>
	<span>
		<a href="cert">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_CERT.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_CERT.name()]"/>
		</a>
	</span>
</li>