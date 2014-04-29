		<div id="company_name" class="moveChild topbaner" >
			<div class="pointer_">
				<div id="log">
					<#if layout.jsonSign['log']??>
						<img <#list jsonSign['log']?keys as key>${key}="${jsonSign['log'][key]}" </#list> />
					</#if>
				</div>
				<div id="companyname">
				<ul>
					<li class="china">
					<span id="chinaname" class="chinaname">${user.getEnterprise().name!''}</span>
					<span class="enname" id="enname"></span>
					</li>
				</ul>
				</div> 
			</div>
		</div>