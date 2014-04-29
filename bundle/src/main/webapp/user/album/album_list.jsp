<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择图片</title>
<link href="/user/css/tex.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript">
	$(function() {
		$("#searchSub").click(function() {
			$("#fileName").val($("#searchName").val());
			var $form = $("form:first");
			$form.submit();
		})
	})
	function showChange(obj) {
		$("#fileName").val("");
		var $form = $("form:first");
		$form.submit();
	}
	function checkPic(obj) {
		var hiddenId = $("#hiddenId").val();
		var hiddenId1 = $("#hiddenId1").val();
		if (hiddenId == "" && hiddenId1 == "")
			window.parent.document.getElementById("picPath").value = obj;
		else if (hiddenId1 == "")
			window.parent.document.getElementById(hiddenId).value = obj;
		else
			window.parent.document.getElementById(hiddenId1).value = obj;
		if (parent.document.getElementById("showPic"))
			parent.document.getElementById("showPic").src = "http://img.51archetype.com/"
					+ obj;
		callback(obj,hiddenId);   // 回调
		parent.$.colorbox.close();
	}
	var callback=$.noop;
	<c:if test="${not empty param.callback}">
		callback=parent.${param.callback};
	</c:if>
</script>
</head>

<body>
<div class="container">
<div class="top">
<h3>选择相片</h3>
</div>

<div class="cf">
<div id="t_01"><s:hidden id="hiddenId1"
	value="%{#parameters['pathIds1']}" /> <s:form namespace="/user/album"
	action="albumchooseAlbum">
	<s:hidden id="hiddenId" name="hiddenId"
		value="%{#parameters['valueId']}" />
	<s:hidden id="fileName" name="fileName" />
	<label> <s:select list="#request['albumList']" listKey="key"
		listValue="value" name="albumId" id="albumId"
		onchange="showChange(this)" label="请选择相册" theme="xhtml" /> </label>
	<s:textfield id="searchName" />
	<input id="searchSub" type="button" value="查找" />

	<a
		href="<s:url value='/user/album/albumuploadPic'/>?id=<s:property value="%{#request.albumIdisNull}"/>&&pageType=3">添加图片</a></div>
<div id="t_02"><s:iterator var="user" value="pageList.items"
	status="st">
	<div class="pho_01"
		onclick="checkPic('<s:property value="serverPath"/>')">
	<div class="ho_y"><img
		src="<s:property value="picUrl5(serverPath)"/>" width="108"
		height="125" /></div>
	<div class="ho_t">${filename}</div>
	</div>
</s:iterator></div>
</s:form></div>
</div>
</body>
</html>
