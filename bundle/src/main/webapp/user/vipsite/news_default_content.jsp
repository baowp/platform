<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="content">
	<div id="content1" class="sideLeft filter filterDom">
		<s:include value="default_page_side.jsp"/>		
	</div>
	
	<div id="content2" class="mainRight filter filterDom">
		<c:choose>
			<c:when test="${!empty param.defaultMain}">
				<c:import url="${param.defaultMain}"/>
			</c:when>
			<c:otherwise>
				<s:include value="news_default_main.jsp"/>	
			</c:otherwise>
		</c:choose>	
	</div>
</div>