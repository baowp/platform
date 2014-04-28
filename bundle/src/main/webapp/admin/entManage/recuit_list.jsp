<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>审核管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#submitform").click(function(){
				document.form1.action="editRecuitManage";
				document.form1.submit();
			});
		});
		function goto(page){
			$("#page").val(page);
			document.form1.submit();
		}
		function getEntRecuit(id){
			$("#entId").val(id);
			document.form1.submit();
		}
		
</script>
</head>
<body>
<s:form namespace="/admin" action="viewRecuitManage" method="post"
	id="form1" name="form1">
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">审核管理</li>
		<li>搜索：<s:textfield name="searchName"/> <s:submit cssClass="search" value="搜索"/>
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
		<s:hidden name="entId" id="entId"/>
		<thead>
			<tr>
				<th class="header" name="enterprise">企业</th>
				<th class="header" name="addTime">发布时间</th>
				<th class="header" name="title">标题</th>
				<th class="header" name="duty">招聘职务</th>
				<th class="header" name="sum">招聘人数</th>
				<th class="header" name="content">招聘内容</th>
				<th class="header" name="endTime">结束时间</th>
				<th class="header">删除</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="job" value="pageList.items">
				<tr>
					<td><a href="javascript:getEntRecuit('<s:property value="enterpriseId" />')"><s:property value="enterprise().name" /></a></td>
					<td><s:property value="addTimeString" /></td>
					<td><s:property value="title" /></td>
					<td><s:property value="duty" /></td>
					<td><s:property value="sum" /></td>
					<td title="<s:property value="content" />"><s:property value="content.length()>20?content.substring(0,20):content" /></td>
					<td><s:property value="endTimeString()" /></td>
					<td>
					<s:checkboxlist name="jobIds" list="%{#{jobId+';03'}}"></s:checkboxlist>
					</td>
				</tr>
			</s:iterator>

		</tbody>
		<tfoot>
			<tr>
				<th colspan="10" class="tb_search"><s:include
					value="../../common/page.jsp">
					<s:param name="pageList" value="pageList" />
				</s:include></th>
			</tr>
			<tr>
				<th colspan="9" class="tb_search">
				<button type="button" id="submitform" class="delete">删除</button>
				</th>
			</tr>
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
