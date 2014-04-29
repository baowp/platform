<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logo管理</title>
<link href="/user/css/textd.css" rel="stylesheet" type="text/css" />
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="/js/util/onload_colorbox.js"></script>
</head>

<body>
<script>
	$(function() {
		$("#updatePic").click(function() {
			$("#pic").show()
		});
		$("#postButton").click(function() {
			$.ajax({
				type : "POST",
				url : "/user/enterprise/company/uploadLogo",
				data : {
					attId : $("#attId").val(),
					serverPath : $("#picPath").val()
				},
				success : function(result) {
					$("#logoimg").attr("src", result);
				}
			});
		});

	})
</script>
<div id="cpcontainer" class="container">
<div class="top">
<h3>Logo管理</h3>
</div>

<table width="100%" border="0" cellpadding="0">
	<tr>
		<td width="50%">&nbsp;</td>
		<td width="50%">&nbsp;</td>
	</tr>
	<tr>
		<td height="128" align="left" valign="middle"><s:if
			test="#request.logoList==null">
您现在还没有上传LOGO,<a href="javascript:void(0)" id="updatePic" class="pic">点击上传</a>
		</s:if><s:else>
			<div class="logo"><img id="logoimg"
				src="<s:property value="logoPath(#request.logoList.serverPath)"/>"
				width="108" height="118" /><a href="javascript:" id="updatePic"
				class="pic">修改</a></div>
		</s:else></td>
		<td>&nbsp;</td>
	</tr>

	<tr style="display: none;" id="pic">
		<td height="28"><s:form action="uploadLogo"
			namespace="/user/enterprise/company">
			<s:hidden name="attId" id="attId" value="%{#request.logoList.attId}"></s:hidden>
			<s:textfield name="serverPath" id="picPath" readonly="true" />
			<a id="various3"
				href="<s:url value="/user/album/albumshowUploadPage"/>?valueId=picPath">点击选择相片</a>
			<a href="javascript:"><input type="button" id="postButton"
				value="提交" title="提交" /></a>
		</s:form></td>
		<td height="28">建议尺寸120*60</td>
	</tr>
</table>
</div>
</body>
</html>
