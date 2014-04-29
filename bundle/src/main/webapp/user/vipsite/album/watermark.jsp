<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/jquery.ui.min.css">
<link rel="stylesheet" href="/user/vipsite/album/css/watermark.css">
<script src="/js/jquery.js"></script>
<script src="/js/jquery/ui/jquery.ui.core.js"></script>
<script src="/js/jquery/ui/jquery.ui.widget.js"></script>

<script src="/js/jquery/ui/jquery.ui.mouse.js"></script>
<script src="/js/jquery/ui/jquery.ui.draggable.js"></script>
<script src="/js/jquery/ui/jquery.ui.sortable.js"></script>
<script type="text/javascript"
	src="/user/vipsite/album/js/iColorPicker.js"></script>
<script type="text/javascript" src="/user/vipsite/album/js/watermark.js"></script>
</head>
<body>
<div id="new-content">
<div class="cell-head">
<h3>设置图片水印</h3>
</div>
<s:action name="getWatermark" namespace="/user/watermark"></s:action> <s:hidden
	id="watermarkId" value="%{#request.watermark.watermarkId}"></s:hidden>
<div class="cell-con">
<div class="img-wrap"><span id="parent"
	style="font-size:${watermark.fontSize};color:${watermark.fontColor};${empty watermark.elocation?'display:none;':'' } ">${sessionScope.abbccEnterprise.name}</span>
<br />
<span id="www"
	style="font-size:${watermark.fontSize};color:${watermark.fontColor};${empty watermark.dlocation?'display:none;':'' }">${domainUrl}</span>

</div>
<h2>参数设置</h2>
<p><s:select id="select6"
	list="#{'5px':'5','10px':'10','12px':'12','14px':'14','16px':'16','18px':'18','20px':'20','22px':'22','26px':'26','30px':'30'}"
	label="大小" listKey="key" listValue="value"
	value="%{#request.watermark.fontSize}" theme="xhtml" /></p>
<p>颜色：<input id="mycolor" name="mycolor" type="text"
	value="${empty watermark.fontColor?'#ffcc00':watermark.fontColor}"
	class="iColorPicker" /></p>
<p><input type="checkbox" ${empty
	watermark.elocation?'':'checked' }
	autocomplete="off" id="showCom"
	name="showMainText"><label for="showCom">显示您的公司名称</label></p>
<p id="peLocation"
	style="<s:if test="%{#request.watermark.elocation==null}">display:none;</s:if>">
<s:select id="eLocation"
	list="#{'NorthWest':'左上角', 'North':'正上方', 'NorthEast':'右上角', 'West':'左居中', 'Center':'中间', 'East':'右居中', 'SouthWest':'左下角', 'South':'正下方', 'SouthEast':'右下角'}"
	label="位置" listKey="key" listValue="value"
	value="%{#request.watermark.elocation}" theme="xhtml" /></p>
<p><input type="checkbox" ${empty
	watermark.dlocation?'':'checked' }
	autocomplete="off" id="showWWW"
	name="showFootText"><label for="showWWW">显示企业网站</label></p>
<p id="pdLocation"
	style="<s:if test="%{#request.watermark.dlocation==null}">display:none;</s:if>">
<s:select id="dLocation"
	list="#{'NorthWest':'左上角', 'North':'正上方', 'NorthEast':'右上角', 'West':'左居中', 'Center':'中间', 'East':'右居中', 'SouthWest':'左下角', 'South':'正下方', 'SouthEast':'右下角'}"
	label="位置" listKey="key" listValue="value"
	value="%{#request.watermark.dlocation}" theme="xhtml" /></p>

<p class="btn-box">
<button class="btn"><a>保 存</a></button>
</p>

</div>

</div>
<script type="text/javascript">
jQuery("#parent").css("font-size", $("#select6").val());
jQuery("#www").css("font-size", $("#select6").val());
	if ($("#showCom").attr("checked") == true)
		toELocation($("#parent"), $("#eLocation"))
		
	if ($("#showWWW").attr("checked") == true)
		toDLocation($("#www"), $("#dLocation"))
</script>
</body>
</html>