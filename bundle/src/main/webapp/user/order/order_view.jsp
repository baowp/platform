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
	style="width: 100%;  border-collapse: collapse">
	<tr>
		<td width="20%">下单企业：</td>
		<td>${orderEnt.name}
		</td>
	</tr>
	<tr>
		<td>收单企业：</td>
		<td>${gerorderEnt.name}
		</td>
	</tr>
	<tr>
		<td>订单产品：</td>
		<td>${product.name}
		</td>
	</tr>
	<tr>
		<td>产品单价(元)：</td>
		<td>${product.price}
		</td>
	</tr>
	<tr>
		<td>订单数量：</td>
		<td><s:property value="orderSum"/></td>
		
	</tr>
	<tr>
		<td>订单总额(元)：</td>
		<td><s:property value="numFmt.format(product.price*orderSum)"/>
		</td>
	</tr>
	<tr>
		<td>下单日期：</td>
		<td><s:date name="orderTime"/></td>
	</tr>
	<tr><td>收单企业回复：</td><td><s:property value="reply"/></td></tr>
	<tr><td>订单描述：</td><td><s:property value="odesc"/></td></tr>
		<tr>
			<td>处理状态：</td>
			<td><s:property value="dealState"/></td>
		</tr>
	</table>
	<input type="button" value="返回" onclick="javascript:history.go(-1);"/>
</body>
</html>