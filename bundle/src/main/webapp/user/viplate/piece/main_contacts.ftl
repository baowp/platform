<#if action.root.contactList??>
<#else>
	${action.pieceContactList()}
</#if>
<#assign contactList=action.root.contactList>
<div id="contact_column" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].MAIN_CONTACTS.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].MAIN_CONTACTS.name()])}
				</span>
	</div>
	<div class="bodyContContent rightConteWidth_ contacts">
		<#list contactList as item>
		<ul class="mainTextColor contactOne glitzColor">
			<li><span>${lan['contacts.name']}${item.name!''}</span></li>
			<li><span>${lan['contacts.position']}${item.position!''}</span></li>
			<li><span>${lan['contacts.phone']}${item.phone!''}</span></li>
			<li><span>${lan['contacts.fax']} ${item.fax!''}</span></li>
			<li><span>${lan['contacts.cellphone']} ${item.cellphone!''}</span></li>
			<li><span>${lan['contacts.email']}${item.email!''}</span></li>
			<li><span>${lan['contacts.entUrl']}<a target="_blank" href="${item.url!'' }"
				class="topicLink draft_no_link" >${item.url!'' }</a>
				</span>
			</li>
			<#if !action.isBlank(item.address)>
				<li><span>${lan['contacts.address']}${item.address}</span></li>
			</#if>
		</ul>
		</#list>
	</div>
</div>
