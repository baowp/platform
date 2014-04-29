<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>证书管理</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/metadata.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/cookie.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/user/product/js/list.js"/>"></script>
<script type="text/javascript">
function stepSort(obj,type){
	var $row=$(obj).parents("tr[certificateId]");
	$("input[name=sourceCate]").val($row.attr("certificateId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=stepType]").val(type);
	
	$("#form1").submit();
}
</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<s:form action="step" namespace="/user/enterprise/certificate"
	id="form1">
	<!-- 用于调顺序 -->
	<s:hidden name="sourceCate" />
	<s:hidden name="sourceSort" />
	<s:hidden name="targetCate" />
	<s:hidden name="targetSort" />
	<s:hidden name="stepType" />
</s:form>
<br/>
<div class="listTab">
<dl class="focus">
	<a href="javascript:">证书管理</a>
</dl>

<input type="button"
	onclick="window.location.href='/user/enterprise/certificate_add.jsp'"
	value="添加证书" />&nbsp<a href="javascript:searchable()">筛选功能</a></div>

<div class="searchArea">
<s:form action="showCertificate" namespace="/user/enterprise/certificate"><table><tr><td>证书名称</td><td><s:textfield  name="name"/> </td><td><s:submit value="查询"/></td></tr></table></s:form>
</div>
<table class="listTable">
	<thead>
		<tr>
			<th>图片</th>
			<th>证书名称</th>
			<th>证书类型</th>
			<th>发证机构</th>
			<th>审核状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="7">没有相关数据!</td></tr>
</s:if>
	<s:iterator var="user" value="pageList.items">
		<tr certificateId=${certificateId } sort=${sort }>
			<td valign="middle" align="center"><input type="hidden" name="cerId"
				value="<s:property value='certificateId'/>" /><img
				src="<s:property value="picUrl(5)"/>" width="120" height="75"/></td>
			<td valign="middle" align="center"><a
				href="<s:property value="picUrl(3)"/>" target="_blank"><s:property
				value='name' /></a></td>
			<td valign="middle" align="center"><s:if test="type==01">基本证书</s:if><s:if
				test="type==02">一般证书</s:if><s:if test="type==03">税务证书</s:if><s:if
				test="type==04">荣誉证书</s:if><s:if test="type==05">其它证书</s:if></td>
			<td valign="middle" align="center"><s:property value='organize' /></td>
			<td valign="middle" align="center">${state=='00'?'<font
			color=red>未审核</font>':state=='01'?'通过审核':'<font
			color=red>审核未通过</font>'}</td>
			
			<td valign="middle" align="center"><a
				href="showUpdateCertificate?id=<s:property value='certificateId'/>">修改</a>|<a
				href="delCertificate?id=<s:property value='certificateId'/>"
				onclick="javascript:return del()">删除</a></td>

		</tr>
	</s:iterator>
</tbody>
	<tfoot>
		<tr>


			<th colspan="8"><s:include value="../../common/page.jsp">
				<s:param name="pageList" value="pageList" />
			</s:include></th>

		</tr>
	</tfoot>
</table>

</body>
</html>
