<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onLoad="WaitDo(3);" style="margin:20px;">
邮件已经发送成功，3秒自动关闭！
<script language="javascript">
function WaitDo(WaitTime){
 
           setTimeout(function(){window.close();},3000);
}
</script>

<style type="text/css">
span,font{font-size:16px; font-weight:bold;}
</style>

<span id="putOutMsg" class="OpenFont"></span>
</body>
</html>