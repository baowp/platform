<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>二级会员管理-企业名单</title>
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
<s:form namespace="/admin" action="viewEnterprises"
	method="post" id="form1">
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">会员企业</li>
	<li>
	搜索企业：<s:textfield name="entName"/> 
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
	<s:hidden name="page" id="page"/>
	<thead>
		<tr>
			<th class="header" name="name">企业名称</th>
			<th class="header" name="contact">联系人</th>
			<th class="header" name="contactPhone">联系电话</th>
			<th class="header" name="address">地址</th>
			<th class="header" name="industry">行业</th>
			<th class="header" name="regtime">注册时间</th>
			<th class="header">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="enterprise" value="pageList.items">
			<tr>
				<td><s:property value="name" /></td>
				<td><s:property value="contact" /></td>
				<td><s:property value="contactPhone" /></td>
				<td><s:property value="address" /></td>
				<td><s:property value="industry" /></td>
				<td><s:property value="regtime" /></td>
				<td>
					<s:url id="url" action= "viewSubmembers">
						<s:param name="enterpriseId">
							<s:property value="enterpriseId" />
						</s:param>
					</s:url> <s:a href="%{url}">查看二级会员</s:a>
				</td>
			</tr>
		</s:iterator>
		
	</tbody>
	<tfoot>
		<tr>
			<th colspan="7" class="tb_search">
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
