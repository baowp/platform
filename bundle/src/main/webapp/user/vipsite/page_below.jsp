<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="<s:property value='@com.abbcc.util.constant.layout.Position@belowList'/>" class="belowList connectedList">
	<s:iterator value="layout.belowmoduleList">
		<s:include value="%{module.msign}"/>
	</s:iterator>
</div>