<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/js/cvi/busy.js"/>"></script> 
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
function sync(exdb,expic){
	$.ajax({
		url:"<s:url value="/user/syncsite/sync"/>",
		cache : false,
		data : {
			excludedb : exdb,
			excludepic : expic
		},
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
<div id="page">
	<p></p>
	<div>
		<span class="button2">
			<a href="javascript:sync()">同步</a>
		</span>
	</div>
	<br/>
	<br/>
	<br/>
	<p></p>
	<div>
		<span class="button2">
			<a href="javascript:sync(0,true)">同步数据</a>
		</span>
	</div>
	<br/>
	<br/>
	<br/>
	<p></p>
	<div>
		<span class="button2">
			<a href="javascript:sync(true,0)">同步文件</a>
		</span>
	</div>
</div>
</body>
</html>