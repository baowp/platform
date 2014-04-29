<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="main_company_location" class="bodyCont moveChild mainTextColor">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_COMPANY_LOCATION}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_COMPANY_LOCATION.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_COMPANY_LOCATION.name()]"/>
		</span>
	</div>
	<div class="bodyContContent">
		<div class="mapbarBox">
			<iframe width="700" height="400" scrolling="no" border="0"
				frameborder="0" id="mapIframe"
				src="http://searchbox.mapbar.com/publish/template/template1010/index.jsp?width=700&height=400&CID=ggggfj&tid=tid1010&nid=${enterprise.mapaddress }&control=2&infopoi=1&infoname=1&zoom=10&showSearchDiv=0"></iframe>
		</div>
	</div>
</div>