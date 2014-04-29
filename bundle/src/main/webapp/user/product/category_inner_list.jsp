<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="resultList" status="st">
	<div id="a<s:property value="#st.index"/>"><a href="javascript:" id="a<s:property value="#st.index"/>"	class="categoryLink"
		onclick="chooseCategory('${categoryId}','${cdesc}${name}')">${cdesc}${name}</a></div>
</s:iterator>
<s:if test="resultList.size==0">
	<div>没有分类，请先在<a class="commlink" href="<s:url value="/user/product/category/show"/>">分类管理</a>里添加分类</div>
</s:if>
<script type="text/javascript">
var categoryMap={
		<s:iterator value="resultList">
		${categoryId} : '${cdesc}${name}',
		</s:iterator>
		0:0
};
$(".categoryLink").sort(function(a,b){
	return a.innerHTML>b.innerHTML?1:-1;
}).each(function(i,node){
	$("#dialog").append(node.parentNode);
});
</script>