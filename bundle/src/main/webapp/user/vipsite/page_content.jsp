<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:if test="layout.belongPage==@com.abbcc.util.constant.layout.BelongPage@PRODUCT_DETAIL">
	<s:set var="mainmoduleList" value="layout.productmoduleList"/>
</s:if>
<s:else>
	<s:set var="mainmoduleList" value="layout.mainmoduleList"/>
</s:else>
<div id="content">
	<s:if test="layout.bodyType!=@com.abbcc.util.constant.layout.BodyType@SR">
		<div id="<s:property value='@com.abbcc.util.constant.layout.Position@content1'/>" class="sideLeft filter filterDom">
			<s:iterator value="layout.sidemoduleList">
				<s:include value="%{module.msign}"/>
			</s:iterator>
		</div>		
		<div id="<s:property value='@com.abbcc.util.constant.layout.Position@content2'/>" class="mainRight filter filterDom">
			<s:iterator value="#mainmoduleList">
				<s:include value="%{module.msign}"/>
			</s:iterator>
			<c:if test="${!empty param.defaultMain}">
				<c:import url="${param.defaultMain}"/>
			</c:if>
		</div>
	</s:if>	
	<s:else>
		<div id="<s:property value='@com.abbcc.util.constant.layout.Position@content2'/>" class="mainLeft filter filterDom">
			<s:iterator value="#mainmoduleList">
				<s:include value="%{module.msign}"/>
			</s:iterator>
			<c:if test="${!empty param.defaultMain}">
				<c:import url="${param.defaultMain}"/>
			</c:if>
		</div>		
		<div id="<s:property value='@com.abbcc.util.constant.layout.Position@content1'/>" class="sideRight filter filterDom">
			<s:iterator value="layout.sidemoduleList">
				<s:include value="%{module.msign}"/>
			</s:iterator>
		</div>
	</s:else>
</div>