<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].wide_contacts>
<#if !contactList??>
	${command.pieceContactList() }
</#if>
<#assign contactList=command.take("contactList")>
<div id="contact_column" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign]!'' }
		</span>
	</div>
	<div class="bodyContContent rightConteWidth_ contacts">
		<#list contactList as c>
			<ul class="mainTextColor contactOne glitzColor">
				<li><span>${lan['contacts.name']}${c.name!''}</span></li>
				<li><span>${lan['contacts.position']}${c.position!''}</span></li>
				<li><span>${lan['contacts.phone']}${c.phone!''}</span></li>
				<li><span>${lan['contacts.fax']} ${c.fax!''}</span></li>
				<li><span>${lan['contacts.cellphone']} ${c.cellphone!''}</span></li>
				<li><span>${lan['contacts.email']}${c.email!''}</span></li>
				<li><span>${lan['contacts.entUrl']}<a target="_blank" href="${c.url!'' }"
					class="topicLink draft_no_link" >${c.url!'' }</a>
					</span>
				</li>
				<#if c.address??>
					<li><span>${lan['contacts.address']}${c.address!''}</span></li>
				</#if>
			</ul>
		</#list>
	</div>
</div>
