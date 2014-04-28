<%@ page language="java"
	import="java.util.*,com.abbcc.models.AbcCertificate"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript">
function setIsdisplay(obj) {
	$.getJSON("isdisplaycategorys?randomNumber="+Math.random(), {
		id:obj.getAttribute('categoryId'),
		isdisplay:obj.getAttribute("isdisplay")
	}, function(result) {
		if(result.affectRows==1){
			if(obj.getAttribute("isdisplay")=='1'){
				obj.setAttribute("isdisplay",'0');
				obj.innerHTML="显示";
			}else{
				obj.setAttribute("isdisplay",'1');
				obj.innerHTML="隐藏";
			}
		}
	});
}

function setName(obj) {
	var $row=$(obj).parents("tr:first");
	$.getJSON("updatecategorys?randomNumber="+Math.random(), {
		id:obj.getAttribute('categoryId'),
		name:$row.find("#name").val()
	}, function(result) {
		if(result.affectRows==1){
			alert('修改成功');
		}else{
			alert('修改失败');
		}
	});
}
function changeSort(obj){
	var $row=$(obj).parents("tr[categoryId]");
	if(!confirm("您确定要调换该分类顺序吗？")){
		obj.value=$row.attr("categoryId")+","+$row.attr("sort");
		return ;
	}
	var info=obj.value.split(",");
	$("input[name=sourceCate]").val($row.attr("categoryId"));
	$("input[name=sourceSort]").val($row.attr("sort"));
	$("input[name=targetCate]").val(info[0]);
	$("input[name=targetSort]").val(info[1]);
	var $form=$("form:first");
	$form.attr("action",$form.attr("action").replace("addcategorys","changecategorys"));
	$form.submit();
}
function checkCategory(categoryId,pName){
	$.getJSON("isRootcategorys?randomNumber="+Math.random(), {
		id:categoryId
	}, function(result) {
		if(result.affectRows==2){
			if (confirm('你确定删除吗?') == true) {
				window.location.href="delcategorys?id="+categoryId+"&&parentName="+pName
				return true;
			} else {
				return false;
			}
		}else{
			alert('您要删除的分类还有二级分类，不能删除，请先删除二级分类!');
			return false;
		}
	});
}
</script>
</head>

<body>
<br>
<div
	style="width: 100%; text-align: left; color: #ce0000; font-size: 15px; font-weight: bold">
<s:form action="addcategorys" namespace="/admin">
	分类名称
	<s:textfield name="name" cssStyle="margin-bottom:5px" value="" />
	<s:submit value="添加" />
	<span class="errorSpan">${errors.name[0]}</span>
	<!-- 用于调顺序 -->
	<s:hidden name="sourceCate" />
	<s:hidden name="sourceSort" />
	<s:hidden name="targetCate" />
	<s:hidden name="targetSort" />
</s:form>
<hr />
</div>

<s:form action="changecategorys" namespace="/admin" name="form1">
	<table class="listTable">
		<thead>
			<tr>
				<td valign="middle" align="center">所属分类</td>
				<td valign="middle" align="center">栏目名称</td>
				<td valign="middle" align="center">排序</td>
				<td valign="middle" align="center">是否显示</td>
				<td valign="middle" align="center">子栏目管理</td>
				<td valign="middle" align="center">操作</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="category" value="pageList.items" status="st">

				<tr categoryId=${categoryId } sort=${sort}>
			<td valign="middle" align="center"><a href="#"><s:property value="rootCategory()"/></a></td>
			<td valign="middle" align="center"><s:textfield id="name" name="name" value="%{name}" /></td>
			<td valign="middle" align="center"><s:select list="#request['sortMap']" listKey="key"
						listValue="value" name="categoryOrder" value="categoryId+','+sort"
						onchange="changeSort(this)" /></td>
			<td valign="middle" align="center"><a href="javascript:void(0)"
				onclick=setIsdisplay(this) categoryId=${categoryId
				}
				isdisplay=${isdisplay eq '1'?'0':'1'}>${isdisplay eq
			'1'?'显示':'隐藏' }</a></td>
			<td valign="middle" align="center"><a href="soncategorys?id=${categoryId}">>>></a></td>
			<td valign="middle" align="center">【<a
				href="#" onclick=setName(this) categoryId=${categoryId
				} name=>修改</a>/<a
				href="#"
				onclick="return checkCategory('${categoryId}','<s:property value="rootCategory()"/>')">删除</a>】</td>
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
</s:form>


</body>
</html>
