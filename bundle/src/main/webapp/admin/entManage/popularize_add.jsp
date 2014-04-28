<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加推广</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<script type="text/javascript">
function forkey(obj){
	$("#key").val(document.getElementById("pName").value);
	
}
</script>
</head>
<body style="padding: 10px;">
<s:form namespace="/admin" action="savePopularize" method="post">
	<table border="1" cellpadding="2" cellspacing="0"
		style="width: 100%; border-color: burlywood; border-collapse: collapse">
		<tr>
			<td align="right" width="110">产品名称：</td>
			<td><s:textfield id="pName" name="pName" onkeyup="forkey(this)"/>${errors.pName[0]}</td>
		</tr>

		<tr>
			<td align="right">产品关键字：</td>
			<td><s:textfield name="key" id="key"/>${errors.key[0]}</td>
		</tr>
		<tr>
			<td align="right">实收价格：</td>
			<td><input type="text" name="price" onkeyup="if(event.keyCode!=37&amp;&amp;event.keyCode!=39)value=value.replace(/\D/g,'');"/>${errors.price[0]}</td>
		</tr>
		<tr>
			<td align="right">公司名字：</td>
			<td><s:textfield name="enterpriseName"/>${errors.enterpriseName[0]}</td>
		</tr>
		
		<tr>
			<td align="right">公司网址：</td>
			<td><s:textfield name="url" />${errors.url[0]}</td>
		</tr>

		<tr>
			<td align="right">开始时间：</td>
			<td><input type="text" name="startTime" 
				onfocus="WdatePicker({isShowWeek:true})" class="Wdate"/>${errors.startTime[0]}</td>
		</tr>
		<tr>
			<td align="right">到期时间：</td>
			<td><input type="text" name="endTime"
				onfocus="WdatePicker({isShowWeek:true})" class="Wdate"/><s:property value="errors.endTime[0]"/></td>
		</tr>
		<tr>
			<td align="right">产品简介：</td>
			<td><s:textarea name="content" cols="30"/></td>
		</tr>
	</table>
	<s:submit/>
</s:form>
</body>
</html>
