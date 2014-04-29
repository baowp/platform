<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>

<script type="text/javascript"
	src="<s:url value="/js/ckeditor/ckeditor.js"/>"></script>
<link href="/user/css/operation.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function addcc(){
		document.getElementById("Trcc").style.display="";	
		document.getElementById("addccHref").innerHTML="删除抄送";	
		document.getElementById("addccHref").onclick=delcc; 

		
	}
	function addbcc(obj){
		document.getElementById("Trbcc").style.display="";
		document.getElementById("addbccHref").innerHTML="删除密送";
		document.getElementById("addbccHref").onclick=delbcc; 

		
	}
	function delbcc(){
		document.getElementById("Trbcc").style.display="none";
		document.getElementById("addbccHref").innerHTML="添加密送";
		document.getElementById("addbccHref").onclick=addbcc; 

	}
	function delcc(){
		document.getElementById("Trcc").style.display="none";
		document.getElementById("addccHref").innerHTML="添加抄送";
		document.getElementById("addccHref").onclick=addcc;
	}
</script>

</head>
<body>

<h3>发送邮件</h3>
<hr>
<s:form name="mailForm" action="/user/send" enctype="multipart/form-data" method="post">
	<table width="100%">
	<tr><td><a href="#" onclick="mailForm.submit()">发送</a></td><td align="left"><a href="#" onclick="addcc()" id="addccHref">添加抄送</a>||<a href="#" onclick="addbcc()" id="addbccHref">添加密送</a></td></tr>
		<tr>
			<td><s:text name="收件人" /></td>
			<td align="left"><s:textfield cssClass="txt" name="receiver" value="%{#request.emailList==null?receiver:#request.emailList}"/><font color="red"> ${errors.receiver[0]}</font></td>
		</tr>
		<tr id="Trcc" style="display:none;">
			<td>抄送</td>
			<td align="left"><s:textfield name="cc" cssClass="txt"></s:textfield></td>
			<td></td>
		</tr>

		<tr id="Trbcc" style="display:none;">
			<td>密送</td>
			<td align="left"><s:textfield cssClass="txt" name="bcc"></s:textfield></td>
			<td></td>
		</tr>
		<tr>
			<td><s:text name="主题" /></td>
			<td align="left"><s:textfield cssClass="txt" name="title"></s:textfield><font color="red"> ${errors.title[0]}</font></td>
		</tr>
		<tr>
			<td>添加附件</td>
			<td align="left"><s:file cssClass="txt" name="upload"></s:file></td>
		</tr>
		<tr>
			<td>内容</td>
			<td><textarea  name="content" class="ckeditor"></textarea></td>
		</tr>


		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</s:form>
</body>
</html>