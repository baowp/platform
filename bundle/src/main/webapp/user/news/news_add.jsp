<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>

<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<link href="../css/div.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<s:include value="/user/css/table.jsp"></s:include>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript" src="/js/util/onload_colorbox.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$("#isImagenews").click(function() {
			if($(this).is(":checked"))
				$("#pic").show();
			else
				$("#pic").hide();
		});
		$("#isText").change(function() {
			var lbmc = $("#watermark");
			lbmc.toggle();
		});
		$("#textPic").click(function() {
			document.getElementById("addText").style.display = "";
			document.getElementById("addPic").style.display = 'none';
		});
		$("#textPic2").click(function() {
			document.getElementById("addText").value = "";
			document.getElementById("addPic").style.display = "";
			document.getElementById("addText").style.display = 'none';
		});

		$("#checkPic").change(function() {
			//var  lbmc = $("#pictrue");
			if ($("#checkPic").attr("checked") == false) {
				$("#fromFile").show();
				$("#pictrue").hide();
				$("#picPath").val("");
			} else {
				$("#fromFile").hide();
				$("#pictrue").show();
				var file = $("#upload");
				file.after(file.clone().val(""));
				file.remove();
			}

		});
	});

	function forNkey(obj) {
		$("#nkey").val(document.getElementById("title").value);

	}
//-->
</script>
<title>添加新闻</title>
</head>
<body>
<s:if test="errors.addState[0]!=null">
	<div id="warning">${errors.addState[0]}</div>
</s:if>
<div id="body"><s:form action="newsaddNews" namespace="/user/news"
	enctype="multipart/form-data">
	<div class="editGrid"><span class="errorSpan" id="keySpan"></span>
	<table width="100%">
		<tr>
			<td width="8%">标题<font color="red">*</font></td>
			<td width="70%"><s:textfield id="title" name="title"
				cssClass="txt" onkeyup="forNkey(this)"/><span
				class="errorSpan" id="titleSpan">${errors.title[0]}</span></td>
		</tr>
		<tr>
			<td>关键字<font color="red">*</font></td>
			<td><s:textfield id="nkey" name="nkey" cssClass="txt"
				 /><span class="errorSpan" id="nkeySpan">${errors.nkey[0]}</span></td>
		</tr>
		<tr>
			<td>分类<font color="red">*</font></td>
			<td><s:select list="#request['sortMap']" listKey="key"
				listValue="value" name="category" value="categoryId" /></td>

		</tr>
		<tr>
			<td>内容<font color="red">*</font></td>
			<td><s:textarea name="content"  rows="12" cols="95" cssStyle="width: 100%;height:250px;"  cssClass="ckeditor" /><span
				class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td>文章属性</td>
			<td><s:checkbox name="isImagenews" id="isImagenews" />图片&nbsp<s:checkbox
				name="isRollingnews" />滚动新闻&nbsp<s:checkbox name="isTopnews" />置顶</td>
		</tr>

		<tr id="pic" style="display: none;">
			<td>图片地址</td>
			<td id="pictrue"><img src="" id="showPic"
				style="width: 108px; height: 80px; margin-top: 20px;" /><s:textfield
				name="picPath" id="picPath" readonly="true" /><a id="various3"
				href="<s:url value="/user/album/albumshowUploadPage"/>">点击选择相片</a></td>
		</tr>

		<tr>
			<td>来源</td>
			<td><s:textfield name="origin" cssClass="txt"
				onblur="checkKey(this)" /><span class="errorSpan">${errors.origin[0]}</span></td>
		</tr>
		<tr>
			<td>作者</td>
			<td><s:textfield name="author" cssClass="txt"
				onblur="checkKey(this)" /><span class="errorSpan">${errors.author[0]}</span></td>
		</tr>
		<tr>
			<td>&nbsp</td>
			<td><s:submit value="提交" /></td>
		</tr>
	</table>
	</div>
</s:form></div>
</body>
</html>