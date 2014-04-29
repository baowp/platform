<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<LINK rel=stylesheet type=text/css href="/user/platform/css/main.css" id="skin">
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="../js/artCommon.js"></script>
<script type="text/javascript" src="/user/enterprise/js/enterprise.js"></script>
</head>
<body>
<s:action name="hint" namespace="/user"></s:action>
<div style="opacity: 1;" class="mod-info-center clr" id="mod-info-center">
			<div class="mod-info-center-wrap">
				<s:if test="%{#request.hintCount==0}">
		<br><br>
		<center><h1>暂时没有提示信息!</h1></center>
		</s:if><s:else>
		<ul>
			<s:iterator value="%{#request.hintList}">
				<s:property escape="false" />
			</s:iterator>
		</ul></s:else>
</div>
			</div>
</body>
</html>