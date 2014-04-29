<#if !command.take("navigator")??>
	${command.pieceNavigator() }
</#if>
<#assign navigator=command.take("navigator")>
<#assign sign=enums["com.abbcc.util.constant.group.GroupPiece"].hunk_navigator>
<div id="top_nav" class="headerMenu moveChild">
<link rel="stylesheet" href="css/menu.css" />
<script type="text/javascript" src="js/jquery/superfish/hoverIntent.js"></script>
<script type="text/javascript" src="js/jquery/superfish/superfish.js"></script>
<script type="text/javascript" src="js/site/menu.js"></script>
	<div class="clr"></div>
	<div>
		<div class="headerMenuBorder headerMenuBg">
			<div class="headerMenuList">
				<ul id='list_nav'>
					<#list navigator as nav>
						<#include "nav.ftl">
					</#list>
				</ul>
			</div>
			<div class="clr"></div>
		</div>
		<div class="headerMenuBottom">
		</div>
	</div>
</div>