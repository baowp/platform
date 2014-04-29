<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:debug/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<s:url value="/admin/css/common.css"/>" type="text/css" />
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/soa.js"/>"></script>
<title>修改个人信息</title>

<script type="text/javascript">
	testSoa = function(){
		soaObj.login();
	}
	
	setCookies = function(){
		soaObj.setCookies("testC", "testCVal");
		soaObj.setCookies("testC_1", "testC_1Val");
	}
	
	getCookies = function(){
		alert(soaObj.getCookies("testC"));
	}
	
	$(document).ready(function(){
		//soaObj.setMainPath("http://localhost:8080/Abbcc");
		soaObj.isLogin();
	})
</script>
</head>
<body>
<div id="sub_info">
	<a href="javascript:soaObj.login()">Login</a>
	<a href="javascript:soaObj.loginOut()" style="margin-left:50px">LoginOut</a>
</div>
<div id="resultDesc" style="margin-top:20px">
</div>

<!--
<a href="javascript:setCookies()">setCookies</a>
<a href="javascript:getCookies()">getCookies</a>
-->
</body>
</html>
