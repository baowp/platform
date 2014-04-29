<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基本资料及联系方式</title>
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
</head>

<body>
<script>
	(function($) {
		// 改变默认配置
		var d = $.dialog.defaults;
		// 预缓存皮肤，数组第一个为默认皮肤
		d.skin = [ 'default', 'chrome', 'facebook', 'aero' ];
		// 是否开启特效
		d.effect = true;
		// 指定超过此面积的对话框拖动的时候用替身
		//d.showTemp = 100000;

	})(art);
	$(function() {
		$("#mapbar").click(function() {
			art.dialog.open('../platform/include/mapbar/map.jsp', {
				id : 'mapIframe',
				skin : 'aero',
				title : '地图定位(按esc可关闭)',
				left : 150,
				top : 40,
				fixed : true,
				width : 630,
				height : 470
			});
		})
		$("#submit").click(function() {
			if (checkValue()) {
				$.ajax({
					url : "/user/enterprise/updateuser/update",
					dataType : "json",
					async : false, //不进行异步操作
					data : {
						id : $("#userId").val(),
						name:$("#name").val(),
						sex : $("#sex").val(),
						position : $("#position").val(),
						cellphone : $("#cellphone").val(),
						phone : $("#phone").val(),
						fax : $("#fax").val(),
						address : $("#address").val(),
						email : $("#email").val(),
						qq : $("#qq").val(),
						msn : $("#msn").val(),
						url : $("#url").val(),
						zipcode : $("#zipcode").val()
					},
					success : function() {
						art.dialog.tips('修改成功！', 1);
						art.dialog.parent.location.reload();
						art.dialog.close();
					}
				})
			}
		})
	})
	function checkValue() {
		if ($("#name").val().trim() == '') {
			art.dialog.tips('姓名不能为空！', 1);
			return false;
		} else if ($("#position").val().trim() == '') {
			art.dialog.tips('职务不能为空！', 1);
			return false;
		} else {
			return true;
		}
	}
</script>
<div id="cpcontainer" class="container">
<s:hidden name="id" id="userId" value="%{userId}" />
<table class="tb tb2 ">
	<tbody>
		<tr>
			<td class="td27" width="80">性&nbsp;&nbsp;&nbsp别:</td>
			<td class="vtop rowform"><s:select list="#{'00':'男','01':'女'}"
				listKey="key" name="sex" id="sex" listValue="value" /></td>
		</tr>
		<tr>
			<td class="td27">姓&nbsp;&nbsp;&nbsp;名:</td>

			<td class="vtop rowform"><s:textfield name="name" id="name"
				cssClass="txt" /></td>
			<td class="vtop tips2"><span class="errorSpan">${errors.name[0]}</span></td>
		</tr>
		<tr>
			<td class="td27">职&nbsp;&nbsp;&nbsp;务:</td>

			<td class="vtop rowform"><s:textfield name="position"
				id="position" cssClass="txt" /></td>
			<td class="vtop tips2"><span class="errorSpan">${errors.position[0]}</span></td>
		</tr>
		<tr>
			<td class="td27">手&nbsp;&nbsp;&nbsp;机:</td>

			<td class="vtop rowform"><s:textfield maxlength="20"
				id="cellphone" name="cellphone" cssClass="txt" /></td>
			<td class="vtop tips2"><span class="errorSpan">${errors.cellphone[0]}</span></td>
		</tr>
		<tr>
			<td class="td27">电&nbsp;&nbsp;&nbsp;话:</td>

			<td class="vtop rowform"><s:textfield name="phone" id="phone"
				cssClass="txt" maxlength="20" /></td>
			<td class="vtop tips2"><span class="errorSpan">${errors.phone[0]}</span></td>
		</tr>
		<tr>
			<td class="td27">传&nbsp;&nbsp;&nbsp;真:</td>

			<td class="vtop rowform"><s:textfield name="fax" cssClass="txt"
				id="fax" /></td>
			<td class="vtop tips2"></td>
		</tr>
		<tr>
			<td class="td27">地&nbsp;&nbsp;&nbsp;址:</td>

			<td class="vtop rowform"><s:textfield name="address"
				id="address" cssClass="txt" /></td>
			<td class="vtop tips2"><input type="button" value="地图定位"
				id="mapbar" /><span class="errorSpan">${errors.address[0]}</span></td>
		</tr>
		<td class="td27">Email:</td>

		<td class="vtop rowform"><s:textfield disabled="true"  name="email" id="email"
			cssClass="txt" /></td>
		<td class="vtop tips2"><span class="errorSpan">${errors.email[0]}</span></td>
		</tr>
		<td class="td27">Q&nbsp;&nbsp;&nbsp;Q:</td>

		<td class="vtop rowform"><s:textfield name="qq" id="qq"
			onkeyup="this.value=this.value.replace(/\D/g,'')"
			onafterpaste="this.value=this.value.replace(/\D/g,'')" cssClass="txt" /></td>
		<td class="vtop tips2"></td>
		</tr>
		<td class="td27">MSN:</td>

		<td class="vtop rowform"><s:textfield name="msn" id="msn"
			cssClass="txt" /></td>
		<td class="vtop tips2"></td>
		</tr>
		<td class="td27">邮&nbsp;&nbsp;&nbsp;编:</td>

		<td class="vtop rowform"><s:textfield name="zipcode" maxlength="10" id="zipcode"
			onkeyup="this.value=this.value.replace(/\D/g,'')"
			onafterpaste="this.value=this.value.replace(/\D/g,'')" cssClass="txt" /></td>
		<td class="vtop tips2"></td>
		</tr>
		<td class="td27">公司主页:</td>

		<td class="vtop rowform"><s:textfield name="url" id="url"
			value="%{url}" cssClass="txt" /></td>
		<td class="vtop tips2"><span class="errorSpan">${errors.url[0]}</span></td>
		</tr>
		<tr>
			<td colspan="15">
			<div class="fixsel"><input type="button" id="submit" value="提交" /></div>
			</td>
		</tr>
	</tbody>
</table>
</div>
</body>
</html>
