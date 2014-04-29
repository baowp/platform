<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="z-index: 99; display: none;" class="rel editor" id="setLayout">
	<div style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; text-align: left; " class="abs">
		<div id=layoutEditor class="setContent2 nb black">
			<div class="contTitle ml7px mt8px"><span class="pl23px b">选择网站布局</span></div>
			<a class="fl sl aUnCheck " onclick="tool.change.sideLeft();" href="#"><img class="borderFFF" src="<s:url value="/user/vipsite/tool/images/leftLayout.gif"/>"></a>
			<a class="fl sr aUnCheck " onclick="tool.change.sideRight();" href="#"><img class="borderFFF" src="<s:url value="/user/vipsite/tool/images/rightLayout.gif"/>"></a>
			<div class="clr"></div>
		</div>
		<div style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;" class="abs">
			<div class="setTitle"><span style="padding-left: 12px; padding-top: 3px; display: block;">网站布局</span></div>
		</div>
	</div>
</div>