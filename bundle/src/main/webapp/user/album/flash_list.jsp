<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	function checkFlash(obj) {
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
		callback(obj,hiddenId);   	// 回调
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
<h3>选择FLASH</h3>
</div>

<div class="cf">
<s:hidden id="hiddenId1"
	value="%{#parameters['pathIds1']}" />
<s:hidden id="hiddenId" name="hiddenId"
		value="%{#parameters['valueId']}" />
		<a href="/user/vipsite/album/flash_upload.jsp?pageType=2">上传flash</a>
<div id="t_02">
<s:iterator var="user" value="pageList.items"
	status="st">
	<div class="pho_01"
		onclick="checkFlash('<s:property value="serverPath"/>')">
	<div class="ho_y"><embed
		src="<s:property value="flashUrl(serverPath)"/>" wmode="transparent"
		menu="true"  allowScriptAccess="never" allownetworking="internal" /></div>
	<div class="ho_t">${filename}</div>
	</div>
</s:iterator>
</div>
</div>
</div>
<s:if test="pageList.pageCount>1">
<div><s:include value="../../common/page.jsp">
				<s:param name="pageList" value="pageList" />
			</s:include></div></s:if>
</body>
</html>
