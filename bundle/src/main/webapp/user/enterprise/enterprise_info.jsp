<%@ page language="java" import="java.util.*;" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司简介</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<s:include value="/common/xheditor.jsp"></s:include>
</head>

<body>
<s:include value="/common/resultMessage.jsp"></s:include>
<font color="red">${errors.content[0]}</font>
<s:form action="updateEnterpriseInfo" namespace="/user/enterprise/company">
<s:hidden name="id" value="%{enterpriseId}"></s:hidden>
<s:textarea name="edesc"  rows="15" cols="98" cssStyle="width: 100%;height:450px;" cssClass="ckeditor" value="%{edesc}"></s:textarea>
<br/><s:submit value="提交"  title="提交"/>
</s:form>
</body>
</html>
