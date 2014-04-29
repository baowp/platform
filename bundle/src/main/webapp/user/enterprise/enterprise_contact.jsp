<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/user/js/show_onmouseover.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript">
function stepSort(obj,type){
	var $row=$(obj).parents("tr[enterpcontactId]");
	$("input[name=sourceCate]").val($row.attr("enterpcontactId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=stepType]").val(type);
	
	$("#form1").submit();
}
</script>
</head>

<body>
<s:form action="contactstep" namespace="/user/contact" id="form1">
	<!-- 用于调顺序 -->
	<s:hidden name="sourceCate" />
	<s:hidden name="sourceSort" />
	<s:hidden name="targetCate" />
	<s:hidden name="targetSort" />
	<s:hidden name="stepType" />
</s:form>
<s:if test="result!=''&&result!=null">
		<div id="warning" style="DISPLAY: none">${result}</div>
		<script type="text/javascript">
		window.onload   =   new   function()
		{
			parent.makeState($("#warning").html());
		}

		</script>
</s:if>
<br>

<input type="button" onclick="window.location.href='/user/enterprise/enterprise_contact_add.jsp'" value="添加联系人"/>
<table class="listTable">
	<thead>
		<tr>
		<th>姓名</th>
		<th>性别</th>
		<th>职务</th>
		<th>电话</th>
		<th>Email</th>
		<th>排序</th>
		<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<s:if test="pageList.totalCount==0">
	<tr><td align="center" colspan="7">没有相关数据!</td></tr>
</s:if>
		<s:iterator var="user" value="pageList.items" status="st">

		<tr enterpcontactId=${enterpcontactId } sort=${sort}>
			<input type="hidden" name="userId"
				value="<s:property value='userId'/>" />
			<td valign="middle" align="center"><a href="#"
				id="<s:property value='#st.index+1'/>"
				onmouseover="show(this,'<s:property value='#st.index+100'/>')"
				onmouseout="hide('<s:property value='#st.index+100'/>')">${name}</a>
			<div id="<s:property value='#st.index+100'/>"
				style="BORDER-RIGHT: black 1px solid; PADDING-RIGHT: 20px; BORDER-TOP: black 1px solid; PADDING-LEFT: 20px; Z-INDEX: 100; VISIBILITY: hidden; PADDING-BOTTOM: 20px; BORDER-LEFT: black 1px solid; WIDTH: 180px; PADDING-TOP: 20px; BORDER-BOTTOM: black 1px solid; POSITION: absolute; background-color: #F0FFFF"
				onmouseover="show(i,'<s:property value='#st.index+100'/>')"
				onmouseout="hide('<s:property value='#st.index+100'/>')"><nobr>详细信息</nobr><br>
			<table>
				<tr>
					<td>姓名:</td>
					<td>${name}</td>
				</tr>
				<tr>
					<td>email:</td>
					<td>${email}</td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><s:property value="sexName()"/></td>
				</tr>
				<tr>
					<td>电话:</td>
					<td>${phone}</td>
				</tr>
				<tr>
					<td>手机:</td>
					<td>${cellphone}</td>
				</tr>
				<tr>
					<td>网址:</td>
					<td>${url}</td>
				</tr>
				<tr>
					<td>职务:</td>
					<td>${position}</td>
				</tr>
			</table>
			</div>

			</td>

			<td valign="middle" align="center">${sex=='00'?'男':sex=='01'?'女':''}</td>
			<td valign="middle" align="center">${position}</td>
			<td valign="middle" align="center">${phone}</td>
			<td valign="middle" align="center">${email}</td>
			<td valign="middle" align="center"><s:if test="enterpcontactId==resultList[0].enterpcontactId">上移</s:if>
				<s:else>
					<a href=javascript:void(0) onclick=stepSort(this,2)>上移</a>
				</s:else>|<s:if test="enterpcontactId==resultList[resultList.size()-1].enterpcontactId">下移</s:if>
				<s:else>
					<a href=javascript:void(0) onclick=stepSort(this,1)>下移</a>
				</s:else></td>
			<td valign="middle" align="center"><a href="/user/contact/contactupdatePage?id=${enterpcontactId }">修改</a>|<a
				href="/user/contact/contactdel?id=${enterpcontactId}"
				onclick="javascript:return del()">删除</a></td>


		</tr>

	</s:iterator>
	</tbody>
	<tfoot>
	<tr>
		<th colspan="8"><s:include
			value="../../common/page.jsp">
			<s:param name="pageList" value="pageList" />
		</s:include></th>
	</tr>
	</tfoot>
</table>


</body>
</html>
