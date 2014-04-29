<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>技术实力</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript">
$(function(){
	
	$("#btnTechnic").click(function(){
		var reg =/((.|^)<script(.*)<\/script>)/i;
		$("#content").val().replace(reg,"")
		$("#technic").submit();
	})
})
</script>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<font color="red">${errors.content[0]}</font>
<s:form namespace="/user/technical" action="updateTechnic"
	id="technic">
	<s:hidden name="id" value="%{#request.AbcAttachment.attId}"></s:hidden>
<s:textarea name="content" id="content"  rows="12" cols="95" cssStyle="width: 100%;height:450px;" cssClass="ckeditor" value="%{#request.AbcAttachment.content}"></s:textarea>
	<br>
	<input type="button" id="btnTechnic" value="提交"  border="0" title="提交"/>
</s:form>
</body>
</html>
