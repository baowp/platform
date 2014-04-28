<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>查看管理员</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
	var type = "";
	var adminId = "";
	var state = 1;
	function changeActive(id) {
		adminId = id;
		var imgId = "img" + adminId;
		if ($('#' + imgId + '').attr("src") == "images/active.png")
			state = 2;
		else
			state = 1;
		var url = 'updateAdminState';
		var params = {
			id : id,
			state : state
		};
		$.ajax({
			url : url,
			type : "post",
			data : params,
			success : callbackFun
		});
	}
	function callbackFun(data) {
		if (state == 1) {
			var id = "img" + adminId;
			$('#' + id + '').attr({
				src : "images/active.png",
				alt : "激活",
				title : "激活"
			});
		}
		if (state == 2) {
			var id = "img" + adminId;
			$('#' + id + '').attr({
				src : "images/inactive.png",
				alt : "注销",
				title : "注销"
			});
		}
	}
	$(document).ready(function() {
		$("#filter").click(function() {
			window.location = "listAdmin?name=" + $("#name").val();
		});
	});
</script>
</head>
<body>
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if>
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<thead>
		<tr>
			<th class="header" name="username">用户名</th>
			<th class="header" name="name">姓名</th>
			<th class="header" name="email">email</th>
			<th class="header" name="cellphone">手机</th>
			<th class="header" name="gender">性别</th>
			<th class="header" name="type">类型</th>
			<th class="header" name="state">状态</th>
			<th class="header">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="admin" value="pageList.items">
			<tr>
				<td><s:property value="username" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="email" /></td>
				<td><s:property value="cellphone" /></td>
				<td><s:property value="#admin.genderName()" /></td>
				<td><s:property value="#admin.adminTypename()" /></td>
				<td><s:property value="#admin.adminStatename()" /></td>
				<td><s:if test="type!='05'">
					<a
						href="<%=request.getContextPath()%>/admin/viewAdmin?id=<s:property value="adminId" />">修改</a>/<a
						href="/admin/viewAdminpriv?adminId=<s:property value="adminId" />">权限</a>/<a
						href="javascript:if(confirm('确定要删除？'))window.location='<%=request.getContextPath()%>/admin/deleteAdmin?id=<s:property value="adminId" />'">删除</a>
				</s:if></td>
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
		<tr>
			<th style="text-align: left"><img src="images/refresh.png"
				alt="刷新" title="刷新" onclick="window.location.reload()" />
			<button type="button" id="addAdmin"
				onclick="window.location='<%=request.getContextPath()%>/admin/sysSettings/add_admin.jsp'">添加管理员</button>
			</th>
			<th colspan="7" class="tb_search">过滤器--<s:textfield id="name"
				name="name">姓名或者用户名： </s:textfield>
			<button type="button" id="filter">过滤</button>
			</th>
		</tr>
	</tfoot>
</table>
</div>
</body>
</html>
