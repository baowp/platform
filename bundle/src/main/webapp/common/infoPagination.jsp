<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul>
	<s:iterator var="user" value="%{#request.countList}" status="st">
		<s:property escape="false" />
	</s:iterator></ul>
