<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
		function goto(page){
		$("#page").val(page);
			document.form1.submit();
		}
</script>
</head>
<body>
<s:form namespace="/admin" action="viewSubmembers"
	method="post" id="form1">
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">二级会员查询</li>
	<s:hidden name="enterpriseId"/>
	<li>
	查看“<s:property value="enterprise.name"/>”的二级会员</li>
	<li>搜索用户：<s:textfield name="memberKey"/> 
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
<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" id="tablesorter">
	<thead>
		<tr>
			<th class="header" name="username">用户名</th>
			<th class="header" name="state">账号状态</th>
			<th class="header" name="enterprise">企业名称</th>
			<th class="header" name="addTime">注册时间</th>
			<th class="header">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="user" value="pageList.items">
			<tr>
				<td><a href="infoMember?id=${userId}">${username }</a></td>
				<td><s:property value="stateName()" /></td>
				<td><s:property value="enterprise().name" /></td>
				<td><s:property value="addTimeString()" /></td>
				<td><s:url id="url" action= "viewSendUpgradeInvite">
						<s:param name="enterpriseId">
							<s:property value="enterpriseId" />
						</s:param>
						<s:param name="id">
							<s:property value="userId" />
						</s:param>
					</s:url> <s:a href="%{url}">发送邀请邮件</s:a></td>
			</tr>
		</s:iterator>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="10" class="tb_search">
			<s:include value="../../common/page.jsp">
				<s:param name="pageList" value="pageList"/> 
			</s:include>
			</th>
		</tr>
	</tfoot>
</table>
</div>
</div>
</s:form>
</body>
</html>
