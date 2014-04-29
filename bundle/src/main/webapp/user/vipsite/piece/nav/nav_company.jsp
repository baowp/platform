<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@COMPANY?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_COMPANY}"/>
	<span>
		<a href="company">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_COMPANY.name()]!=null?
				layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_COMPANY.name()]:
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_COMPANY.name()]"/>
		</a>
	</span>
</li>