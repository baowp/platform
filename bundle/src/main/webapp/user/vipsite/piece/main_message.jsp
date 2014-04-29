<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:url value="/user/js/site/message.js"/>"></script>
<link href="/user/vipsite/css/message.css" rel="stylesheet" type="text/css" />
		<div id="message" class="bodyCont moveChild mainTextColor" style="margin-bottom: 7px;">
			<s:hidden name="piece" value="%{@com.abbcc.util.constant.layout.Piece@MAIN_MESSAGE}"/>
			<div class="clr"></div>
			<div class="bodyContTitle">
			<span class="fl b titleLinkColor titleName" >
					<s:property value="layout.jsonContent['title'][@com.abbcc.util.constant.layout.Piece@MAIN_MESSAGE.name()]||
						#request['moduleMap'][@com.abbcc.util.constant.layout.Piece@MAIN_MESSAGE.name()]"/>
				</span>
			<a class="fr fs12px nb titleLinkColor draft_no_link" ></a>
			</div>
			<div class="bodyContContent rel rightConteWidth">
			<div class="leaveWord">
				<s:form id="mainMessageForm" namespace="/site_message" action="send" >
				 	<table width="98%" border="0" cellspacing="0">
				 		<tr>
				 			<td colspan="2" align="left">${lan['message.introduction']}</td>
				 		</tr>
				 		<tr><td class="sendtd">${lan['message.title']}</td><td class="sendcont"><s:textfield name="title" cssClass="sendTitle {required:true,messages:{required:'%{lan['message.requiredTitle']}'}}"/><span class="tip"></span></td></tr>
				 	
				 		
				 		<tr><td class="sendtd">${lan['message.content']}</td><td class="sendcont"><s:textarea name="content" rows="6" cssClass="sendContent {required:true,messages:{required:'%{lan['message.requiredContent']}'}}"/><span class="tip"></span></td></tr>
				 	<%-- 	<tr><td class="sendtd">${lan['valiCode']}</td><td class="sendcont" id=codeCell><s:textfield id="valiCode" name="valiCode" size="14" cssClass="code {required:true,messages:{required:'%{lan['requiredValiCode']}'}}"/>
				 				<img class="validate-num" style="position: absolute;margin-left:4px;" onclick="refreshCode()" id="verifyPic" name="verifyPic" src="<s:url value="/comm/veriImg"/>" /><span id="authcodeSpan" class="tip"></span> </td></tr>
				 	--%>
				 		<tr>
				 			<td class="sendtd">${lan['message.phone']}</td>
				 			<td class="sendcont"><s:textfield name="fromPhone" maxlength="20" cssClass="sendPhone"></s:textfield></td>
				 		</tr>
				 		<tr>
				 			<td class="sendtd">${lan['message.email']}</td>
				 			<td class="sendcont"><s:textfield name="fromEmail" maxlength="20" cssClass="sendEmail"></s:textfield></td>
				 		</tr>
				 		<tr>
				 			<td class="sendtd">${lan['message.yourname']}</td>
				 			<td class="sendcont"><s:textfield name="fromName" maxlength="20" cssClass="sendName" value="%{lan['message.anonymous']}"></s:textfield></td>
				 		</tr>
				 		<tr><td></td>
				 			<td class="sendcont">
					 			<div class="sendSub">
									<s:submit cssClass="sendBtn" onclick="" value="%{lan['message.send']}>>" name="sendButton" disabled="%{maintainable?'true':''}"/>
								</div>
				 			</td>
				 		</tr>
				 	</table>
				 	<s:hidden name="recvUser" value="%{userId}"/>
				 	<s:hidden name="recvEnt" value="%{enterprise.enterpriseId}"/>
			 	</s:form>
			</div>
			<div class="clr"></div>
			</div>
		</div>