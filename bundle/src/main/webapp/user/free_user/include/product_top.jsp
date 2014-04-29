<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="you_contain">
<ul>
<s:iterator value="%{#request.topProduct}" status="tp">
<s:if test="%{#tp.index<5}">
<li id="yui-gen1">
	<div class="image"><a target="_blank" class="draft_no_link"
		href="/jump.html?url=${url}"><img
		width="64" alt="${name}"
		src="<s:property value="mainPic.picUrl(5)"/>"></a></div>
	<div class="title"><a target="_blank"
		class="topicLink draft_no_link"
		href="/jump.html?url=${url}">${name}</a></div>
	<div class="price"><span class="cny">￥</span><em>${empty price ?'面议':price}</em></div>
	</li>
</s:if>
</s:iterator>
</ul>
</div>