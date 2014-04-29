<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].hunk_logo>
<div id="company_name" class="moveChild topbaner headCont">
	<div class="topbanerCont">
		<div id="log">
			<#if layout.jsonSign['log']??>
				<img <#list layout.jsonSign['log'] as log>${log.key!''}="${log.value!''}"</#list>></img>
			</#if>
		</div>
		<div id="companyname">
		<ul>
			<li class="china">
			<span id="chinaname" class="chinaname">${enterprise.name!''}</span>
			<span class="enname" id="enname"></span>
			</li>
		</ul>
		</div> 
	</div>
</div>