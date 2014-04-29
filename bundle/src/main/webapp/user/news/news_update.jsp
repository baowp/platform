<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<s:include value="/user/css/table.jsp"></s:include>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript">
$(document).ready(function() { 
	$("#isImagenews").change(function() {
		var  lbmc = $("#pic");
		 lbmc.toggle();
	});
});

</script>
<title>修改新闻</title>
</head>
<body><div id="body">
<center>
<h2>修改新闻</h2>
<hr>
</center>
<s:form action="newsupdateNews" namespace="/user/news"
	enctype="multipart/form-data">
	<s:hidden name="id" value="%{newsId}" />
	<table width="100%">

		<tr>
			<td width="10%">标题<font color="red">*</font></td>
			<td align="left" width="80%"><s:textfield name="title" value="%{title}" cssClass="txt" onblur="checkKey(this)"/></td>
			<td width="10%"><span class="errorSpan">${errors.title[0]}</span></td>
		</tr>
		<tr>
			<td>关键字<font color="red">*</font></td>
			<td align="left"><s:textfield name="nkey" cssClass="txt"  value="%{nkey}" onblur="checkKey(this)"/></td>
			<td><span class="errorSpan">${errors.nkey[0]}</span></td>
		</tr>
		<tr>
			<td>分类<font color="red">*</font></td>
			<td align="left"><s:select list="#request['sortMap']" listKey="key"
				listValue="value" name="category" value="category" /></td>
			<td></td>
		</tr>
		<tr>
			<td>内容<font color="red">*</font></td>
			<td align="left"><s:textarea name="content"  rows="12" cols="95" cssStyle="width: 100%" cssClass="ckeditor" value="%{content}"/></td>
			<td><span class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td>文章属性</td>
			<td align="left"><s:checkbox name="isImagenews" id="isImagenews"
				value="%{imagenews=='01'?01:''}" />图片&nbsp<s:checkbox
				name="isRollingnews" value="%{rollingnews=='01'?01:''}" />滚动新闻&nbsp<s:checkbox
				name="isTopnews" value="%{topnews=='01'?01:''}" />置顶</td>
		</tr>
		<tr id="pic" style="display: none;">
			<td>图片地址</td>
			<td align="left"><input type="file" name="upload" /></td>
		</tr>
		<tr>
			<td>来源<font color="red">*</font></td>
			<td align="left"><s:textfield name="origin" cssClass="txt"  value="%{origin}" onblur="checkKey(this)"/>作者<font color="red">*</font><s:textfield name="author" cssClass="txt"  value="%{author}" onblur="checkKey(this)"/></td>
		</tr>
		<tr>
			<td><s:submit value="提交" title="提交"/></td>
			<td></td>
		</tr>
	</table>
</s:form></div>
</body>
</html>