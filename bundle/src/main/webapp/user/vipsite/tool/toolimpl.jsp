<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="maintainable">
	<div class="maintain">
		<s:include value="toolbar.jsp"/>
		<s:include value="pattern/pattern.jsp"/>
		<s:include value="undefined/editor.jsp" />
		<s:include value="undefined/edit_title.jsp" />
	</div>
</s:if>