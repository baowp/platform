<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>查看管理员</title>
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<link rel="stylesheet" type="text/css" href="css/flexigrid/flexigrid.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/flexigrid.js"></script>
		<script type="text/javascript">
	var type = "";
	var adminId = "";
	var state = 1;
	function changeActive(id) {
		adminId = id;
		var imgId = "img" + adminId;
		if ($('#' + imgId + '').attr("src") == "images/active.png")
			state = 2;
		else
			state = 1;
		var url = 'updateAdminState';
		var params = {
			id :id,
			state :state
		};
		$.ajax( {
			url :url,
			type :"post",
			data :params,
			success :callbackFun
		});
	}
	function callbackFun(data) {
		if (state == 1) {
			var id = "img" + adminId;
			$('#' + id + '').attr( {
				src :"images/active.png",
				alt :"激活"
			});
		}
		if (state == 2) {
			var id = "img" + adminId;
			$('#' + id + '').attr( {
				src :"images/inactive.png",
				alt :"注销"
			});
		}
	}
</script>
		<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.flexigrid div.fbutton .add {
	background: url(css/images/add.png) no-repeat center left;
}

.flexigrid div.fbutton .delete {
	background: url(css/images/close.png) no-repeat center left;
}
</style>

	</head>
	<body>
		<div id="man_zone">
			<table id="flex1" style="display: none"></table>

			<script type="text/javascript">
	$("#flex1").flexigrid( {
		url :'viewPageAdmin',
		dataType :'json',
		colModel : [ {
			display :'name',
			name :'name',
			width :40,
			sortable :true,
			align :'center'
		}, {
			display :'username',
			name :'username',
			width :180,
			sortable :true,
			align :'left'
		}],
		sortname :"name",
		sortorder :"asc",
		usepager :true,
		title :'用户列表',
		useRp :true,
		rp :15,
		showTableToggleBtn :true,
		width :700,
		height :200
	});
</script>

		</div>
	</body>
</html>
