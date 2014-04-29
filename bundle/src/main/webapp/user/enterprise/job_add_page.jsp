<%@ page language="java" import="java.util.*,com.abbcc.models.AbcJob"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="<s:url value='/js/date.js'/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value='/user/css/validation.css'/>">
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<s:include value="/user/css/table.jsp"></s:include>
<script type="text/javascript">
	function checkForm(){
		var keyArray=new Array()
		keyArray[0]=$("#duty").val()
		keyArray[1]=$("#title").val()
		keyArray[2]=$("#content").val()
		checkKeys(keyArray);
		if(rkey!=null){
			alert("您输入的("+rkey+")是非法关键字!");
			return false;
		}
		return true;
			
	}
	</script>
</head>

<body>
<s:if test="errors.addState[0]!=null">
	<div id="warning">${errors.addState[0]}</div>
</s:if>
<form action="<s:url value='/user/enterprise/job/issuanceRecruit'/>"
	method="POST" onsubmit="return checkForm()">
<center>
<h1>发布招聘信息</h1>
</center>
<hr>
<input type="hidden" name="enterpriseId"
	value="${sessionScope.abbccEnterprise.enterpriseId}" />
<table>
	<tr>
		<td>标题<font color="red">*</font></td>
		<td><s:textfield name="title" id="title" cssClass="txt" /></td>
		<td><span class="errorSpan">${errors.title[0]}</span></td>
	</tr>
	<tr>
		<td>职务<font color="red">*</font></td>
		<td><s:textfield name="duty" id="duty" cssClass="txt" /></td>
	<tr>
		<td>人数<font}ont></td>
		<td><select name="sum">
			<%for (int sum = 1; sum < 1000; sum++) {%>
			<option value="<%=sum%>"><%=sum%></option>
			<%}%>
		</select></td>
		<td></td>
	</tr>
	<tr>
		<td>要求<font color="red">*</font></td>
		<td><s:textarea cols="30" rows="2" cssStyle="width:520px;height:200px;" name="content" id="content"
			cssClass="txt"></s:textarea></td>
		<td><span class="errorSpan">${errors.content[0]}</span></td>
	</tr>
	<tr>
		<td>有效期<font color="red">*</font></td>
		<td>截止至<input name="sDate2" type="text"
			value="<s:date name="publishTime" format="yyyy-MM-dd hh:mm:ss"/>"
			onfocus="WdatePicker()" class="Wdate" readonly="readonly" /></td>
		<td><span class="errorSpan">${errors.sDate2[0]}</span></td>
	</tr>
	<tr>
		<td><s:submit value="提交" /></td>
		<td></td>
	</tr>
</table>
</form>
</body>
</html>
