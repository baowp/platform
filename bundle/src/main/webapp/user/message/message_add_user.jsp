<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>

 <script type="text/javascript">

 function submit2(){
	// alert(document.getElementsByName('username'))
	 window.returnValue=document.getElementsByName('username') ;
	 window.close();
	 return false;
 }
</script>
</head>
<body>
<center>
<h2>请选择接收人的名字</h2>
<hr>
<table>
<s:iterator var="user" value="pageList.items" status="st">
	<tr>
		<td><s:checkbox name="username" id="userId" fieldValue="%{username}" label="%{name}" theme="xhtml"></s:checkbox></td>
	</tr>

</s:iterator>
<tr><td><s:checkbox name="all" label="全选" onclick="check_all(this,'username')" theme="xhtml"/></td></tr>
<tr><td><input type=button value="确定" onclick="submit2()"/></td></tr>
</table>
</center>
</body>
</html>