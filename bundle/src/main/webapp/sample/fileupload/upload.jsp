<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struts2 File Upload</title>  
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript">
	function addUploadField(){
		$("#uploadContainer").append('<div>选择文件：<input type="file" name="upload" size="50"/><input type="button" value="删除" onclick="removeUploadField(this)"></div>');
	}
	function removeUploadField(btn){
		$(btn.parentNode).remove();
	}
</script>
</head>  
<body>    
    <s:form namespace="/sample/fileupload" action="upload" method="POST" enctype="multipart/form-data">  
	        文件标题：<input type="text" name="title" size="50"/><br/>  
	   <div id=uploadContainer>
	   <s:if test="feedback!=null">
	   		<s:iterator	value="feedback">
		       	<div>选择文件：<input type="file" name="upload" size="50"/><input type="button" value="删除" onclick="removeUploadField(this)"><s:property/></div> 
		   	</s:iterator>
	   </s:if>
	   </div>
	   <input type="button"  value="添加附件" onclick="addUploadField()"><br/>
       <s:submit value=" 上传 " onclick=""/>          
    </s:form>  
</body>  
</html>  