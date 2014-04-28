<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>

<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="/js/util/onload_colorbox.js"></script>
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
<body>
<center>
<h2>修改新闻</h2>
<hr>
</center>
<s:form action="updateNews" namespace="/admin"
	enctype="multipart/form-data">
	<s:hidden name="id" value="%{newsId}" />
	<table>

		<tr>
			<td>标题<font color="red">*</font></td>
			<td><s:textfield name="title" value="%{title}" /></td>
			<td><span class="errorSpan">${errors.title[0]}</span></td>
		</tr>
		<tr>
			<td>关键字<font color="red">*</font></td>
			<td><s:textfield name="nkey" value="%{nkey}" /></td>
			<td><span class="errorSpan">${errors.nkey[0]}</span></td>
		</tr>
		<tr>
			<td>分类<font color="red">*</font></td>
			<td><s:select list="#session['sortMap']" listKey="key"
				listValue="value" name="category" value="category" /></td>
			<td></td>
		</tr>
		<tr>
			<td>内容<font color="red">*</font></td>
			<td><textarea class="ckeditor" name="content" rows="80"
				cols="70" style="width: 100%; height: 100%">${content}</textarea></td>
			<td><span class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td>文章属性</td>
			<td><s:checkbox name="isImagenews" id="isImagenews"
				value="%{imagenews=='01'?01:''}" />图片&nbsp<s:checkbox
				name="isRollingnews" value="%{rollingnews=='01'?01:''}" />滚动新闻&nbsp<s:checkbox
				name="isTopnews" value="%{topnews=='01'?01:''}" />置顶</td>
		</tr>
		<tr id="pic" style="${imagenews=='01'?'':'display: none;' }">
			<td><img src="<s:property value="picUrl()"/>" height="80" width="100" alt="图片"/></td>
			<td><input type="file" name="upload" /></td>
		</tr>
		<tr>
			<td>来源<font color="red">*</font></td>
			<td><s:textfield name="origin" value="%{origin}"/></td>
			<td>作者<font color="red">*</font></td>
			<td><s:textfield name="author" value="%{author}"/></td>
		</tr>
		<tr>
			<td><s:submit value="修改" /></td>
			<td><input type="button" value="取消" /></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>