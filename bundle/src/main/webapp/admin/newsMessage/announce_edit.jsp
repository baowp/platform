<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改公告</title>
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>"
	type="text/css" />
<link rel="stylesheet" href="<s:url value="/admin/css/tinybox.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>

<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
	function checkValue(){
		if($("#title").val()==''){
			alert('请输入标题');
			return false;
		}else if($("#content").val()==''){
			alert('请输入内容');
			return false;
		}else
			return true;
	}
</script>
</head>
<body>
<center><h2>修改公告</h2></center><hr />
<s:form action="editAnnounce" namespace="/admin" onsubmit="return checkValue()"
	enctype="multipart/form-data">
	<s:hidden name="id"></s:hidden>
	<table>

		<tr>
			<td>标题<font color="red">*</font></td>
			<td><s:textfield id="title" name="title"  /></td>
			<td><span class="errorSpan">${errors.title[0]}</span></td>
		</tr>

		<tr>
			<td>内容<font color="red">*</font></td>
			<td colspan="4"><s:textarea id="content" name="content" cssClass="ckeditor"/></td>
			<td><span class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td><s:submit value="添加" /></td>
			<td><input type="button" value="取消" /></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>
