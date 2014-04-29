<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<div class="you_contain">
<ul>
<s:iterator value="%{#request.topEntList}" status="st">
<s:if test="%{#st.index<5}">
<li><a href="${url}" target="_blank">${name}</a></li>
</s:if>
</s:iterator>
</ul>
</div>