<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/cvi/busy.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
function staticize(){
	$.ajax({
		url:"/user/emailValidate",
		cache : false,
		beforeSend:function(){
			busylay = getBusyOverlay(
				msie6?document.body:parent.document.body,
				{
					color : 'black',
					opacity : 0.4,
					text : '',
					style : 'text-decoration:blink;font-weight:bold;font-size:12px;color:white;',
					zIndex : 30
				},  {
					color : '#fff',
					size : 128,
					type : 'o'
				});
		},
		complete:function(){
			if(window.busylay)
				busylay.remove();
		}
	});
}
</script>
</head>
<body>
<div id="page"><input type="button" value="提交" onclick="staticize()"/></div>
</body>
</html>