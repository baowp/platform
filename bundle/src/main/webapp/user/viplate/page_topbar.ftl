<link href="css/site/topbar.css" rel="stylesheet" />
<script type="text/javascript" src="js/site/topbar.js"></script>
<div id="topbar-v2" class="topbar topbar-v2">
	<div class="topbar-container">
		<div class="account-sign-status">
			<ul>
                <li class="account-welcome">${lan['topbar.hello']}<span id="accountId" class="isLoged"><a href="#" class="account-signed"><script type="text/javascript">document.write($.cookie('username'))</script></a></span><span class="isNotLoged">${lan['topbar.welcome']}</span></li>
                <li class="isNotLoged" id="accountSignIn"><a id=loginLink class="loginLink" href="#">${lan['topbar.login']}</a></li>
                <li class="isNotLoged" id="accountSignUp"><a id=registerLink href="#">${lan['topbar.register']}</a></li>
                <li class="isLoged" id="accountSignOut"><a onclick="$.getScript('${server()}/subuser/logout?userDomain='+document.domain)" href="#">${lan['exit']}</a></li>
            </ul>
		</div>
		<div class="topnav">
			<ul>
				<li><a href="javascript:addFav()">${lan['addFavourite']}</a></li>
				<li><a href="javascript:setHomepage()">${lan['setHomepage']}</a></li>
				<#if (layout.jsonContent['visit']['href'])??>
				<li>
	      			<a href="${layout.jsonContent['visit']['href']}">
	      				${layout.jsonContent['visit']['name']}
	      			</a>
	      		</li>
      			</#if>
      			<li style="display:none">${statics["java.util.Calendar"].getInstance().time?datetime}</li>
      			<li style="display:none">${layout.layoutId!""}</li>
			</ul>
		</div>
	</div>	
	<p class="panel_flex" id="topbarCtrl">
		<span class="icon_toolbar_up"></span>
	</p>
</div>