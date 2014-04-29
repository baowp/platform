<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="!maintainable">
	<div>
		<s:include value="page_topbar.jsp"/>
		<s:include value="dolog.jsp"/>
	</div>
	<div>
		<s:include value="../subsite/page_message.jsp"/>
	</div>
</s:if>
