<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" href="<s:url value="/user/css/site/fixedbar.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/user/js/site/fixedbar.js"/>"></script>
<div id="fixedLayout" >
	<div id="divToolbarRoot" class="toolbar_v3" >
		<div id="divToolbarBody" class="wrap divToolbarBody">
			<div class="panel_left">
				
			</div>
			<div class="panel_right">
<!--				<div class="bor_r2 mode_other_services" id="divTbCommService">-->
<!--					<p class="other_services" id="pTbCommService">-->
<!--						<span><span></span></span>-->
<!--					</p>-->
<!--				</div>-->
				<div class="bor_r log isLoged">
					<div class="loged">
							<div><span>${lan['welcome']}</span> <script type="text/javascript">document.write($.cookie('username'))</script></div>
					</div>
					<ul class="loged">
						<li><div><a onclick="$.getScript('<s:url value="/subuser/logout"/>?userDomain='+document.domain)">${lan['exit']}</a></div></li>
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
		<p title="隐藏工具条" class="panel_flex" id="pTbCtrl">
			<img class="icon_toolbar_up" src="<s:url value="/user/images/vipsite/b.gif"/>" id="imgTbCCtrl">
		</p>
	</div>	
</div>