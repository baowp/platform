<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="z-index: 99; display: none;" class="rel editor" id="setStyleDetail">
	<div style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; top: 0px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;" class="abs">
		<div class="setContent">
			<div id="editTopic">
				<s:include value="edit.style.topic.jsp"/>
				<s:include value="edit.style.sign.jsp"/>
				<s:include value="edit.style.menu.jsp"/>
				<s:include value="edit.style.module.jsp"/>
				<s:include value="edit.style.whole.jsp"/>
			</div>
		</div>
		<div style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;" class="abs">
			<div class="setTitle"><span style="padding-left: 12px; padding-top: 3px; display: block;">风格设计</span></div>
		</div>
	</div>
</div>