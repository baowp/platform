<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户群组管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$("#filter").click(function() {
				window.location="searchMember?searchWord="+$("#searchWord").val();
			});
			$("#addlink").click(function() {
				$("#add").css("display","block");
			});
			$("#cancel").click(function() {
				$("#add").css("display","none");
			});
		});
</script>
</head>
<body>
<s:form namespace="/admin" action="viewMembergroup"
	method="post" id="form1">
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">用户群组</li>
	<li>
	群组名称：<s:textfield name="searchName"></s:textfield>
	<s:submit value="确定"/></li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone">
<s:if test="result!=''&&result!=null">
<div id="warning">
	${result}
</div>
</s:if>
<div id="data">
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<thead>
		<tr>
			<th class="header" name="name">群组名称</th>
			<th class="header" name="desc">群组描述</th>
			<th class="header">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="group" value="groups">
			<tr>
				<td><s:property value="groupname" /></td>
				<td><s:property value="gdesc" /></td>
				<td>
					<s:url id="deleteurl" action= "deleteMembergroup">
						<s:param name="usergroupId">
							<s:property value="usergroupId" />
						</s:param>
					</s:url><a href="javascript:if(confirm('确定要删除？'))window.location='<s:property value="deleteurl"/>'"> 删除</a>
					<s:url id="editurl" action= "listMembergroup">
						<s:param name="usergroupId">
							<s:property value="usergroupId" />
						</s:param>
					</s:url> <s:a href="%{editurl}">设置成员</s:a>
				</td>
			</tr>
		</s:iterator>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="3" class="tb_search">
			<button type="button" id="addlink">添加群组</button>
			</th>
		</tr>
	</tfoot>
</table>
</s:form>
</div>
<div id="add" style="display:none">
<s:form namespace="/admin" action="addMembergroup"
	method="post" id="form2">
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1">
	<thead>
		<tr>
			<th class="header" name="name">群组名称</th>
			<th class="header" name="desc">群组描述</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td><s:textfield name="groupname"/></td>
				<td><s:textfield name="gdesc"/></td>
			</tr>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="2" class="tb_search">
			<button type="submit">提交</button><button type="button" id="cancel">取消</button>
			</th>
		</tr>
	</tfoot>
</table>
</s:form>
</div>
</div>
</div>
</body>
</html>
