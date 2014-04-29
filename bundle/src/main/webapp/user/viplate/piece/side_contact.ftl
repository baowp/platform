<script type="text/javascript">
var enterpriseId = "${user.enterpriseId!''}";
var obj = "ent";
</script>
<script type="text/javascript" src="js/site/collect.js"></script>
		<div id="contact_side" class="bodyCont moveChild" >
			<div class="clr"></div>
			<div class="bodyContTitle">
				<span class="fl b titleLinkColor titleName" >
					${extract(layout.jsonContent['title'][enums["com.abbcc.util.constant.layout.Piece"].SIDE_CONTACT.name()],
						moduleMap[enums["com.abbcc.util.constant.layout.Piece"].SIDE_CONTACT.name()])}
				</span>
			</div>
			<div class="bodyContContent mainTextColor">
				<ul>
				<li class="contactLi">
				<span>
					<a class="topicLink draft_no_link" >${user.name!'' }</a>				
				</span>
				<span class="gaim" id="gaim-qq">${(layout.jsonContent['gaim']['qq'])!'' }</span>
				<span class="gaim">${(layout.jsonContent['gaim']['msn'])!'' }</span>
				</li>
				<#if !action.isBlank(user.phone)>
					<li class="contactLi"><span>${lan['contact.phone']}${user.phone!'' }</span></li>
				</#if>				
				<#if !action.isBlank(user.fax)>
					<li class="contactLi"><span>${lan['contact.fax']}${user.fax!'' }</span></li>
				</#if>
				<#if !action.isBlank(user.cellphone)>
					<li class="contactLi"><span>${lan['contact.cellphone']}${user.cellphone!'' }</span></li>
				</#if>
				<#if !action.isBlank(user.url)>
					<li class="contactLi">
					<span>${lan['contact.entUrl']}<a target="_blank" title="${user.url2!'' }" href="http://${user.url2!'' }">${user.url2!'' }</a></span></li>
				</#if>
				<#if !action.isBlank(user.email)>
					<li class="contactLi">
					<span>${lan['contact.email']}<a href="mailto:${user.email!''}" title="${user.email!'' }" }">${user.email!'' }</a></span></li>
				</#if>
				<#if !action.isBlank(user.address)>
					<li class="contactLi"><span>${lan['address']}${user.address!'' }</span></li>
				</#if>
				<li class="contactLi">
			<span><input type="button" eId="${user.enterpriseId!''}" id="collect-btn"  value="${lan['contacts.collectByEnt']}"/>&nbsp${lan['contacts.moods']}：<font color="red" class="moods"></font></span></li>
				</ul>
			</div>
			<div class="clr"></div>
		</div>