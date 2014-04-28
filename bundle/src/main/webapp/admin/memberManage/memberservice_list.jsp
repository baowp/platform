<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员服务</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript">
		function goto(page){
		$("#page").val(page);
			document.form1.submit();
		}
</script>
</head>
<body onload="changePayendType()">
<s:form namespace="/admin" action="viewMemberservice" method="post"
	id="form1">
	<s:hidden name="page" id="page" />
	<div id="nav">
	<ul>
		<li id="man_nav_1" class="bg_image_onclick">会员服务</li>
		<li>会员类型：<s:radio name="memberGrade"
			list="%{#{'':'全部','11':'付费会员'}}" value="memberGrade" /> 会员名：<s:textfield
			name="searchName" /> <s:submit value="搜索" /></li>
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
				<th class="header" name="username">用户名</th>
				<th class="header" name="name">姓名</th>
				<th class="header" name="email">email</th>
				<th class="header" name="state">账号状态</th>
				<th class="header" name="type">账号类型</th>
				<th class="header" name="grade">付费等级</th>
				<th class="header" name="enterprise">企业名称</th>
				<th class="header" name="cellphone">手机</th>
				<th class="header" name="addTime">注册时间</th>
				<th class="header">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="user" value="pageList.items">
				<tr>
					<td><a href="infoMember?id=${userId}">${username }</a></td>
					<td><s:property value="name" /></td>
					<td><s:property value="email" /></td>
					<td><s:property value="stateName()" /></td>
					<td><s:property value="typeName()" /></td>
					<td><s:property value="gradeName()" /></td>
					<td><s:property value="enterprise().name" /></td>
					<td><s:property value="cellphone" /></td>
					<td><s:property value="addTimeString()" /></td>
					<td>
						<s:url id="url" action= "viewMemberservicehistory">
						<s:param name="userId">
							<s:property value="userId" />
						</s:param>
					</s:url> <s:a href="%{url}">查看服务记录</s:a>
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
		</tfoot>
	</table>
	</div>
	</div>
</s:form>
</body>
</html>
