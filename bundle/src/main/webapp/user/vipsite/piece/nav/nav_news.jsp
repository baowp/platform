<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@NEWS?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_NEWS}"/>
	<span>
		<a href="news">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_NEWS.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_NEWS.name()]"/>
		</a>
	</span>
</li>