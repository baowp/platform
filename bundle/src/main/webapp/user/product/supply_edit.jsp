<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供求信息发布</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value="/js/util/context.jsp"/>"></script>
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/common.js"/>"></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/user/abbcc/css/common.css"/>">
<script type="text/javascript" src="<s:url value="/user/product/js/supply.js"/>"></script>
<s:include value="/user/css/table.jsp"></s:include>
<s:include value="/common/xheditor.jsp"></s:include>
<script type="text/javascript">

$(function(){
	$('#productName').val(productMap[$('input[name=productId]').val()]);
});

</script>
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
<s:form namespace="/user/supply" action="update" id="thisForm">
	<s:hidden name="id" value="%{supplyId}"/>
	<s:hidden name="back"/>
	<table style="width:100%">
		<tr>
			<td width="10%">标题<font color="red">*</font> </td><td width="90%">
			<s:textfield name="title" /><span class="errorSpan">${errors.title[0]}</span></td>

		</tr>
		<tr>
			<td>供求类型<font color="red">*</font> </td><td>
			<s:select name="type" list="@com.abbcc.util.constant.SupplyType@values()"
					listKey="name()" value="type.name()"/><span class="errorSpan">${errors.type[0]}</span></td>
		</tr>
		<tr>
			<td>关键字<font color="red">*</font> </td><td>
			<s:textfield name="skey" /><span class="errorSpan">${errors.skey[0]}</span></td>
		</tr>
		<tr>
			<td>对应产品<font color="red">*</font> </td><td>
			
				<s:hidden name="productId" />
				<s:textfield name="productName" id="productName" readonly="1" />
				<input type=button value=请选择产品  onclick="showDialog()"/><span class="errorSpan">${errors.productId[0]}</span></td>
			
		</tr>
		<tr>
			<td>产品价格&nbsp; </td><td>
			<s:textfield name="price" /></td>
		</tr>
		<tr>
			<td>计数单位&nbsp; </td><td>
			<s:textfield name="unit" /></td>
		</tr>
		<tr>
			<td>产品联系人<font color="red">*</font> </td><td>
			<s:textfield name="contactId" /><span class="errorSpan">${errors.contactId[0]}</span></td>
		</tr>
		<tr>
			<td>联系方式<font color="red">*</font> </td><td>
			<s:textfield name="contact" /><span class="errorSpan">${errors.contact[0]}</span></td>
		</tr>
		<tr>
			<td>发布时间<font color="red">*</font> </td><td>
			<input type=text name="startTime" value="<s:date name="startTime" format="yyyy-MM-dd hh:mm:ss"/>"
				onfocus="WdatePicker()" class="Wdate" readonly="readonly"/><span class="errorSpan">${errors.startTime[0]}</span></td>
		</tr>
		<tr>
			<td>到期时间<font color="red">*</font> </td><td>
			<input type=text name="endTime" value="<s:date name="endTime" format="yyyy-MM-dd hh:mm:ss"/>"
				onfocus="WdatePicker()" class="Wdate" readonly="readonly"/><span class="errorSpan"><s:property value="endTime[0]"/></span>
		</td></tr>
		<tr>
			<td>简介&nbsp; </td><td>
			<s:textarea name="wdesc" rows="20"  cssStyle="width: 90%" cssClass="ckeditor"/>
		</td></tr>
		<tr>
			<td>&nbsp;</td><td>
			<s:submit value="提交"></s:submit>
			<input type="button" value="返回" onclick="javascript:history.go(-1);"/>
		</td></tr>
	</div>
</s:form>
<div id="dialog" title="请选择产品" style="display: none">
	<s:action name="productInnerList" namespace="/user/product" executeResult="true">
	</s:action>
</div>
</body>
</html> 
