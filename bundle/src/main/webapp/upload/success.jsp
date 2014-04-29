<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.abbcc.common.ConfConst"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件结果</title>
	<script type="text/javascript">
	function returnUpload(){
		document.body.innerHTML="<form action='../fileUpload/uploadImage' method='post' enctype='multipart/form-data'><input type='file' size='38' name='upload'/></form>";
	}
	</script>
</head>
<body>
	
	<s:if test="feedback.get(0)==''">
	上传成功！<a href="javascript:returnUpload()">再传一个！</a>
	<script type="text/javascript">
	var html="<img style='margin:5px; width:50px;height:50px' src='<%=ConfConst.FILE_SVR%><s:url value="%{uploadFilePath}"/>'/>";
	window.parent.document.getElementById("uploadedimages").innerHTML+=html;
	</script>
	</s:if>
	<s:else>
	上传失败，原因：	<s:property value="feedback.get(0)"></s:property>
	<a href="javascript:returnUpload()">返回</a>
	</s:else>
	
</body>
</html>