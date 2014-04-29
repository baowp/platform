<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="resultList">
	<div><a href="javascript:void(0)"
		onclick="chooseProduct('${productId}','${name}')">${name}</a></div>
</s:iterator>
<s:if test="!resultList.size">
	没有产品，请加<a href="<s:url value="/user/product/add"/>" class="commlink">添加产品</a>
</s:if>
<script type="text/javascript">
var productMap={
		<s:iterator value="resultList">
		${productId} : '${name}',
		</s:iterator>
		0:0
}
</script>