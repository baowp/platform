<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新闻审核</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#submitform").click(function(){
				document.form1.action="editAuditNews";
				document.form1.submit();
			});
			$("#checkall").click(function() { 
				   $("input:checkbox[id='newsIds-1']").attr('checked', true);
				}); 
				
				$("#uncheckall").click(function() { 
				    $("input:checkbox[id='newsIds-1']").attr('checked', false);
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
		
</script>
</head>
<body>
<s:form namespace="/admin" action="viewAuditNews" method="post"
	id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">新闻审核</li>
		<li><s:radio name="newsState"
			list="%{#{'00':'未审核','01':'已审核','02':'审核不通过'}}" value="newsState" />
		<s:if test="entId!=''&&entId!=null">
			现在仅仅查看“<s:property value="entName" />”的新闻 【<a
				href="javascript:reset()">清空选择</a>】
			</s:if> <s:textfield name="searchKey" id="searchKey">新闻标题：</s:textfield><s:submit  cssClass="search" value="查询" /></li>
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
				<th class="header" name="title">新闻标题</th>
				<th class="header" name="category">新闻栏目</th>
				<th class="header" name="enterprise">添加企业</th>
				<th class="header" name="adduser">添加用户</th>
				<th class="header" name="addtime">添加时间</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="supply" value="pageList.items">
				<tr>
					<td><a href="viewNews?id=<s:property value="newsId" />" target="_blank"><s:property value="title" /></a></td>
					<td><s:property value="categoryName()" /></td>
					<td><a href="javascript:sortEnt('<s:property value="enterprise().enterpriseId" />','<s:property value="enterprise().name" />')"><s:property value="enterprise().name" /></a></td>
					<td><s:property value="addUser().username" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td><s:if test="newsState=='00'">
						<s:checkboxlist name="newsIds"
							list="%{#{newsId+';01':'审核通过',newsId+';02':'审核不通过'}}"></s:checkboxlist>
					</s:if> </td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="7" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<s:if test="newsState=='00'">
				<tr>
					<th colspan="7" class="tb_search">
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
</body>
</html>
