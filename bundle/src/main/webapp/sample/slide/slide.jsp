<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/easyui/themes/default/easyui.css">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
<title>Insert title here</title>
<script>
		function show1(){
			jQuery.messager.show({
				title:'欢迎进入ABBCC后台!',
				msg:'温馨提示:请尽快完善个人信息，公司资料，以便客户能够快速找到您！',
				showType:'show'
			});
		}
		function show2(){
			$.messager.show({
				title:'My Title',
				msg:'Message will be closed after 5 seconds.',
				timeout:5000,
				showType:'slide'
			});
		}
		function show3(){
			$.messager.show({
				title:'My Title',
				msg:'Message never be closed.',
				timeout:0,
				showType:'fade'
			});
		}
		function alert1(){
			$.messager.alert('My Title','Here is a message!');
		}
		function alert2(){
			$.messager.alert('My Title','Here is a error message!','error');
		}
		function alert3(){
			$.messager.alert('My Title','Here is a info message!','info');
		}
		function alert4(){
			$.messager.alert('My Title','Here is a question message!','question');
		}
		function alert5(){
			$.messager.alert('My Title','Here is a warning message!','warning');
		}
		function confirm1(){
			$.messager.confirm('My Title', 'Are you confirm this?', function(r){
				if (r){
					alert('confirmed:'+r);
					location.href = 'http://www.google.com';
				}
			});
		}
		function prompt1(){
			$.messager.prompt('My Title', 'Please type something', function(r){
				if (r){
					alert('you type:'+r);
				}
			});
		}
	</script>
</head>
<body>
<h1>Messager</h1>
	<div>
		<a href="javascript:void(0)" onclick="show1()">show</a> |
		<a href="#" onclick="show2()">slide</a> |
		<a href="#" onclick="show3()">fade</a> |
	</div>
		
	<div>
		<a href="#" onclick="alert1()">alert</a> |
		<a href="#" onclick="alert2()">alert(error)</a> |
		<a href="#" onclick="alert3()">alert(info)</a> |
		<a href="#" onclick="alert4()">alert(question)</a> |
		<a href="#" onclick="alert5()">alert(warning)</a>
	</div>
	<div>
		<a href="#" onclick="confirm1();">confirm</a>
	</div>
	<div>
		<a href="#" onclick="prompt1()">prompt</a>
	</div>
	<div style="height:600px;"></div>
</body>
</html>