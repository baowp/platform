<script type="text/javascript" src="js/site/message.js"></script>
<link href="css/site/message.css" rel="stylesheet" type="text/css" />
<div id="message" class="bodyCont moveChild mainTextColor">
	<div class="clr"></div>
	<div class="bodyContTitle">
	<span class="fl b titleLinkColor titleName" >
			${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_MESSAGE.name()],
				moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_MESSAGE.name()])}
		</span>
	<a class="fr fs12px nb titleLinkColor draft_no_link" ></a>
	</div>
	<div class="bodyContContent rel rightConteWidth">
	<div class="leaveWord">
		<form id="mainMessageForm" action="${server()}/site_message/send" >
		 	<table width="98%" border="0" cellspacing="0">
		 		<tr><td colspan="2" align="left">${lan['message.introduction']}</td></tr>
		 		<tr><td class="sendtd">${lan['message.title']}</td><td class="sendcont"><input type="text" name="title" class="sendTitle {required:true,messages:{required:'${lan['message.requiredTitle']}'}}"/><span class="tip"></span></td></tr>		 		
				<tr><td class="sendtd">${lan['message.content']}</td><td class="sendcont"><textarea type="text" name="content" rows="6" class="sendContent {required:true,messages:{required:'${lan['message.requiredContent']}'}}"></textarea><span class="tip"></span></td></tr>
		 		<tr><td class="sendtd">${lan['message.phone']}</td><td class="sendcont"><input type="text" name="fromPhone" maxlength="20" ></input></td></tr>
				<tr><td class="sendtd">${lan['message.email']}</td><td class="sendcont"><input type="text" name="fromEmail" maxlength="20"></input></td></tr>
				<tr><td class="sendtd">${lan['message.yourname']}</td><td class="sendcont"><input type="text" name="fromName" maxlength="20"  value="${lan['message.anonymous']}"></input></td></tr>
			<#--	<tr><td class="sendtd">${lan['valiCode']}</td><td class="sendcont" id=codeCell><input type="text" id="valiCode" name="valiCode" size="14" class="code {required:true,messages:{required:'${lan['requiredValiCode']}'}}"/>
		 				<img class="validate-num" style="position: absolute;margin-left:4px;" onclick="refreshCode()" id="verifyPic" name="verifyPic" src="${server()}/comm/veriImg" /><span id="authcodeSpan" class="tip"></span> </td></tr>
		 	-->
		 		<tr><td></td>
		 			<td class="sendcont">
			 			<div class="sendSub">
							<input type="submit" class="sendBtn" value="${lan['message.send']}>>" name="sendButton"/>
						</div>
		 			</td>
		 		</tr>
		 	</table>
		 	<input type="hidden" name="recvUser" value="${user.userId}"/>
		 	<input type="hidden" name="recvEnt" value="${user.enterpriseId}"/>
	 	</form>
	</div>
	<div class="clr"></div>
	</div>
</div>