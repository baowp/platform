<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>abbcc</title>
<link rel="stylesheet" type="text/css"
	href="<s:url value="/user/abbcc/css/common.css"/>">
<link href="/user/css/div.css" rel="stylesheet" type="text/css" />
<link href="/user/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value='/js/datePicker/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery.js"/>"></script>
<script type="text/javascript" src="<s:url value="/js/jquery/colorize.js"/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>
<body style="padding: 10px;">
<div id="body">
<div class="fv">
<s:form action="stating" namespace="/user/order" ><table><tr><td>
	下单日期: <s:textfield name="frontTime" size="12"  onfocus="WdatePicker()" cssClass="Wdate"/> 
			- <s:textfield name="backTime" size="12" onfocus="WdatePicker()" cssClass="Wdate"/></td><td>
	订单状态: <s:select list="@com.abbcc.util.constant.OrderDealState@values()"
		listKey="name()" name="dealState" value="dealState.name()" 
		headerKey="" headerValue="全部"/></td><td>
	统计方式: <s:select list="#{1:'按产品',2:'按企业'}" name="statType" 
		/></td><td>
	<s:submit value="提交"/></td></tr></table>
</s:form></div>
<table class="listTable">
	<s:if test="statType==1">
		<s:include value="/user/order/inner_product_stat.jsp"/>
	</s:if>
	<s:elseif test="statType==2">
		<s:include value="/user/order/inner_custom_stat.jsp"/>
	</s:elseif>
</table></div>
</body>
</html>