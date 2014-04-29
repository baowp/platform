<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#request['contactList']==null">
	<s:action var="contacts" name="*/pieceContactList" namespace="/vip" ignoreContextParams="true">
		<s:param name="enterpriseId" value="enterpriseId"/>
		<s:param name="userId" value="userId"/>
	</s:action>
</s:if>
<div id="contact_column" class="bodyCont moveChild">
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_CONTACTS}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_CONTACTS.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_CONTACTS.name()]"/>
				</span>
	</div>
	<div class="bodyContContent rightConteWidth_ contacts">
		<s:iterator value="#request.contactList">
		<ul class="mainTextColor contactOne glitzColor">
			<li><span>${lan['contacts.name']}${name}</span></li>
			<li><span>${lan['contacts.position']}${position}</span></li>
			<li><span>${lan['contacts.phone']}${phone}</span></li>
			<li><span>${lan['contacts.fax']} ${fax}</span></li>
			<li><span>${lan['contacts.cellphone']} ${cellphone}</span></li>
			<li><span>${lan['contacts.email']}${email}</span></li>
			<li><span>${lan['contacts.entUrl']}<a target="_blank" href="${url }"
				class="topicLink draft_no_link" >${url }</a>
				</span>
			</li>
			<s:if test="!isBlank(address)">
				<li><span>${lan['contacts.address']}${address}</span></li>
			</s:if>
		</ul>
		</s:iterator>
	</div>
</div>
