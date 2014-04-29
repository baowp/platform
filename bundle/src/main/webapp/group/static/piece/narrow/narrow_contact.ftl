<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_contact>
<div id="contact_side" class="bodyCont moveChild">
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName" >
			${moduleMap[sign]!'' }
		</span>
	</div>
	<div class="bodyContContent mainTextColor">
		<ul>
		<li class="contactLi">
			<span id="contactName">
				<a class="topicLink draft_no_link" >${user.name!'' }</a>				
			</span>
			<span class="gaim" id="gaim-qq"></span>
			<span class="gaim"></span>
		</li>
		<#if user.phone??>
			<li class="contactLi"><span>${lan['contact.phone']}${user.phone!''}</span></li>
		</#if>
		<#if user.fax??>
			<li class="contactLi"><span>${lan['contact.fax']}${user.fax!''}</span></li>
		</#if>
		<#if user.cellphone??>
			<li class="contactLi"><span>${lan['contact.cellphone']}${user.cellphone!'' }</span></li>
		</#if>
		<#if user.url??>
			<li class="contactLi">
			<span>${lan['contact.entUrl']}<a href="http://${user.url2!'' }" title="${user.url2!'' }" target="_blank">${user.url2!'' }</a></span></li>
		</#if>
		<#if user.email??>
			<li class="contactLi">
			<span>${lan['contact.email']}<a href="mailto:${user.email!''}" title="${user.email!'' }">${user.email!'' }</a></span></li>
		</#if>
		<#if user.address??>
			<li class="contactLi"><span>${lan['address']}${user.address!'' }</span></li>
		</#if>
		</ul>
	</div>
	<div class="clr"></div>
</div>