<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar='Basic';
};

</script>
<title>群发邮件</title>
</head>
<body>
<div id="nav">
<ul>
	<li id="man_nav_1" class="bg_image_onclick">群发邮件</li>
	<li>请选择用户组</li>
</ul>
</div>
<div id="sub_info">
<div id="man_zone"><s:if test="result!=''&&result!=null">
	<div id="warning">${result}</div>
</s:if> <s:form namespace="/admin" action="sendGroupMail" method="post"
	id="form1">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">标题</span>
			</td>
			<td><s:textfield name="title" /></td>
		</tr>
		<tr>
			<td width="70px" class="left_title_1"><span class="left-title">发送的群组</span>
			</td>
			<td><s:checkboxlist name="groupIds"
				list="%{#session.usergroups}" listKey="usergroupId"
				listValue="groupname" value="true" /></td>
		</tr>
		<tr>
			<td class="left_title_1"><span class="left-title">邮件内容</span></td>
			<td><s:textarea name="content" cssClass="ckeditor">
			</s:textarea> <span class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td class="left_title_2"></td>
			<td class="left_title_2" style="text-align: left">
			<s:submit cssClass="submit" value="发送"/>
			</td>
		</tr>
	</table>
</s:form></div>
</div>
</body>
</html>
