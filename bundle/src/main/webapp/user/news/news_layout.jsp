<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript">
function changeSort(obj){
	var $row=$(obj).parents("tr[newsId]");
	if(!confirm("您确定要调换该分类顺序吗？")){
		obj.value=$row.attr("newsId")+","+$row.attr("sort");
		return ;
	}
	var info=obj.value.split(",");
	$("input[name=sourceCate]").val($row.attr("newsId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=targetCate]").val(info[0]);
	$("input[name=targetSort]").val(info[1]);
	var $form=$("form:first");
	$form.attr("action",$form.attr("action").replace("newssearchByCategory","newschange"));
	$form.submit();
}
function showChange(obj){
	var $form=$("form:first");
	$form.submit();
}

function stepSort(obj,type){
	var $row=$(obj).parents("tr[newsId]");
	$("input[name=sourceCate]").val($row.attr("newsId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=stepType]").val(type);
	var $form=$("form");
	$form.attr("action",$form.attr("action").replace("newssearchByCategory","newsstep"));
	$form.submit();
}

</script>

</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<div class="fv"><s:form action="newssearchByCategory"
	namespace="/user/news">
	<s:if test="%{#request.newsType!='image'}">
调序的分类:<s:select list="#request['sortMap']" listKey="key"
			listValue="value" name="category" value="%{#request.categoryId}"
			onchange="showChange(this)" />
	</s:if>
	<s:if test="%{#request.newsType=='image'}">
		<input type="button" onclick="window.location.href='newslayout'"
			value="所有新闻调序" />&nbsp&nbsp图片新闻调序</s:if>
	<s:else>所有新闻调序&nbsp&nbsp<input type="button"
			onclick="window.location.href='newsimageSort'" value="图片新闻调序" />
	</s:else>


	<!-- 用于调顺序 -->
	<s:hidden name="sourceCate" />
	<s:hidden name="sourceSort" />
	<s:hidden name="targetCate" />
	<s:hidden name="targetSort" />
	<s:hidden name="stepType" />
	<s:hidden name="newsType" value="%{#request.newsType}" />
</s:form></div>

<table class="listTable">
	<thead>
		<tr>
			<th>所属分类</th>
			<th>新闻标题</th>
			<th>属性</th>
			<th>排序</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="pageList.totalCount==0">
			<tr>
				<td align="center" colspan="5">没有相关数据!</td>
			</tr>
		</s:if>
		<s:iterator var="user" value="pageList.items" status="st">

			<tr newsId=${newsId } sort=${sort}>
				<td valign="middle" align="center"><a href="#"><s:property
					value="categoryName()" /></a></td>
				<td valign="middle" align="left"><a
					href="<s:url value='/%{staticpath}'/>">${title}</a></td>
				<td valign="middle" align="center">${imagenews eq
				'01'?'图片新闻':'一般新闻' }</td>
				<td valign="middle" align="center"><s:select
					list="#request['sortList']" listKey="key" listValue="value"
					name="categoryOrder" value="newsId+','+sort"
					onchange="changeSort(this)" /></td>

				<td valign="middle" align="center"><s:if
					test="newsId==resultList[0].newsId">上移</s:if> <s:else>
					<a href=javascript:void(0) onclick=stepSort(this,2)>上移</a>
				</s:else>|<s:if test="newsId==resultList[resultList.size()-1].newsId">下移</s:if>
				<s:else>
					<a href=javascript:void(0) onclick=stepSort(this,1)>下移</a>
				</s:else></td>


			</tr>

		</s:iterator>
	</tbody>
	<tfoot>
		<tr>


			<th colspan="8"><s:include value="/common/page.jsp">
				<s:param name="urlArgs"
					value="pageList?'firstId='+(%{firstId})+'&':''" />
			</s:include></th>

		</tr>
	</tfoot>
</table>


</body>
</html>
