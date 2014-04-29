<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/jquery-ui.css">
<link rel="stylesheet" href="/user/abbcc/css/common.css">
<link rel="stylesheet" href="/css/tabs/jq-tabs.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/js/jquery/form.js"></script>
<script type="text/javascript" src="/js/jquery/cookie.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/user/vipsite/analysis/js/edit.js"></script>
</head>
<body>
<s:form action="update" cssClass="editGrid">
<s:hidden id="layoutId" name="layoutId"/>
<div id="tabs">
	<ul> 
		<li><a href="#tab1">站长统计</a></li> 
		<li><a href="#tab2">51la</a></li> 
	</ul> 
		<div id="tab1">
				<dl>
					<dt>统计代码</dt>
					<dd><s:textarea id="cnzz" name="cnzz" value="%{jsonFooter['cnzz']}" cssStyle="height: 100px;"/></dd>
				</dl>
				<dl>
					<dt>示例代码</dt>
					<dd>
					<label>&lt;script src="http://s19.cnzz.com/stat.php?id=2919983&web_id=2919983&show=pic" language="JavaScript">&lt;/script></label>
					<br><label><a style="color: red" href="http://www.cnzz.com" target="_blank">点击注册</a></label>
					</dd>
				</dl>
		</div>
		<div id="tab2">
				<dl>
					<dt>统计代码</dt>
					<dd><s:textarea id="51la" name="la" value="%{jsonFooter['51la']}" cssStyle="height: 100px;"/></dd>
				</dl>
				<dl>
					<dt>示例代码</dt>
					<dd><label>&lt;script language="javascript" type="text/javascript" src="http://js.users.51.la/4550240.js">&lt;/script>
&lt;noscript>&lt;a href="http://www.51.la/?4550240" target="_blank">&lt;img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/4550240.asp" style="border:none" />&lt;/a>&lt;/noscript></label>
					<br><label><a style="color: red" href="http://www.51.la" target="_blank">点击注册</a></label></dd>
				</dl>
				
		</div>
</div>
	<s:submit value="提交"/>
</s:form>
</body>
</html>