<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
var enterpriseId = "${enterpriseId}";
var obj = "ent";
</script>
<script type="text/javascript" src="/user/vipsite/js/collect.js"></script>
<div id="contact_side" class="bodyCont moveChild" >
	<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@SIDE_CONTACT}"/>
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@SIDE_CONTACT.name()]||
				#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@SIDE_CONTACT.name()]"/>
		</span>
	</div>
	<div class="bodyContContent mainTextColor">
		<ul>
		<li class="contactLi">
			<span id="contactName">
				<a class="topicLink draft_no_link" >${model.name }</a>				
			</span>
			<s:if test="maintainable">
				<span class="gaim" id="gaim-qq">
					<!-- 可点击 <a href="#" onclick="gaim.open()" id="gaimqqLink"> -->
					<a href="#" id="gaimqqLink">
					<s:if test="layout.jsonContent['gaim']['qq']!=null">
						<s:property value="layout.jsonContent['gaim']['qq'].replaceAll('<(/)?a[^>]*>','')" escape="false"/>
					</s:if>
					<s:else>
						
					</s:else>
					</a>
				</span>
				<span class="gaim">
					<!-- 可点击 <a href="#" onclick="gaim.openMsn()" id="gaimMsnLink"> -->
					<a href="#" id="gaimMsnLink">
					<s:if test="layout.jsonContent['gaim']['msn']!=null">
						<s:property value="layout.jsonContent['gaim']['msn'].replaceAll('<(/)?a[^>]*>','')" escape="false"/>
					</s:if>
					<s:else>
						
					</s:else>
					</a>
				</span>
			</s:if>
			<s:else>
				<span class="gaim" id="gaim-qq">${layout.jsonContent['gaim']['qq'] }</span>
				<span class="gaim">${layout.jsonContent['gaim']['msn'] }</span>
			</s:else>
		</li>
		<s:if test="!isBlank(phone)">
			<li class="contactLi"><span>${lan['contact.phone']}${phone }</span></li>
		</s:if>
		<s:if test="!isBlank(fax)">
			<li class="contactLi"><span>${lan['contact.fax']}${fax }</span></li>
		</s:if>
		<s:if test="!isBlank(cellphone)">
			<li class="contactLi"><span>${lan['contact.cellphone']}${cellphone }</span></li>
		</s:if>
		<s:if test="!isBlank(url)">
			<li class="contactLi">
			<span>${lan['contact.entUrl']}<a href="http://${url2 }" title="${url2 }" target="_blank">${url2 }</a></span></li>
		</s:if>
		<s:if test="!isBlank(email)">
			<li class="contactLi">
			<span>${lan['contact.email']}<a href="mailto:${email}" title="${email }">${email }</a></span></li>
		</s:if>
		<s:if test="!isBlank(address)">
			<li class="contactLi"><span>${lan['address']}${address }</span></li>
		</s:if>
		<li class="contactLi">
			<span><input type="button" eId="${enterpriseId}" id="collect-btn"  value="${lan['contacts.collectByEnt']}"/>&nbsp${lan['contacts.moods']}：<font color="red" class="moods"></font></span></li>
		</ul>
	</div>
	<div class="clr"></div>
</div>