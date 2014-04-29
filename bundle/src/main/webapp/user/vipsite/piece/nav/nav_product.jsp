<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<li class="<s:property value="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@PRODUCT?'headerMenuLiCheck':'headerMenuLi'"/> moveMenu" >
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@NAV_PRODUCT}"/>
	<span>
		<a href="product">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_PRODUCT.name()]!=null?
				layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@NAV_PRODUCT.name()]:
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@NAV_PRODUCT.name()]"/>
		</a>
	</span>
</li>