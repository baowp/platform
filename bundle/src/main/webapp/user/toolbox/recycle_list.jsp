<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
</head>
<body style="padding: 10px;">
<table border="1" cellpadding="2" cellspacing="0"
	style="width: 100%; border-color: burlywood; border-collapse: collapse">
	<tr><th>所属模块</th><th>名称</th><th>删除时间</th><th>操作</th></tr>
	<s:iterator value="pageList.items">
		<tr>
			<td><s:property value="category!=null?category:belongType"/></td>
			<td><s:property value="content"/></td>
			<td><s:date name="delTime" format="yyyy-MM-dd hh:mm:ss"/></td>
			<td><a href="resume?id=${recycleId}">恢复</a> 
				<a href="remove?id=${recycleId}" onclick="if(!confirm('您确定要彻底删除吗？'))return false;">彻底删除</a></td>
		</tr>
	</s:iterator>	
</table>
</body>
</html>