<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择flash</title>
<link href="/user/css/tex.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link href="/css/dialog/jq_aero.css" rel="stylesheet" />
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<link href="/user/album/css/flashupload.css" rel="stylesheet" />

<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript">
	function checkFlash(obj) {
		$("#hid_div").dialog('open');
		$("#hid_div").dialog(
				{
					modal : true,
					width : 380,
					title : "设置flash属性",
					autoOpen : true,
					buttons : {
						确定 : function() {
							var url = "<embed src=\"http://img.51archetype.com/"+obj+"\" allowScriptAccess=\"always\" width=\""+$("#flashWidth").val()+"\" height=\""+$("#flashHeight").val()+"\"  wmode=\"transparent\" menu=\"true\"/>"
							callback(url);
						}
					}
				})		
	}

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
		menu="true" allowScriptAccess="never" allownetworking="internal" /></div>
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
<div id="hid_div" style="display:none;">
	<s:textfield id="flashWidth" label="宽度" theme="xhtml"></s:textfield><br/>
	<s:textfield id="flashHeight" label="高度" theme="xhtml"></s:textfield>
</div>
</body>
</html>
