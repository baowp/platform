<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div id="<s:property value='@com.abbcc.util.constant.layout.Position@headList'/>" class="headList connectedList">
		<s:iterator value="layout.headmoduleList">
			<s:include value="%{module.msign}"/>
		</s:iterator>
	</div>