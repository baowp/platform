<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link type="text/css" href="<s:url value="/css/jquery-ui.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery-ui.js"/>"></script>
<script type="text/javascript">
function onChooseEnt(obj){
	entCell=$(obj).parents("td:first");
	$('#entDialog').dialog('open');
	$('#entDialog').dialog({
		width:400,
		modal :true
	});
}
function chooseEnt(id){
	entCell.find(':hidden:first').val(id);
	entCell.find(':text:first').val(entMap[id]);
	$('#entDialog').dialog('close');
}
function onChooseProduct(){
	$('#productDialog').dialog('open');
	$('#productDialog').dialog({
		width:400,
		modal :true
	});
}
function chooseProduct(id,name){
	$('#productId').val(id);
	$('#productName').val(productMap[id]);
	$('#productDialog').dialog('close');
}
</script>
</head>
<body style="padding: 10px;">
<s:form action="save" namespace="/user/order">
<s:hidden name="id" value="%{orderId}"/>
<input type=hidden name="state" value="${empty state? '01':state}"/>
<table border="1" cellpadding="2" cellspacing="0"
	style="width: 100%; border-color: burlywood; border-collapse: collapse">
	<tr>
		<td width="20%">下单企业：</td>
		<td><s:hidden name="model.orderEnt.enterpriseId"/>
			<s:textfield name="model.orderEnt.name" /><input type=button value=选择企业  onclick="onChooseEnt(this)"/>
		</td>
	</tr>
	<tr>
		<td>收单企业：</td>
		<td><s:hidden name="model.gerorderEnt.enterpriseId"/>
			<s:textfield name="model.gerorderEnt.name" /><input type=button value=选择企业  onclick="onChooseEnt(this)"/>
		</td>
	</tr>
	<tr>
		<td>订单产品：</td>
		<td><s:hidden name="model.product.productId" id="productId"/>
			<s:textfield name="model.product.name" id="productName"/><input type=button value=选择产品 onclick="onChooseProduct()"/>
		</td>
	</tr>
	<tr>
		<td>订单数量：</td>
		<td><s:textfield name="orderSum"/></td>
		
	</tr>
	<tr>
		<td>下单日期：</td>
		<td><input type=text name=orderTime value="<s:date name="orderTime"/>"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate"/></td>
	</tr>
	<tr><td>收单企业回复：</td><td><s:textfield name="reply"/></td></tr>
	<tr><td>订单描述：</td><td><s:textfield name="odesc"/></td></tr>
		<tr>
			<td>处理状态：</td>
			<td><s:select name="dealState" listKey="name()"
				list="@com.abbcc.util.constant.OrderDealState@values()" /></td>
		</tr>
	</table>
	<s:submit/>
</s:form>
<div id="productDialog" title="请选择产品" style="display: none">
	<s:action name="tempProductList" namespace="/user/order" ignoreContextParams="true"/>
	<s:iterator value="#request['productList']">
	<div><a href="javascript:void(0)"
		onclick="chooseProduct('${productId}')">${name}</a></div>
	</s:iterator>
	<script type="text/javascript">
	var productMap={
		<s:iterator value="#request['productList']">
		${productId} : '${name}',
		</s:iterator>
		0:0
	}
	</script>
</div>
<div id="entDialog" title="请选择企业" style="display: none">
	<s:action name="tempEntList" namespace="/user/order" ignoreContextParams="true"/>
	<s:iterator value="#request['entList']">
	<div><a href="javascript:void(0)"
		onclick="chooseEnt('${enterpriseId}')">${name}</a></div>
	</s:iterator>
	<script type="text/javascript">
	var entMap={
		<s:iterator value="#request['entList']">
		${enterpriseId} : '${name}',
		</s:iterator>
		0:0
	}
	</script>
</div>
</body>
</html>