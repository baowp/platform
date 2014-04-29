<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_message>
<div id="message" class="bodyCont moveChild mainTextColor" style="margin-bottom: 7px;">
<script type="text/javascript" src="js/site/message.js"></script>
		<div class="clr"></div>
		<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign]!'' }
		</span>
			<a class="fr fs12px nb titleLinkColor draft_no_link" ></a>
			</div>
			<div class="bodyContContent rel rightConteWidth">
			<div class="leaveWord">
				<div class="isNotLoged" style="height: 100pt;line-height: 100pt;font-size: 14px;font-weight: ;">${lan['message.forbid']}</div>
				<form id="mainMessageForm" action="send" class="validateForm isLoged">
				 	<table width="98%" border="0" cellspacing="0">
				 		<tr><td class="sendtd">${lan['message.title']}</td><td class="sendcont"><input type="text" name="title" class="sendTitle {required:true,messages:{required:'${lan['message.requiredTitle']}'}}"/><span class="tip"></span></td></tr>
				 		<tr><td class="sendtd">${lan['message.content']}</td><td class="sendcont"><textarea name="content" rows="6" class="sendContent {required:true,messages:{required:'${lan['message.requiredContent']}'}}"></textarea><span class="tip"></span></td></tr>
				 		<tr><td></td> 
				 			<td class="sendcont">
					 			<div class="sendSub">
									<input type="submit" class="sendBtn" onclick="" value="${lan['message.send']}>>" name="sendButton"/>
								</div>
				 			</td>
				 		</tr>
				 	</table>
				 	<input type="hidden" name="recvUser" value="${user.userId }" />
				 	<input type="hidden" name="recvEnt" value="${enterprise.enterpriseId }" />
			 	</form>
			</div>
			<div class="clr"></div>
			</div>
		</div>