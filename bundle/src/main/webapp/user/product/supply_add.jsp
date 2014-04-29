<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供求信息发布</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>"
	rel="stylesheet" />
<script type="text/javascript"
	src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript"
	src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript"
	src="<s:url value="/user/product/js/supply.js"/>"></script>
<link href="../css/div.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link href="../css/operation.css" rel="stylesheet" type="text/css" />
<link href="/css/dialog/jq_aero.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]><script type="text/javascript" src="/css/dialog/aero_ie6.js"></script><![endif]-->
<s:include value="/user/css/table.jsp"></s:include>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript">
$(function(){
	$("#thisForm").submit(function(){
		var reg =/((.|^)<script(.*)<\/script>)/i;
		$(".ckeditor").val().replace(reg,"")
		return true;
	})
	
	
})
</script>
</head>

<body>
<s:if test="errors.addState[0]!=null">
	<div id="warning">${errors.addState[0]}</div>
</s:if>
<s:form namespace="/user/supply" action="save" id="thisForm">
	<div id="body">
	<div id="main_r">
	<div class="main_c04">
	<table width="100%" height="27" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题<span
				class="font03">*</span></td>
			<td width="30%" align="left"><s:textfield name="title"
				id="title" cssClass="txt" /><span class="errorSpan">${errors.title[0]}</span></td>
			<td width="10%" align="center">对应产品<span class="font03">*</span></td>
			<td width="49%" align="left"><s:hidden name="productId" /> <s:textfield
				name="productName" id="productName" readonly="1" /> <input
				type=button value=请选择产品 onclick="showDialog()" /><span
				class="errorSpan">${errors.productId[0]}</span></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">产品价格</td>
			<td width="30%" align="left"><s:textfield name="price"
				cssClass="txt" /></td>
			<td width="10%" align="center">供求类型</td>
			<td width="49%" align="left"><s:select name="type"
				list="@com.abbcc.util.constant.SupplyType@values()" listKey="name()"
				value="type.name()" /><span class="errorSpan">${errors.type[0]}</span></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">关&nbsp;&nbsp;键&nbsp;字<span
				class="font03">*</span></td>
			<td width="30%" align="left"><s:textfield name="skey" id="skey"
				cssClass="txt" /><span class="errorSpan">${errors.skey[0]}</span></td>
			<td width="10%" align="center">计数单位</td>
			<td width="49%" align="left"><s:textfield name="unit" id="unit"
				cssClass="txt" /></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">联系方式</td>
			<td width="30%" align="left"><s:textfield name="contact" value="%{#session.abbccuser.phone}"
				onkeyup="this.value=this.value.replace(/\D/g,'')"
				onafterpaste="this.value=this.value.replace(/\D/g,'')"
				cssClass="txt" /><span class="errorSpan">${errors.contact[0]}</span></td>
			<td width="10%" align="center">发布时间</td>
			<td width="49%" align="left"><input type=text name="startTime"
				value="<s:date name="startTime" format="yyyy-MM-dd hh:mm:ss"/>"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"
				readonly="readonly" /><span class="errorSpan">${errors.startTime[0]}</span></td>
		</tr>
	</table>
	</div>
	<div class="main_c04">
	<table width="100%" height="25" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="10%" align="center">联&nbsp;系&nbsp;人</td>
			<td width="30%" align="left"><s:textfield name="contactId"
				id="contactId" cssClass="txt" value="%{#session.abbccuser.name}"/><span class="errorSpan">${errors.contactId[0]}</span></td>
			<td width="10%" align="center">到期时间<span class="font03"></span></td>
			<td width="49%" align="left"><input type=text name="endTime"
				value="<s:date name="endTime" format="yyyy-MM-dd hh:mm:ss"/>"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"
				readonly="readonly" /><span class="errorSpan"><s:property
				value="errors.endTime[0]" /></span></td>
		</tr>
	</table>
	</div>
	<div class="main_h">
	<table width="100%" height="95%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="left" valign="top"><s:textarea name="wdesc"
				id="wdesc" rows="20"  cssStyle="width: 90%;height:250px;" cssClass="ckeditor" /></td>
		</tr>
		<tr>
			<td align="left"><s:submit value="提交" title="提交" /></td>
		</tr>
	</table>
	</div>
	</div>
	</div>
</s:form>
<div id="dialog" title="请选择产品" style="display: none"><s:action
	name="productInnerList" namespace="/user/product" executeResult="true">
</s:action></div>
</body>
</html>
