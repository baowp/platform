<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>网站模板管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#add").click(function() {
				document.form1.submit();
			});
			$("#edit").click(function() {
				document.form1.action="updateCriteriaUserTemplate";
				document.form1.submit();
			});
			$("#return").click(function() {
				document.form1.action="addCriteriaUserTemplate";
				$("#form1_pageSize").val("");
				$("#form1_criteriaContent").val("");
				$("#form1_description").val("");
				$("#form1_name").val("");
				$("#form1_orderContent").val("");
				$("#add").css("display","block");
				$("#edit").css("display","none");
				$("#return").css("display","none");
			});
			
		});
		
		deleteCriteria=function(id){
			if(confirm("确定要删除这条数据么？"))
				window.location="deleteCriteriaUserTemplate?id=<s:property value="id" />&criteriaId="+id;
		}
		
		editCriteria=function(id){
			var dataType = $("#dataType"+id).attr("name");
			var pageType = $("#pageType"+id).attr("name");
			var pageSize = $("#pageSize"+id).html();
			var content = $("#content"+id).html();
			var orderContent = $("#ordercontent"+id).html();
			var description = $("#description"+id).html();
			var name = $("#name"+id).html();
			$("#form1_dataType").val(dataType);
			$("#form1_pageType").val(pageType);
			$("#form1_pageSize").val(pageSize);
			$("#form1_criteriaContent").val(content);
			$("#form1_description").val(description);
			$("#form1_orderContent").val(orderContent);
			$("#form1_name").val(name);
			$("#add").css("display","none");
			$("#edit").css("display","block");
			$("#return").css("display","block");
			$("#criteriaId").val(id);
			
		}
		
</script>
</head>
<body>
<s:form namespace="/admin" action="addCriteriaUserTemplate"
	method="post" enctype="multipart/form-data" id="form1" name="form1">
	<s:hidden name="id" />
	<s:hidden name="siteId" />
	<s:hidden name="criteriaId" id="criteriaId" />
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">网站模板数据</li>
		<li>设置模板“<s:property value="name" />(<s:property
			value="pageName" />)”的数据</li>
		<a href="listUserTemplate.action?siteId=<s:property value="siteId" />"
			class="return">返回</a>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<thead>
			<tr>
				<th class="header" name="name">变量名称</th>
				<th class="header" name="description">数据描述</th>
				<th class="header" name="dataType">数据类型</th>
				<th class="header" name="pageType">分页类型</th>
				<th class="header" name="pageSize">每页数据</th>
				<th class="header" name="content">查询条件</th>
				<th class="header" name="ordercontent">排序条件</th>
				<th class="header" name="op">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="template" value="criterias">
				<tr>
					<td id="name<s:property value="criteriaId" />"
						name="<s:property value="name" />"><s:property value="name" /></td>
					<td id="description<s:property value="criteriaId" />"
						name="<s:property value="description" />"><s:property
						value="description" /></td>
					<td id="dataType<s:property value="criteriaId" />"
						name="<s:property value="dataType.name()" />"><s:property
						value="dataType.toString()" /></td>
					<td id="pageType<s:property value="criteriaId" />"
						name="<s:property value="pageType" />"><s:if
						test="pageType==0">全部</s:if> <s:elseif test="pageType==1">分页</s:elseif>
					<s:elseif test="pageType==2">一页</s:elseif> <s:elseif
						test="pageType==3">一条</s:elseif> <s:else>其他</s:else></td>
					<td id="pageSize<s:property value="criteriaId" />"><s:property
						value="pageSize" /></td>
					<td id="content<s:property value="criteriaId" />"><s:property
						value="content" /></td>
					<td id="ordercontent<s:property value="criteriaId" />"><s:property
						value="ordercontent" /></td>
					<td><a
						href="javascript:deleteCriteria('<s:property value="criteriaId" />')">删除</a>&nbsp;
					<a
						href="javascript:editCriteria('<s:property value="criteriaId" />')">修改</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="8" class="tb_search" style="text-align: left"><s:textfield
					name="name">变量名称：</s:textfield> <s:textfield name="description">数据描述：</s:textfield><s:select
					name="dataType"
					list="@com.abbcc.util.constant.TemplateDataType@values()"
					listKey="name()" value="type.name()">数据来源：</s:select> <s:select
					list="#{'0':'全部','1':'分页','2':'一页','3':'单条'}" name="pageType">分页类型：</s:select>
				<s:textfield name="pageSize" size="2" tooltip="只能数字">每页数据量</s:textfield> <s:textfield
					name="criteriaContent" tooltip="格式： category=12">匹配条件</s:textfield> <s:textfield
					name="orderContent" tooltip="格式： desc=ordertime">排序条件</s:textfield>
				<button type="button" id="add" class="add">添加数据</button>
				<button type="button" id="edit" class="submit" style="display: none">修改数据</button>
				<button type="button" id="return" class="refresh"
					style="display: none">取消</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
