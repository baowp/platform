<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].narrow_gaim>
<#if !command.take("narrowGaim")??>
	${command.pieceGaim() }
</#if>
<#assign narrowGaim=command.take("narrowGaim")>
<div id="side_link" class="bodyCont moveChild mainTextColor">
	<link href="css/gaim.css" rel="stylesheet" />
	<div class="clr"></div>
	<div class="bodyContTitle">
		<span class="fl b titleLinkColor titleName"> ${moduleMap[sign]!''}
		</span>
	</div>
	<div class="bodyContContent">
		<ul class="gaim_ul">
				<#list narrowGaim as c>
					<li>
					<div class="gaimName">${c.name!'' }</div>
					<div class="gaimCode">
						<#if !c.code??>
							<a target="_blank"
									href="http://wpa.qq.com/msgrd?v=3&uin=${c.account!'' }&site=qq&menu=yes"><img
									border="0" src="http://wpa.qq.com/pa?p=2:qqnumber:41"
									alt="${c.account!'' }" title="有事请Q我"> </a>
						<#else>
							${c.code!'' }
						</#if>
					</div>
				</li>
				</#list>
		</ul>
	</div>
</div>