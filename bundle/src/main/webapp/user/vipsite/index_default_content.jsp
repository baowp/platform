<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="content">
	<div id="<s:property value='@com.abbcc.util.constant.layout.Position@content1'/>" class="sideLeft filter filterDom">
		<s:include value="default_page_side.jsp"/>		
	</div>
	
	<div id="<s:property value='@com.abbcc.util.constant.layout.Position@content2'/>" class="mainRight filter filterDom">
		<s:include value="index_default_main.jsp"/>		
	</div>
</div>