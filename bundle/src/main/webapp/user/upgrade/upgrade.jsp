<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品升级</title>
<link href="/user/upgrade/css/upgrade.css" rel="stylesheet" />
<link href="/css/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/jquery/form.min.js"></script>
<link href="/css/dialog/jq_aero.css" rel="stylesheet" />
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<style type="text/css">
.dHide {
	display: none;
}

.dShow {
	display: block;
}
</style>
<script type="text/javascript">
	function openMessage(obj) {
		$("#hid_div").dialog('open');
		$("#hid_div").dialog({
			modal : true,
			width : 900,
			title : "留言",
			autoOpen : true,
			buttons : {
				确认发送报价 : function() {
					if (clickBtn()) {
						$(".ui-button").attr("disabled", "disabled");
						$("#quote1").ajaxSubmit(function(data) {
							$(".ui-button").attr("disabled", "false");
							alert(data)
							$("#hid_div").dialog('close');
						})
					}
				}
			}
		})
	}
	$(function() {
		$("#divUpgrade ul li").mouseover(function() {
			$("#" + $(this).attr("class")).attr("class", "dShow");
		}).mouseout(function() {
			$("#" + $(this).attr("class")).attr("class", "dHide");
		})

	})
</script>
</head>
<body>
<div class="abbccbar lightgray">
<div class="w970 c abbccbar-container">
<div class="account-sign-status left h27">
<ul>
	<li class="account-welcome">您好，<span id="accountId"><a
		target="_self" href="" class="account-signed">
	${sessionScope.abbccuser.name }</a> </span>&nbsp;</li>
	<li id="accountSignOut"><a title="退出" href="">退出</a></li>
</ul>
</div>

</div>
</div>
<div class="h100 upgrade_head">
<div class="w970 h100 upgrade_head-content">
<div class="left h100 upgrade_head-logo"><img
	src="/images/logot.jpg" alt="东方五金" class="mt29"></div>
<div class="left h100 logo_l"></div>
<div class="left h100 upgrade_head-up">版本升级</div>
</div>
</div>
<div class="w970 c upgrade_menu"></div>
<div class="w970 c up_se" id="divUpgrade">
<ul class="up">
	<li class="up11"></li>
	<li class="up0"><input type="button" id="up00" name="up0"
		class="up_btn" value="免费体验" />
	<div id="up0" class="dHide"><span class="tip1"></span></div>
	</li>
	<li class="up1"><input type="button" id="up01" name="up1"
		class="up_btn" value="立即购买" />
	<div id="up1" class="dHide"><span class="tip1"></span></div>
	</li>
	<li class="up2"><input type="button" id="up02" name="up2"
		class="up_btn" value="立即购买" />
	<div id="up2" class="dHide"><span class="tip1"></span></div>
	</li>
	<li class="up3"><input type="button" id="up03" name="up3"
		class="up_btn" value="立即购买" />
	<div id="up3" class="dHide"><span class="tip2"></span></div>
	</li>

</ul>
</div>
<div class="upgrade_main w970 c o">
<div class="h27 t title">
<h4></h4>
</div>
<div class="content">
<div class="h320 upgrade_attr"></div>
<div class="p10 o upgrade_desc-box"></div>
</div>
</div>
<div class="f12 c product-statement-widget"></div>
<div id="hid_div" style="display: none;" class="quoteMsg"></div>
</body>
</html>
