<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="screen">
<div id="screen-spc"
	style="background: none repeat scroll 0% 0% rgb(184, 211, 246); border-bottom: 1px solid rgb(101, 147, 207);"></div>
<s:include value="left.jsp"></s:include>
<div id="new-content">
<div class="panel layout-panel layout-panel-center"
	style="left: 160px; top: 130px; width: 844px;">
<div
	style="background: none repeat scroll 0% 0% rgb(238, 238, 238); overflow-y: hidden; width: 842px; height: 100%;"
	region="center" id="mainPanle"
	class="layout-body panel-body panel-body-noheader" title="">
<div border="false" fit="true" class="easyui-tabs tabs-container"
	id="tabs" style="width: 842px;height:430px; ">
<div class="tabs-panels tabs-panels-noborder"
	style="height: 430px; width: 842px;">

<iframe name="main" id="mainFrame" scrolling="auto" frameborder="0"
	src="action/main.jsp" style="width: 842px; height: 100%;"></iframe>
</div>
</div>
</div>
</div>

</div>