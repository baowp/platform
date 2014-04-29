<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="/user/vipsite/css/sidelink.css"></link>
<s:if test="#request['sideLink']==null">
		<s:action name="*/pieceSideLink" namespace="/vip" >
			<s:param name="enterpriseId" value="enterpriseId"/>
		</s:action>
</s:if>
<div id="side_link" class="bodyCont moveChild mainTextColor">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@SIDE_LINK}"/>
	<div class="clr"></div>
	
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName">
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@SIDE_LINK.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@SIDE_LINK.name()]"/>
		</span>
	</div>
	<div class="bodyContContent">
		<ul >
		<s:iterator value="#request.sideLink">
		    <li class="friendLinkLi"> <a class="topicLink draft_no_link" target="_blank" href="${url}">${name}</a>
			</li>
		</s:iterator>
		</ul>
	</div>
</div>