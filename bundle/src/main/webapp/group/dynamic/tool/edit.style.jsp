<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="z-index: 99; display: none;" class="rel editor" id="setStyleDetail">
	<div style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; top: 0px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous;" class="abs">
		<div class="setContent">
			<div id="editTopic">
				<jsp:include page="edit.style.topic.jsp"/>
				<jsp:include page="edit.style.sign.jsp"/>
				<jsp:include page="edit.style.menu.jsp"/>
				<jsp:include page="edit.style.module.jsp"/>
				<jsp:include page="edit.style.whole.jsp"/>
			</div>
		</div>
		<div style="top: -33px; left: 0px; color: rgb(255, 115, 0); font-weight: bold; text-align: left;" class="abs">
			<div class="setTitle"><span style="padding-left: 12px; padding-top: 3px; display: block;">风格设计</span></div>
		</div>
	</div>
</div>