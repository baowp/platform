<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
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
	$form.attr("action",$form.attr("action").replace("searchByCategoryNews","changeNews"));
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
	$form.attr("action",$form.attr("action").replace("searchByCategoryNews","stepNews"));
	$form.submit();
}

</script>
</head>

<body>
<br>
<div
	style="width: 100%; text-align: left; color: #ce0000; font-size: 15px; font-weight: bold">

<s:form action="searchByCategoryNews" namespace="/admin">
	<s:if test="%{#request.newsType!='image'}">
请选择您要调序的分类<s:select list="#session['sortMap']" listKey="key"
			listValue="value" name="category" value="%{#request.categoryId}"
			onchange="showChange(this)" />
		<br />

	</s:if>
	<s:if test="%{#request.newsType=='image'}">
		<a href="layOutNews">所有新闻调序</a>&nbsp&nbsp图片新闻调序</s:if>
	<s:else>所有新闻调序&nbsp&nbsp<a href="imageSortNews">图片新闻调序</a>
	</s:else>


	<!-- 用于调顺序 -->
	<s:hidden name="sourceCate" />
	<s:hidden name="sourceSort" />
	<s:hidden name="targetCate" />
	<s:hidden name="targetSort" />
	<s:hidden name="stepType" />
	<s:hidden name="newsType" value="%{#request.newsType}" />
</s:form>

<hr />
</div>

<table class="listTable">
	<thead>
		<tr>
			<td valign="middle" align="center">所属分类</td>
			<td valign="middle" align="center">新闻标题</td>
			<td valign="middle" align="center">属性</td>
			<td valign="middle" align="center">排序</td>
			<td valign="middle" align="center">操作</td>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="admin" value="pageList.items" status="st">

			<tr newsId=${newsId } sort=${sort}>
			<td valign="middle" align="center"><a href="#"><s:property value="categoryName()"/></a></td>
			<td valign="middle" align="center"><a href="<s:url value='/%{staticpath}'/>" >${title}</a></td>
			<td valign="middle" align="center">${imagenews eq '01'?'图片新闻':'一般新闻' }</td>
			<td valign="middle" align="center"><s:select list="#request['sortList']" listKey="key"
						listValue="value" name="categoryOrder" value="newsId+','+sort"
						onchange="changeSort(this)" /></td>
			
			<td valign="middle" align="center">【<s:if test="newsId==resultList[0].newsId">上移</s:if>
				<s:else>
					<a href=javascript:void(0) onclick=stepSort(this,2)>上移</a>
				</s:else>
				<s:if test="newsId==resultList[resultList.size()-1].newsId">下移</s:if>
				<s:else>
					<a href=javascript:void(0) onclick=stepSort(this,1)>下移</a>
				</s:else>】</td>


		</tr>

		</s:iterator>
	</tbody>
	<tr>


		<th colspan="8" class="tb_search"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>

	</tr>
</table>


</body>
</html>
