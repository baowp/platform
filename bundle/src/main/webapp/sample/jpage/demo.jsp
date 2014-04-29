<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 默认风格 -->
<!-- <link rel="stylesheet" type="text/css" href="./theme/default/css/jpage.css"/> -->
<!-- 黑色风格 -->
<!-- <link rel="stylesheet" type="text/css" href="./theme/black/css/jpage.css"/> -->
<!-- 白色风格 -->
<link rel="stylesheet" type="text/css" href="<s:url value="/css/jpage.css"/>"/>
<style>
body,div {
	font-size:13px;
	font-family:Verdana;
}

.pgContainer {
	background-color:#F9F9F9;
	padding:10px;
	line-height:20px;
}
#pagetest5 .pgContainer {
	color:#93A5B3;
	background:url(./temp/desk.jpg);
}


hr {
	margin:10px 0 10px 0;
	border:0;
	border-top:1px dashed #CCCCCC;
	height:0;
}

h3 {
	margin:5px 0;
	font-size:14px;
}
</style>
<script language="javascript" src="<s:url value="/js/jquery/jquery-1.2.3.pack.js"/>"></script>
<script language="javascript" src="<s:url value="/js/jquery/jquery.cookie-min.js"/>"></script>
<script language="javascript" src="<s:url value="/js/jquery/jquery.jpage.js"/>"></script>
<script language="javascript">
<!--

$(document).ready(
	function(){
		//demo1带参数
		var param = {
			"money":encodeURI($("#money").val())
		};
		
		//上下工具栏
		$("#pagetest3").jpage({totalRecord:96,proxyUrl:'data.action',groupSize:3,perPage:20,barPosition:'top&bottom'});
		
		
	}
)
//-->
</script>
</head>
<body>
<div style="width:750px;">
	<h3>Demo3（头部底部显示工具条）:</h3>
	<div id="pagetest3"></div>
	<hr/>

</div>
<table>
	<tr>
		<td></td>
</table>
</body>
</html>