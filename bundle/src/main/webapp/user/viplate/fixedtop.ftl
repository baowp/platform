<link type="text/css" href="css/site/fixedbar.css" rel="stylesheet" />
<script type="text/javascript" src="js/site/fixedbar.js"></script>
<div id="fixedLayout" >
	<div id="divToolbarRoot" class="toolbar_v3" >
		<div id="divToolbarBody" class="wrap divToolbarBody">
			<div class="panel_left">
				
			</div>
			<div class="panel_right">
				<div class="bor_r log isLoged">
					<div class="loged">
							<div><span>${lan['welcome']}</span> <script type="text/javascript">document.write($.cookie('username'))</script></div>
					</div>
					<ul class="loged">
						<li><div><a onclick="$.getScript('${server()}/subuser/logout?userDomain='+document.domain)">${lan['exit']}</a></div></li>
					</ul>
				</div>
				<div class="bor_r log isNotLoged">
					<ul class="log">
						<li>
							<div><a id=loginLink>${lan['login']}</a></div>
						</li>
						<li>
							<div><a id=registerLink>${lan['register']}</a></div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<p title="${lan['hideToolbar']}" class="panel_flex" id="pTbCtrl">
			<img class="icon_toolbar_up" src="images/vipsite/b.gif" id="imgTbCCtrl">
		</p>
	</div>	
</div>