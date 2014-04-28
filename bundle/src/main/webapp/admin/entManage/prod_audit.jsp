<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品审核</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
	<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#submitform").click(function(){
				document.form1.action="editAuditProd";
				document.form1.submit();
			});
			$("#checkall").click(function() { 
				   $("input:checkbox[id='prodIds-1']").attr('checked', true);
				}); 
				
				$("#uncheckall").click(function() { 
				    $("input:checkbox[id='prodIds-1']").attr('checked', false);
				});
		});
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		function sortEnt(id,value){
			$("#entId").val(id);
			$("#entName").val(value);
			document.form1.submit();
		}
		function reset(){
			$("#entId").val("");
			$("#entName").val("");
		}
		function openDialog(obj){
			$("#proddesc").html(obj);
			$("#proDialog").dialog('open');
			$("#proDialog").dialog({
				title:"产品描述",
				width:480
			});
		}
		
</script>
</head>
<body>
<s:form namespace="/admin" action="viewAuditProd" method="post"
	id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">产品审核</li>
		<li><s:radio name="prodState"
			list="%{#{'00':'未审核','01':'已审核','02':'审核不通过'}}" value="prodState" /> <s:submit
			 cssClass="search" value="查询"/>
			<s:if test="entId!=''&&entId!=null">
			现在仅仅查看“<s:property value="entName"/>”的产品 【<a href="javascript:reset()">清空选择</a>】
			</s:if>
			</li>
	</ul>
	</div>
	<div id="sub_info">
	<div id="man_zone"><s:if test="result!=''&&result!=null">
		<div id="warning">${result}</div>
	</s:if>
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" id="tablesorter">
		<s:hidden name="page" id="page" />
		<s:hidden name="entId" id="entId" />
		<s:hidden name="entName" id="entName" />
		<thead>
			<tr>
				<th class="header" name="name">产品名称</th>
				<th class="header" name="category">产品分类</th>
				<th class="header" name="enterprise">添加企业</th>
				<th class="header" name="username">添加用户</th>
				<th class="header" name="addtime">添加时间</th>
				<th class="header" name="proddesc">产品描述</th>
				<th class="header" name="pic">产品图片</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="pro" value="pageList.items" status="st">
				<tr>
					<td>${name}</td>
					<td><s:property value="categoryName()" /></td>
					<td><a href="javascript:sortEnt('<s:property value="enterpriseId" />','<s:property value="enterprise().name" />')"><s:property value="enterprise().name" /></a></td>
					<td><s:property value="addUser().username" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><a href="infoProd?id=${productId}"> 产品详细</a></td>
					<td><a href="<s:property value="productMainPic().picUrl(3)" />" target="_blank">查看图片<a></td>
					<td>
					<s:if test="state=='00'">
					<s:checkboxlist name="prodIds" list="%{#{productId+';01':'审核通过',productId+';02':'审核不通过'}}"></s:checkboxlist>
					</s:if>
					</td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="8" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<s:if test="prodState=='00'">
			<tr>
				<th colspan="8" class="tb_search">
				<button class="checkall" id="checkall" type="button">全选</button>
				<button class="uncheckall" id="uncheckall" type="button">反选</button>
				<button type="button" id="submitform" class="submit">提交审核</button>
				</th>
			</tr>
			</s:if>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
<div id="proDialog" style="display:none;">
<div id="proddesc"></div>
</div>
</body>
</html>
