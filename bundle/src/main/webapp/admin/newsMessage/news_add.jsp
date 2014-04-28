<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="/js/util/onload_colorbox.js"></script>
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src=""></script>
<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<script type="text/javascript"><!--
$(document).ready(function() { 
	$("#isImagenews").change(function() {
		var  lbmc = $("#pic");
		 lbmc.toggle();
	});
	$("#isText").change(function() {
		var  lbmc = $("#watermark");
		 lbmc.toggle();
	});  
	$("#textPic").click(function() {
		document.getElementById("addText").style.display="";
		document.getElementById("addPic").style.display='none';
	});
	$("#textPic2").click(function() {
		document.getElementById("addText").value="";
		document.getElementById("addPic").style.display="";
		document.getElementById("addText").style.display='none';
	});	
	
	$("#checkPic").change(function() {
		//var  lbmc = $("#pictrue");
		if($("#checkPic").attr("checked")==false){
			$("#fromFile").show();
			$("#pictrue").hide();
			$("#picPath").val("");
		}else{
			$("#fromFile").hide();
			$("#pictrue").show();
			 var file = $("#upload");  
			 file.after(file.clone().val(""));  
			file.remove();
		}

	});
});

	function forNkey(obj){
		$("#nkey").val(document.getElementById("title").value);
		
	}
	function checkValue(){
		if($("#isImagenews").attr("checked")==true){
			if($("#picPath").val()==''){
				alert('请选择图片');
				return false;
			}
			else
				return true;
		}
		else
			return true;
	}
--></script>
<title>添加新闻</title>
</head>
<body>
<center>
<h2>添加新闻</h2>
<hr>
</center>
<s:form action="addSysNews" namespace="/admin" onsubmit="return checkValue()"
	enctype="multipart/form-data">
	<table>

		<tr>
			<td>标题<font color="red">*</font></td>
			<td><s:textfield id="title" name="title" onkeyup="forNkey(this)" /></td>
			<td><span class="errorSpan">${errors.title[0]}</span></td>
		</tr>
		<tr>
			<td>关键字<font color="red">*</font></td>
			<td><s:textfield id="nkey" name="nkey" /></td>
			<td><span class="errorSpan">${errors.nkey[0]}</span></td>
		</tr>
		<tr>
			<td>分类<font color="red">*</font></td>
			<td><s:select list="#session['sortMap']" listKey="key"
				listValue="value" name="category" value="categoryId" /></td>
			<td></td>
		</tr>
		<tr>
			<td>内容<font color="red">*</font></td>
			<td colspan="4"><textarea class="ckeditor" name="content"
				rows="80" cols="70" style="width: 100%; height: 100%"></textarea></td>
			<td><span class="errorSpan">${errors.content[0]}</span></td>
		</tr>
		<tr>
			<td>文章属性</td>
			<td><s:checkbox name="isImagenews" id="isImagenews"/>图片&nbsp<s:checkbox
				name="isRollingnews" />滚动新闻&nbsp<s:checkbox name="isTopnews"/>置顶</td>
		</tr>

		<tr id="pic" style="display: none;">
			<td>图片地址</td>
			<td><input type="file" name="upload" /></td>
		</tr>

		<tr>
			<td>来源<font color="red">*</font></td>
			<td><s:textfield name="origin" /><span class="errorSpan">${errors.origin[0]}</span></td>
			<td>作者<font color="red">*</font></td>
			<td><s:textfield name="author" /><span class="errorSpan">${errors.author[0]}</span></td>
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