<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/jquery-ui.css">
<link rel="stylesheet" href="/css/tabs/jq-tabs.css">
<link rel="stylesheet" href="/user/abbcc/css/common.css">
<link id="artDialogSkin" href="/js/artDialog3.0.5/skin/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/artDialog3.0.5/artDialog.min.js"></script>	
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery/form.js"></script>
<script type="text/javascript" src="/js/jquery/cookie.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="/js/jquery/tips/jquery.bt.css" />
<script src="/js/jquery/tips/jquery.qtip-1.0.0-rc3.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="/user/vipsite/seo/js/edit.js"></script>
</head>
<body>
<div id="tabs">
<ul>
	<s:iterator value="#request['pieceList']" status="st">
		<li><a href="#tabs-<s:property value="#st.index+1"/>"><s:property
			value="#request['map'][name()]" /></a></li>
	</s:iterator>
	<li><a  href="#tabs-submit">搜索引擎批量提交</a></li>
</ul>
<s:iterator value="resultList" status="st">
	<div id="tabs-<s:property value="#st.index+1"/>"><s:form
		action="update" cssClass="editGrid">
		<s:hidden name="id" value="%{seoId}" />
		<dl>
			<dt>标题</dt>
			<dd><s:textfield name="title" id="title-%{#st.index+1}" cssStyle="width:98%" /></dd>
		</dl>
		<dl class="h50">
			<dt>关键词</dt>
			<dd><s:textarea name="keywords" id="keywords-%{#st.index+1}" /></dd>
		</dl>
		<dl class="h90">
			<dt>描述</dt>
			<dd><s:textarea name="description" id="description-%{#st.index+1}"/></dd>
		</dl>
		<dl>
			<dt>&nbsp;</dt>
			<dd><s:submit value="提交" />&nbsp<input type="button" onclick="seoHelp()" id="button-<s:property value="#st.index+1"/>" value="优化帮助"/></dd>
		</dl>
	</s:form></div>
</s:iterator>
<div id="tabs-submit">
<center><input type="button" value="搜索批量提交" id="pltj" /></center>
</div>
</div>
</body>
</html>